package br.com.algaworks.algafoods.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.algaworks.algafoods.domain.Kitchen;
import br.com.algaworks.algafoods.repository.KitchenRepository;
import br.com.algaworks.algafoods.util.KitchenCreator;
import br.com.algaworks.algafoods.util.KitchenPostRequestBodyCreator;
import br.com.algaworks.algafoods.util.KitchenPutRequestBodyCreator;

@ExtendWith(SpringExtension.class)
class KitchenServiceTest {
	
	@InjectMocks
	private KitchenService kitchenService;
	@Mock
	private KitchenRepository kitchenRepositoryMock;

	@BeforeEach
	void setUp() {
		PageImpl<Kitchen> kitchenPage = new PageImpl<>(List.of(KitchenCreator.createValidKitchen()));
		BDDMockito.when(kitchenRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(kitchenPage);

		BDDMockito.when(kitchenRepositoryMock.findAll()).thenReturn(List.of(KitchenCreator.createValidKitchen()));

		BDDMockito.when(kitchenRepositoryMock.findById(ArgumentMatchers.anyLong()))
				.thenReturn(Optional.of(KitchenCreator.createValidKitchen()));

		BDDMockito.when(kitchenRepositoryMock.findByName(ArgumentMatchers.anyString()))
				.thenReturn(List.of(KitchenCreator.createValidKitchen()));

		BDDMockito.when(kitchenRepositoryMock.save(ArgumentMatchers.any(Kitchen.class)))
				.thenReturn(KitchenCreator.createValidKitchen());

		BDDMockito.doNothing().when(kitchenRepositoryMock).delete(ArgumentMatchers.any(Kitchen.class));
	}

	@Test
	@DisplayName("listAll returns list of kitchen inside page object when successful")
	void listAll_ReturnsListOfKitchensInsidePageObject_WhenSuccessful() {
		String expectedName = KitchenCreator.createValidKitchen().getName();

		Page<Kitchen> kitchenPage = kitchenService.listAll(PageRequest.of(1, 1));

		Assertions.assertThat(kitchenPage).isNotNull();

		Assertions.assertThat(kitchenPage.toList()).isNotEmpty().hasSize(1);

		Assertions.assertThat(kitchenPage.toList().get(0).getName()).isEqualTo(expectedName);
	}

	@Test
	@DisplayName("listAllNonPageable returns list of kitchen when successful")
	void listAllNonPageable_ReturnsListOfKitchens_WhenSuccessful() {
		String expectedName = KitchenCreator.createValidKitchen().getName();

		List<Kitchen> kitchens = kitchenService.listAllNonPageable();

		Assertions.assertThat(kitchens).isNotNull().isNotEmpty().hasSize(1);

		Assertions.assertThat(kitchens.get(0).getName()).isEqualTo(expectedName);
	}

//	@Test
//	@DisplayName("findByIdOrThrowBadRequestException returns kitchen when successful")
//	void findByIdOrThrowBadRequestException_ReturnsKitchen_WhenSuccessful() {
//		Long expectedId = KitchenCreator.createValidKitchen().getId();
//
//		Kitchen kitchen = kitchenService.findByIdOrThrowBadRequestException(1);
//
//		Assertions.assertThat(kitchen).isNotNull();
//
//		Assertions.assertThat(kitchen.getId()).isNotNull().isEqualTo(expectedId);
//	}

//	@Test
//	@DisplayName("findByIdOrThrowBadRequestException throws BadRequestException when kitchen is not found")
//	void findByIdOrThrowBadRequestException_ThrowsBadRequestException_WhenKitchenIsNotFound() {
//		BDDMockito.when(kitchenRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
//
//		Assertions.assertThatExceptionOfType(BadRequestException.class)
//				.isThrownBy(() -> kitchenService.findByIdOrThrowBadRequestException(1));
//	}

	@Test
	@DisplayName("findByName returns a list of kitchen when successful")
	void findByName_ReturnsListOfKitchen_WhenSuccessful() {
		String expectedName = KitchenCreator.createValidKitchen().getName();

		List<Kitchen> kitchens = kitchenService.findByName("kitchen");

		Assertions.assertThat(kitchens).isNotNull().isNotEmpty().hasSize(1);

		Assertions.assertThat(kitchens.get(0).getName()).isEqualTo(expectedName);
	}

	@Test
	@DisplayName("findByName returns an empty list of kitchen when kitchen is not found")
	void findByName_ReturnsEmptyListOfKitchen_WhenKitchenIsNotFound() {
		BDDMockito.when(kitchenRepositoryMock.findByName(ArgumentMatchers.anyString()))
				.thenReturn(Collections.emptyList());

		List<Kitchen> kitchens = kitchenService.findByName("kitchen");

		Assertions.assertThat(kitchens).isNotNull().isEmpty();

	}

	@Test
	@DisplayName("save returns kitchen when successful")
	void save_ReturnsKitchen_WhenSuccessful() {

		Kitchen kitchen = kitchenService.save(KitchenPostRequestBodyCreator.createKitchenPostRequestBody());

		Assertions.assertThat(kitchen).isNotNull().isEqualTo(KitchenCreator.createValidKitchen());

	}

	@Test
	@DisplayName("replace updates kitchen when successful")
	void replace_UpdatesKitchen_WhenSuccessful() {

		Assertions
				.assertThatCode(
						() -> kitchenService.replace(KitchenPutRequestBodyCreator.createKitchenPutRequestBody()))
				.doesNotThrowAnyException();

	}

	@Test
	@DisplayName("delete removes kitchen when successful")
	void delete_RemovesKitchen_WhenSuccessful() {

		Assertions.assertThatCode(() -> kitchenService.delete(1L)).doesNotThrowAnyException();

	}
}