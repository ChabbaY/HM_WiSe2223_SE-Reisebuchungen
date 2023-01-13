package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.*;
import com.chabbay.dataobjects.repositories.AddressInformationRepository;
import com.chabbay.dataobjects.repositories.CustomerRepository;
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
 * controller for the Customer entity that defines endpoints
 *
 * @author Linus Englert
 */
@RestController
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.chabbay"})
@Api(tags="Customer")
public class CustomerController {
    private final CustomerRepository repository;
    private final AddressInformationRepository addressInformationRepository;
    private final CustomerModelAssembler assembler;
    private final String PATH = "/customers";

    public CustomerController(CustomerRepository repository, CustomerModelAssembler assembler,
                              AddressInformationRepository addressInformationRepository) {
        this.repository = repository;
        this.assembler = assembler;
        this.addressInformationRepository = addressInformationRepository;
    }

    /**
     * select all
     *
     * @Produces 200
     */
    @GetMapping(PATH)
    CollectionModel<EntityModel<Customer>> selectAll() {
        List<EntityModel<Customer>> list = repository.findAll().stream().map(assembler::toModel).toList();
        return assembler.toCollection(list);
    }

    /**
     * select
     *
     * @Produces 200
     * @Produces 404 if not found
     */
    @GetMapping(PATH + "/{id}")
    EntityModel<Customer> select(@PathVariable Long id) {
        Customer value = repository.findById(id).orElseThrow(() ->
                new DataNotFoundException(Customer.class, id));
        return assembler.toModel(value);
    }

    /**
     * insert
     *
     * @Produces 201
     * @Produces 404 if id not found
     */
    @PostMapping(PATH)
    ResponseEntity<?> insert(@RequestBody Customer value) {
        checkForeignKeys(value);
        EntityModel<Customer> entityModel = assembler.toModel(repository.save(value));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    /**
     * update
     *
     * @Produces 201
     * @Produces 404 if id not found
     */
    @PutMapping(PATH + "/{id}")
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody Customer value) {
        checkForeignKeys(value);
        Customer updated =  repository.findById(id).map(v -> {
            v.setFirstname(value.getFirstname());
            v.setLastname(value.getLastname());
            v.setBirthdate(value.getBirthdate());
            v.setAddressInformationId(value.getAddressInformationId());
            return repository.save(value);
        }).orElseGet(() -> {
            value.setId(id);
            return repository.save(value);
        });

        EntityModel<Customer> entityModel = assembler.toModel(updated);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    /**
     * delete
     *
     * @Produces 200
     * @Produces 404 if not found
     */
    @DeleteMapping(PATH + "/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        repository.findById(id).orElseThrow(() ->
                new DataNotFoundException(Customer.class, id));

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * checks if the referenced Objects exist
     *
     * @throws DataNotFoundException if id does not exist, produces 404
     */
    void checkForeignKeys(Customer value) throws DataNotFoundException {
        addressInformationRepository.findById(value.getAddressInformationId()).orElseThrow(() ->
                new DataNotFoundException(AddressInformation.class, value.getAddressInformationId()));
    }
}