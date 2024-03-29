package br.com.algaworks.algafoods.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.algaworks.algafoods.domain.Restaurant;
import br.com.algaworks.algafoods.requersts.RestaurantPostRequestBody;
import br.com.algaworks.algafoods.requersts.RestaurantPutRequestBody;
import br.com.algaworks.algafoods.service.RestaurantService;

@RestController
@RequestMapping("restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@GetMapping
	public ResponseEntity<Page<Restaurant>> list(Pageable pageable) {
		return ResponseEntity.ok(restaurantService.listAll(pageable));
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<Restaurant>> listAll() {
		return ResponseEntity.ok(restaurantService.listAllNonPageable());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Restaurant> findById(@PathVariable Long id) {
		return ResponseEntity.ok(restaurantService.findByIdOrThrowBadRequestException(id));
	}

	@GetMapping(path = "/find-like")
	public ResponseEntity<List<Restaurant>> findByNameContaining(@RequestParam String name) {
		return ResponseEntity.ok(restaurantService.findByNameContaining(name));
	}

	@GetMapping(path = "/find-and-like")
	public ResponseEntity<List<Restaurant>> findByNameContainingAndKitchenNameContaining(
			@RequestParam String restaurantName, @RequestParam String kitchenName) {
		return ResponseEntity
				.ok(restaurantService.findByNameContainingAndKitchenNameContaining(restaurantName, kitchenName));
	}

	@GetMapping(path = "/find-and-id")
	public ResponseEntity<List<Restaurant>> findByNameContainingAndKitchenId(@RequestParam String restaurantName,
			@RequestParam Long kitchenId) {
		return ResponseEntity.ok(restaurantService.findByNameContainingAndKitchenId(restaurantName, kitchenId));
	}
	
	@GetMapping(path = "/find-and-id-caraideasa")
	public ResponseEntity<List<Restaurant>> caraiDeAsa(@RequestParam String restaurantName,
			@RequestParam Long kitchenId) {
		return ResponseEntity.ok(restaurantService.caraiDeAsa(restaurantName, kitchenId));
	}

	@GetMapping(path = "/find-freight-between-barrildobrado")
	public ResponseEntity<List<Restaurant>> barrilDobrado(@RequestParam String name
			, @RequestParam Long startFreight
			, @RequestParam Long endFreight) {
		return ResponseEntity.ok(restaurantService.barrilDobrado(name, startFreight, endFreight));
	}
	
	@GetMapping(path = "/first")
	public ResponseEntity<Optional<Restaurant>> customBuscarPrimeiro() {
		return ResponseEntity.ok(restaurantService.customBuscarPrimeiro());
	}
	
	@GetMapping(path = "/find-freight")
	public ResponseEntity<List<Restaurant>> findByFreight(@RequestParam Long freight) {
		return ResponseEntity.ok(restaurantService.findByFreight(freight));
	}

	@GetMapping(path = "/find-freight-between")
	public ResponseEntity<List<Restaurant>> findByFreightBetween(@RequestParam Long startFreight,
			@RequestParam Long endFreight) {
		return ResponseEntity.ok(restaurantService.findByFreightBetween(startFreight, endFreight));
	}

	@PostMapping
	public ResponseEntity<Restaurant> save(@RequestBody RestaurantPostRequestBody restaurantPostRequestBody) {
		return new ResponseEntity<>(restaurantService.save(restaurantPostRequestBody), HttpStatus.CREATED);
	}

	@PatchMapping(path = "/{id}")
	public ResponseEntity<Restaurant> replacePartial(@PathVariable Long id
			, @RequestBody Map<String, Object> patchRequestBody) {
		return new ResponseEntity<>(restaurantService.replacePartial(id, patchRequestBody), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Void> replace(@RequestBody RestaurantPutRequestBody restaurantPutRequestBody) {
		restaurantService.replace(restaurantPutRequestBody);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(path = "/admin/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		restaurantService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}