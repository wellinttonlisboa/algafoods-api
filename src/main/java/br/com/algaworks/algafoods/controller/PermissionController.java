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

import br.com.algaworks.algafoods.domain.Permission;
import br.com.algaworks.algafoods.requersts.PermissionPostRequestBody;
import br.com.algaworks.algafoods.requersts.PermissionPutRequestBody;
import br.com.algaworks.algafoods.service.PermissionService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("permissions")
@RequiredArgsConstructor
public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	@GetMapping
	public ResponseEntity<Page<Permission>> list(Pageable pageable) {
		return ResponseEntity.ok(permissionService.listAll(pageable));
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<Permission>> listAll() {
		return ResponseEntity.ok(permissionService.listAllNonPageable());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Permission> findById(@PathVariable long id) {
		return ResponseEntity.ok(permissionService.findByIdOrThrowBadRequestException(id));
	}

	@GetMapping(path = "/find")
	public ResponseEntity<List<Permission>> findByName(@RequestParam String name) {
		return ResponseEntity.ok(permissionService.findByName(name));
	}

	@PostMapping
	public ResponseEntity<Permission> save(@RequestBody PermissionPostRequestBody permissionPostRequestBody) {
		return new ResponseEntity<>(permissionService.save(permissionPostRequestBody), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/admin/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "When Permission Does Not Exist in The Database")
    })
	public ResponseEntity<Void> delete(@PathVariable long id) {
		permissionService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping
	public ResponseEntity<Void> replace(@RequestBody PermissionPutRequestBody permissionPutRequestBody) {
		permissionService.replace(permissionPutRequestBody);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping
	public ResponseEntity<Void> replacePartial(@RequestBody PermissionPutRequestBody permissionPutRequestBody) {
		permissionService.replacePartial(permissionPutRequestBody);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
