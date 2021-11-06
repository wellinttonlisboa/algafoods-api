package br.com.algaworks.algafoods.controller;

import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.algaworks.algafoods.domain.Kitchen;
import br.com.algaworks.algafoods.requersts.KitchenPostRequestBody;
import br.com.algaworks.algafoods.requersts.KitchenPutRequestBody;
import br.com.algaworks.algafoods.service.KitchenService;
import br.com.algaworks.algafoods.util.KitchenCreator;
import br.com.algaworks.algafoods.util.KitchenPostRequestBodyCreator;
import br.com.algaworks.algafoods.util.KitchenPutRequestBodyCreator;

@ExtendWith(SpringExtension.class)
class KitchenControllerTest {

	@InjectMocks
	private KitchenController kitchenController;
	@Mock
	private KitchenService kitchenServiceMock;

	@BeforeEach
	void setUp() {
		PageImpl<Kitchen> kitchenPage = new PageImpl<>(List.of(KitchenCreator.createValidKitchen()));
		BDDMockito.when(kitchenServiceMock.listAll(ArgumentMatchers.any())).thenReturn(kitchenPage);

		BDDMockito.when(kitchenServiceMock.listAllNonPageable())
				.thenReturn(List.of(KitchenCreator.createValidKitchen()));

		BDDMockito.when(kitchenServiceMock.findByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
				.thenReturn(KitchenCreator.createValidKitchen());

		BDDMockito.when(kitchenServiceMock.findByName(ArgumentMatchers.anyString()))
				.thenReturn(List.of(KitchenCreator.createValidKitchen()));

		BDDMockito.when(kitchenServiceMock.save(ArgumentMatchers.any(KitchenPostRequestBody.class)))
				.thenReturn(KitchenCreator.createValidKitchen());

		BDDMockito.doNothing().when(kitchenServiceMock).replace(ArgumentMatchers.any(KitchenPutRequestBody.class));

		BDDMockito.doNothing().when(kitchenServiceMock).delete(ArgumentMatchers.anyLong());
	}

	@Test
	@DisplayName("list returns list of kitchen inside page object when successful")
	void list_ReturnsListOfKitchensInsidePageObject_WhenSuccessful() {
		String expectedName = KitchenCreator.createValidKitchen().getName();

		Page<Kitchen> kitchenPage = kitchenController.list(null).getBody();

		Assertions.assertThat(kitchenPage).isNotNull();

		Assertions.assertThat(kitchenPage.toList()).isNotEmpty().hasSize(1);

		Assertions.assertThat(kitchenPage.toList().get(0).getName()).isEqualTo(expectedName);
	}

	@Test
	@DisplayName("listAll returns list of kitchen when successful")
	void listAll_ReturnsListOfKitchens_WhenSuccessful() {
		String expectedName = KitchenCreator.createValidKitchen().getName();

		List<Kitchen> kitchens = kitchenController.listAll().getBody();

		Assertions.assertThat(kitchens).isNotNull().isNotEmpty().hasSize(1);

		Assertions.assertThat(kitchens.get(0).getName()).isEqualTo(expectedName);
	}

	@Test
	@DisplayName("findById returns kitchen when successful")
	void findById_ReturnsKitchen_WhenSuccessful() {
		Long expectedId = KitchenCreator.createValidKitchen().getId();

		Kitchen kitchen = kitchenController.findById(1L).getBody();

		Assertions.assertThat(kitchen).isNotNull();

		Assertions.assertThat(kitchen.getId()).isNotNull().isEqualTo(expectedId);
	}

	@Test
	@DisplayName("findByName returns a list of kitchen when successful")
	void findByName_ReturnsListOfKitchen_WhenSuccessful() {
		String expectedName = KitchenCreator.createValidKitchen().getName();

		List<Kitchen> kitchens = kitchenController.findByName("kitchen").getBody();

		Assertions.assertThat(kitchens).isNotNull().isNotEmpty().hasSize(1);

		Assertions.assertThat(kitchens.get(0).getName()).isEqualTo(expectedName);
	}

	@Test
	@DisplayName("findByName returns an empty list of kitchen when kitchen is not found")
	void findByName_ReturnsEmptyListOfKitchen_WhenKitchenIsNotFound() {
		BDDMockito.when(kitchenServiceMock.findByName(ArgumentMatchers.anyString()))
				.thenReturn(Collections.emptyList());

		List<Kitchen> kitchens = kitchenController.findByName("kitchen").getBody();

		Assertions.assertThat(kitchens).isNotNull().isEmpty();

	}

	@Test
	@DisplayName("save returns kitchen when successful")
	void save_ReturnsKitchen_WhenSuccessful() {

		Kitchen kitchen = kitchenController.save(KitchenPostRequestBodyCreator.createKitchenPostRequestBody())
				.getBody();

		Assertions.assertThat(kitchen).isNotNull().isEqualTo(KitchenCreator.createValidKitchen());

	}

	@Test
	@DisplayName("replace updates kitchen when successful")
	void replace_UpdatesKitchen_WhenSuccessful() {

		Assertions
				.assertThatCode(
						() -> kitchenController.replace(KitchenPutRequestBodyCreator.createKitchenPutRequestBody()))
				.doesNotThrowAnyException();

		ResponseEntity<Void> entity = kitchenController
				.replace(KitchenPutRequestBodyCreator.createKitchenPutRequestBody());

		Assertions.assertThat(entity).isNotNull();

		Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

	@Test
	@DisplayName("delete removes kitchen when successful")
	void delete_RemovesKitchen_WhenSuccessful() {

		Assertions.assertThatCode(() -> kitchenController.delete(1L)).doesNotThrowAnyException();

		ResponseEntity<Void> entity = kitchenController.delete(1L);

		Assertions.assertThat(entity).isNotNull();

		Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}
}