package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.Address;
import com.chabbay.dataobjects.objects.AddressInformation;
import com.chabbay.dataobjects.repositories.AddressInformationRepository;
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
 * controller for the AddressInformation entity that defines endpoints
 *
 * @author Linus Englert
 */
@RestController
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.chabbay"})
@Api(tags="AddressInformation")
public class AddressInformationController {
    private final AddressInformationRepository repository;
    private final AddressInformationModelAssembler assembler;
    private final AddressRepository addressRepository;
    private final String PATH = "/addressinformation";

    public AddressInformationController(AddressInformationRepository repository, AddressInformationModelAssembler assembler, AddressRepository addressRepository) {
        this.repository = repository;
        this.assembler = assembler;
        this.addressRepository = addressRepository;
    }

    //select all
    @GetMapping(PATH)
    CollectionModel<EntityModel<AddressInformation>> selectAll() {
        List<EntityModel<AddressInformation>> list = repository.findAll().stream().map(assembler::toModel).toList();
        return assembler.toCollection(list);
    }

    //select
    @GetMapping(PATH + "/{id}")
    EntityModel<AddressInformation> select(@PathVariable Long id) {
        AddressInformation value = repository.findById(id).orElseThrow(() ->
                new DataNotFoundException(AddressInformation.class, id));
        return assembler.toModel(value);
    }

    //insert
    @PostMapping(PATH)
    ResponseEntity<?> insert(@RequestBody AddressInformation value) {
        EntityModel<AddressInformation> entityModel = assembler.toModel(repository.save(value));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    //update
    @PutMapping(PATH + "/{id}")
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody AddressInformation value) {
        AddressInformation updated =  repository.findById(id).map(v -> {
            v.setTitle(value.getTitle());
            v.setTelephone(value.getTelephone());
            v.setEmail(value.getEmail());
            return repository.save(value);
        }).orElseGet(() -> {
            value.setId(id);
            return repository.save(value);
        });

        EntityModel<AddressInformation> entityModel = assembler.toModel(updated);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    //delete
    @DeleteMapping(PATH + "/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);

        //cascading delete of referenced Address entities
        List<Address> addresses = addressRepository.findByAddressInformationId(id);
        for (Address address : addresses ) {
            addressRepository.deleteById(address.getId());
        }

        return ResponseEntity.noContent().build();
    }
}