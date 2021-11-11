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

import br.com.algaworks.algafoods.domain.State;
import br.com.algaworks.algafoods.requersts.StatePostRequestBody;
import br.com.algaworks.algafoods.requersts.StatePutRequestBody;
import br.com.algaworks.algafoods.service.StateService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("cities")
@RequiredArgsConstructor
public class StateController {

	@Autowired
	private StateService stateService;

	@GetMapping
	public ResponseEntity<Page<State>> list(Pageable pageable) {
		return ResponseEntity.ok(stateService.listAll(pageable));
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<State>> listAll() {
		return ResponseEntity.ok(stateService.listAllNonPageable());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<State> findById(@PathVariable long id) {
		return ResponseEntity.ok(stateService.findByIdOrThrowBadRequestException(id));
	}

	@GetMapping(path = "/find")
	public ResponseEntity<List<State>> findByName(@RequestParam String name) {
		return ResponseEntity.ok(stateService.findByName(name));
	}

	@PostMapping
	public ResponseEntity<State> save(@RequestBody StatePostRequestBody statePostRequestBody) {
		return new ResponseEntity<>(stateService.save(statePostRequestBody), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/admin/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "When State Does Not Exist in The Database")
    })
	public ResponseEntity<Void> delete(@PathVariable long id) {
		stateService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping
	public ResponseEntity<Void> replace(@RequestBody StatePutRequestBody statePutRequestBody) {
		stateService.replace(statePutRequestBody);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping
	public ResponseEntity<Void> replacePartial(@RequestBody StatePutRequestBody statePutRequestBody) {
		stateService.replacePartial(statePutRequestBody);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
