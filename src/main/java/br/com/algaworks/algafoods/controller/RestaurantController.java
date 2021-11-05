package br.com.algaworks.algafoods.controller;

import java.util.List;

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
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("restaurants")
@RequiredArgsConstructor
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
	public ResponseEntity<Restaurant> findById(@PathVariable long id) {
		return ResponseEntity.ok(restaurantService.findByIdOrThrowBadRequestException(id));
	}

	@GetMapping(path = "/find")
	public ResponseEntity<List<Restaurant>> findByName(@RequestParam String name) {
		return ResponseEntity.ok(restaurantService.findByName(name));
	}

	@PostMapping
	public ResponseEntity<Restaurant> save(@RequestBody RestaurantPostRequestBody restaurantPostRequestBody) {
		return new ResponseEntity<>(restaurantService.save(restaurantPostRequestBody), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/admin/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "When Restaurant Does Not Exist in The Database")
    })
	public ResponseEntity<Void> delete(@PathVariable long id) {
		restaurantService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping
	public ResponseEntity<Void> replace(@RequestBody RestaurantPutRequestBody restaurantPutRequestBody) {
		restaurantService.replace(restaurantPutRequestBody);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping
	public ResponseEntity<Void> replacePartial(@RequestBody RestaurantPutRequestBody restaurantPutRequestBody) {
		restaurantService.replacePartial(restaurantPutRequestBody);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
