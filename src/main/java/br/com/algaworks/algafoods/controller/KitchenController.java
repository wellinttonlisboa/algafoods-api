package br.com.algaworks.algafoods.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.algaworks.algafoods.domain.Kitchen;
import br.com.algaworks.algafoods.requersts.KitchenPostRequestBody;
import br.com.algaworks.algafoods.requersts.KitchenPutRequestBody;
import br.com.algaworks.algafoods.service.KitchenService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("kitchens")
@RequiredArgsConstructor
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
    public ResponseEntity<Kitchen> save(@PathVariable Long id, @RequestBody Map<String, Object> patchRequestBody) {
        return new ResponseEntity<>(kitchenService.save(id, patchRequestBody), HttpStatus.CREATED);
    }
    
    @DeleteMapping(path = "/admin/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "When Kitchen Does Not Exist in The Database")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
    	kitchenService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody KitchenPutRequestBody KitchenPutRequestBody) {
        kitchenService.replace(KitchenPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
