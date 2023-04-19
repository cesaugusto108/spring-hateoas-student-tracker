package augusto108.ces.studenttracker.controllers;

import augusto108.ces.studenttracker.assemblers.AddressEntityModelAssembler;
import augusto108.ces.studenttracker.entities.Address;
import augusto108.ces.studenttracker.services.AddressService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    private static final Logger LOGGER = Logger.getLogger(AddressController.class.getName());

    private final AddressService addressService;
    private final AddressEntityModelAssembler assembler;

    public AddressController(AddressService addressService, AddressEntityModelAssembler assembler) {
        this.addressService = addressService;
        this.assembler = assembler;
    }

    private static void log(Map<String, String> requestHeadersMap) {
        requestHeadersMap
                .forEach((k, v) -> LOGGER.info(String.format("Request header: %s = %s", k, v)));
    }

    @GetMapping(value = "/{id}", produces = "application/hal+json")
    public ResponseEntity<EntityModel<Address>> getAddress(
            @PathVariable("id") Long id,
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        try {
            return ResponseEntity.ok(assembler.toModel(addressService.getAddress(id)));
        } catch (NoResultException e) {
            throw new NoResultException(e.getMessage() + ". Id: " + id);
        }
    }

    @GetMapping(value = {"/", ""}, produces = "application/hal+json")
    public ResponseEntity<CollectionModel<EntityModel<Address>>> getAddresses(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "max", defaultValue = "5", required = false) int max,
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        return ResponseEntity
                .ok(assembler.toCollectionModel(addressService.getAddresses(page, max), page, max));
    }

    @PostMapping(value = "/save", produces = "application/hal+json", consumes = "application/json")
    public ResponseEntity<EntityModel<Address>> saveAddress(
            @RequestBody Address address,
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(addressService.saveAddress(address)));
    }

    @PutMapping(value = "/{id}/update", produces = "application/hal+json")
    public ResponseEntity<EntityModel<Address>> updateAddress(
            @PathVariable("id") Long id,
            @RequestBody Address address,
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        Address a;

        try {
            a = addressService.getAddress(id);
        } catch (NoResultException e) {
            throw new NoResultException(e.getMessage() + ". Id: " + id);
        }

        a.setStreet(address.getStreet());
        a.setNumber(address.getNumber());
        a.setCity(address.getCity());

        return ResponseEntity.ok(assembler.toModel(addressService.updateAddress(a)));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteAddress(
            @PathVariable("id") Long id,
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        try {
            addressService.deleteAddress(addressService.getAddress(id));
        } catch (NoResultException e) {
            throw new NoResultException(e.getMessage() + ". Id: " + id);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/search", produces = "application/hal+json")
    public ResponseEntity<CollectionModel<EntityModel<Address>>> searchAddresses(
            @RequestParam String search,
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        return ResponseEntity.ok(assembler.toCollectionModel(addressService.searchAddresses(search)));
    }
}
