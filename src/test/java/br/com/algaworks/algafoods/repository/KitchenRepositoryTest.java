package br.com.algaworks.algafoods.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.algaworks.algafoods.domain.Kitchen;
import br.com.algaworks.algafoods.util.KitchenCreator;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DisplayName("Tests for Kitchen Repository")
class KitchenRepositoryTest {

	@Autowired
	private KitchenRepository kitchenRepository;

	@Test
	@DisplayName("Save persists kitchen when Successful")
	void save_PersistKitchen_WhenSuccessful() {
		Kitchen kitchenToBeSaved = KitchenCreator.createKitchenToBeSaved();

		Kitchen kitchenSaved = kitchenRepository.save(kitchenToBeSaved);

		Assertions.assertThat(kitchenSaved).isNotNull();

		Assertions.assertThat(kitchenSaved.getId()).isNotNull();

		Assertions.assertThat(kitchenSaved.getName()).isEqualTo(kitchenToBeSaved.getName());
	}

	@Test
	@DisplayName("Save updates kitchen when Successful")
	void save_UpdatesKitchen_WhenSuccessful() {
		Kitchen kitchenToBeSaved = KitchenCreator.createKitchenToBeSaved();

		Kitchen kitchenSaved = kitchenRepository.save(kitchenToBeSaved);

		kitchenSaved.setName("Italiana");

		Kitchen kitchenUpdated = kitchenRepository.save(kitchenSaved);

		Assertions.assertThat(kitchenUpdated).isNotNull();

		Assertions.assertThat(kitchenUpdated.getId()).isNotNull();

		Assertions.assertThat(kitchenUpdated.getName()).isEqualTo(kitchenSaved.getName());
	}

	@Test
	@DisplayName("Delete removes kitchen when Successful")
	void delete_RemovesKitchen_WhenSuccessful() {
		Kitchen kitchenToBeSaved = KitchenCreator.createKitchenToBeSaved();

		Kitchen kitchenSaved = kitchenRepository.save(kitchenToBeSaved);

		kitchenRepository.delete(kitchenSaved);

		Optional<Kitchen> kitchenOptional = kitchenRepository.findById(kitchenSaved.getId());

		Assertions.assertThat(kitchenOptional).isEmpty();

	}

	@Test
	@DisplayName("Find By Name returns list of kitchen when Successful")
	void findByName_ReturnsListOfKitchen_WhenSuccessful() {
		Kitchen kitchenToBeSaved = KitchenCreator.createKitchenToBeSaved();

		Kitchen kitchenSaved = kitchenRepository.save(kitchenToBeSaved);

		String name = kitchenSaved.getName();

		List<Kitchen> kitchens = kitchenRepository.findByName(name);

		Assertions.assertThat(kitchens).isNotEmpty().contains(kitchenSaved);

	}

	@Test
	@DisplayName("Find By Name returns empty list when no kitchen is found")
	void findByName_ReturnsEmptyList_WhenKitchenIsNotFound() {
		List<Kitchen> kitchens = kitchenRepository.findByName("kitchen");

		Assertions.assertThat(kitchens).isEmpty();
	}

	@Test
	@DisplayName("Save throw ConstraintViolationException when name is empty")
	void save_ThrowsConstraintViolationException_WhenNameIsEmpty() {
		Kitchen kitchen = new Kitchen();

		Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
				.isThrownBy(() -> kitchenRepository.save(kitchen))
				.withMessageContaining("The kitchen name cannot be empty");
	}

}