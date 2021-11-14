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

import br.com.algaworks.algafoods.domain.Kitchen;
import br.com.algaworks.algafoods.requersts.KitchenPostRequestBody;
import br.com.algaworks.algafoods.requersts.KitchenPutRequestBody;
import br.com.algaworks.algafoods.requersts.PatchRequestBody;
import br.com.algaworks.algafoods.service.KitchenService;

@RestController
@RequestMapping("kitchens")
public class KitchenController {

	@Autowired
	private KitchenService kitchenService;

	@GetMapping
	public ResponseEntity<Page<Kitchen>> list(Pageable pageable) {
		return ResponseEntity.ok(kitchenService.listAll(pageable));
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<Kitchen>> listAll() {
		return ResponseEntity.ok(kitchenService.listAllNonPageable());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Kitchen> findById(@PathVariable Long id) {
		return ResponseEntity.ok(kitchenService.findByIdOrThrowBadRequestException(id));
	}

	@GetMapping(path = "/find")
	public ResponseEntity<List<Kitchen>> findByName(@RequestParam String name) {
		return ResponseEntity.ok(kitchenService.findByName(name));
	}

	@GetMapping(path = "/find-like")
	public ResponseEntity<List<Kitchen>> findByNameContaining(@RequestParam String name) {
		return ResponseEntity.ok(kitchenService.findByNameContaining(name));
	}

	@PostMapping
	public ResponseEntity<Kitchen> save(@RequestBody KitchenPostRequestBody kitchenPostRequestBody) {
		return new ResponseEntity<>(kitchenService.save(kitchenPostRequestBody), HttpStatus.CREATED);
	}

	@PatchMapping(path = "/{id}")
	public ResponseEntity<Kitchen> replacePartial(@PathVariable Long id
			,@RequestBody PatchRequestBody patchRequestBody) {
		return new ResponseEntity<>(kitchenService.replacePartial(id, patchRequestBody), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Void> replace(@RequestBody KitchenPutRequestBody KitchenPutRequestBody) {
		kitchenService.replace(KitchenPutRequestBody);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(path = "/admin/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		kitchenService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}