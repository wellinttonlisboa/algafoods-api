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

import br.com.algaworks.algafoods.domain.PaymentMethod;
import br.com.algaworks.algafoods.requersts.PaymentMethodPostRequestBody;
import br.com.algaworks.algafoods.requersts.PaymentMethodPutRequestBody;
import br.com.algaworks.algafoods.service.PaymentMethodService;

@RestController
@RequestMapping("payments")
public class PaymentMethodController {

	@Autowired
	private PaymentMethodService paymentMethodService;

	@GetMapping
	public ResponseEntity<Page<PaymentMethod>> list(Pageable pageable) {
		return ResponseEntity.ok(paymentMethodService.listAll(pageable));
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<PaymentMethod>> listAll() {
		return ResponseEntity.ok(paymentMethodService.listAllNonPageable());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<PaymentMethod> findById(@PathVariable long id) {
		return ResponseEntity.ok(paymentMethodService.findByIdOrThrowBadRequestException(id));
	}

	@GetMapping(path = "/find")
	public ResponseEntity<List<PaymentMethod>> findByName(@RequestParam String name) {
		return ResponseEntity.ok(paymentMethodService.findByName(name));
	}
	
	@GetMapping(path = "/find-like")
	public ResponseEntity<List<PaymentMethod>> findByNameContaining(@RequestParam String name) {
		return ResponseEntity.ok(paymentMethodService.findByNameContaining(name));
	}

	@PostMapping
	public ResponseEntity<PaymentMethod> save(@RequestBody PaymentMethodPostRequestBody paymentMethodPostRequestBody) {
		return new ResponseEntity<>(paymentMethodService.save(paymentMethodPostRequestBody), HttpStatus.CREATED);
	}

	@PatchMapping
	public ResponseEntity<Void> replacePartial(@RequestBody PaymentMethodPutRequestBody paymentMethodPutRequestBody) {
		paymentMethodService.replacePartial(paymentMethodPutRequestBody);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping
	public ResponseEntity<Void> replace(@RequestBody PaymentMethodPutRequestBody paymentMethodPutRequestBody) {
		paymentMethodService.replace(paymentMethodPutRequestBody);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(path = "/admin/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) {
		paymentMethodService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}