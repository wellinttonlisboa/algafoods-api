package br.com.algaworks.algafoods.controller;

import java.util.List;
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
    public ResponseEntity<Kitchen> findById(@PathVariable long id) {
        return ResponseEntity.ok(kitchenService.findById(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Kitchen>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(kitchenService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Kitchen> save(@RequestBody KitchenPostRequestBody kitchenPostRequestBody) {
        return new ResponseEntity<>(kitchenService.save(kitchenPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/admin/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        kitchenService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody KitchenPutRequestBody KitchenPutRequestBody) {
        kitchenService.replace(KitchenPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
