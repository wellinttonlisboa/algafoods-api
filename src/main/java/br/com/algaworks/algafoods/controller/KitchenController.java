package br.com.algaworks.algafoods.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.algaworks.algafoods.domain.Kitchen;
import br.com.algaworks.algafoods.service.KitchenService;

@RestController
@RequestMapping("kitchens")
public class KitchenController {

    @Autowired
    private KitchenService kitchenService;

    @GetMapping
    //    @Operation(summary = "List all Kitchens paginated",
    //            description = "The default size is 20, use the parameter size to change the default value", tags = { "Kitchen" })
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

    //    @GetMapping(path = "/{id}")
    //    public ResponseEntity<Kitchen> findById(@PathVariable long id) {
    //        return ResponseEntity.ok(kitchenService.findByIdOrThrowBadRequestException(id));
    //    }

    //    @GetMapping(path = "by-id/{id}")
    //    public ResponseEntity<Kitchen> findByIdAuthenticationPrincipal(@PathVariable long id, @AuthenticationPrincipal UserDetails userDetails) {
    //        log.info(userDetails);
    //        return ResponseEntity.ok(kitchenService.findByIdOrThrowBadRequestException(id));
    //    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Kitchen>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(kitchenService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Kitchen> save(@RequestBody Kitchen KitchenPostRequestBody) {
        return new ResponseEntity<>(kitchenService.save(KitchenPostRequestBody), HttpStatus.CREATED);
    }

    //    @PostMapping
    //    public ResponseEntity<Kitchen> save(@RequestBody @Valid KitchenPostRequestBody KitchenPostRequestBody) {
    //        return new ResponseEntity<>(kitchenService.save(KitchenPostRequestBody), HttpStatus.CREATED);
    //    }

    //    @DeleteMapping(path = "/admin/{id}")
    //    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Successful Operation"),
    //            @ApiResponse(responseCode = "400", description = "When Kitchen Does Not Exist in The Database") })
    //    public ResponseEntity<Void> delete(@PathVariable long id) {
    //        kitchenService.delete(id);
    //        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //    }

    //    @PutMapping
    //    public ResponseEntity<Void> replace(@RequestBody KitchenPutRequestBody KitchenPutRequestBody) {
    //        kitchenService.replace(KitchenPutRequestBody);
    //        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //    }
}
