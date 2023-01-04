package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.Address;
import com.chabbay.dataobjects.repositories.AddressRepository;
import com.chabbay.errorhandling.exceptions.DataNotFoundException;
import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * controller for the Address entity that defines endpoints
 *
 * @author Linus Englert
 */
@RestController
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.chabbay"})
@Api(tags="Address")
public class AddressController {
    private final AddressRepository repository;
    private final AddressModelAssembler assembler;
    private final String PATH = "/addresses";

    public AddressController(AddressRepository repository, AddressModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    //select all
    @GetMapping(PATH)
    CollectionModel<EntityModel<Address>> selectAll() {
        List<EntityModel<Address>> list = repository.findAll().stream().map(assembler::toModel).toList();
        return assembler.toCollection(list);
    }

    //select
    @GetMapping(PATH + "/{id}")
    EntityModel<Address> select(@PathVariable Long id) {
        Address value = repository.findById(id).orElseThrow(() ->
                new DataNotFoundException(Address.class, id));
        return assembler.toModel(value);
    }

    //insert
    @PostMapping(PATH)
    ResponseEntity<?> insert(@RequestBody Address value) {
        EntityModel<Address> entityModel = assembler.toModel(repository.save(value));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    //update
    @PutMapping(PATH + "/{id}")
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody Address value) {
        Address updated =  repository.findById(id).map(v -> {
            v.setStreet(value.getStreet());
            v.setHouseNumber(value.getHouseNumber());
            v.setPostcode(value.getPostcode());
            v.setCity(value.getCity());
            v.setAddressSupplement(value.getAddressSupplement());
            v.setAddressInformationId(value.getAddressInformationId());
            v.setCountryId(value.getCountryId());
            v.setTimezoneId(value.getTimezoneId());
            return repository.save(value);
        }).orElseGet(() -> {
            value.setId(id);
            return repository.save(value);
        });

        EntityModel<Address> entityModel = assembler.toModel(updated);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    //delete
    @DeleteMapping(PATH + "/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}