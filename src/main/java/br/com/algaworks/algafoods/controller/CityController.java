package br.com.algaworks.algafoods.controller;

import java.math.BigDecimal;
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

import br.com.algaworks.algafoods.domain.City;
import br.com.algaworks.algafoods.requersts.CityPostRequestBody;
import br.com.algaworks.algafoods.requersts.CityPutRequestBody;
import br.com.algaworks.algafoods.service.CityService;

@RestController
@RequestMapping("cities")
public class CityController {

	@Autowired
	private CityService cityService;

	@GetMapping
	public ResponseEntity<Page<City>> list(Pageable pageable) {
		return ResponseEntity.ok(cityService.listAll(pageable));
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<City>> listAll() {
		return ResponseEntity.ok(cityService.listAllNonPageable());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<City> findById(@PathVariable long id) {
		return ResponseEntity.ok(cityService.findByIdOrThrowBadRequestException(id));
	}

	@GetMapping(path = "/find")
	public ResponseEntity<List<City>> findByName(@RequestParam String name) {
		return ResponseEntity.ok(cityService.findByName(name));
	}
	
	@GetMapping(path = "/find-like")
	public ResponseEntity<List<City>> findByNameContaining(@RequestParam String name) {
		return ResponseEntity.ok(cityService.findByNameContaining(name));
	}
	
	@GetMapping(path = "/find-and-like")
	public ResponseEntity<List<City>> findByNameContainingAndStateNameContaining(String cityName
			,String stateName) {
		return ResponseEntity.ok(cityService.findByNameContainingAndStateNameContaining(cityName, stateName));
	}
	
	@GetMapping(path = "/find-and-id")
	public ResponseEntity<List<City>> findByNameContainingAndStateId(String name
			,BigDecimal stateId) {
		return ResponseEntity.ok(cityService.findByNameContainingAndStateId(name, stateId));
	}

	@PostMapping
	public ResponseEntity<City> save(@RequestBody CityPostRequestBody cityPostRequestBody) {
		return new ResponseEntity<>(cityService.save(cityPostRequestBody), HttpStatus.CREATED);
	}

	@PatchMapping
	public ResponseEntity<Void> replacePartial(@RequestBody CityPutRequestBody cityPutRequestBody) {
		cityService.replacePartial(cityPutRequestBody);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping
	public ResponseEntity<Void> replace(@RequestBody CityPutRequestBody cityPutRequestBody) {
		cityService.replace(cityPutRequestBody);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(path = "/admin/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) {
		cityService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}