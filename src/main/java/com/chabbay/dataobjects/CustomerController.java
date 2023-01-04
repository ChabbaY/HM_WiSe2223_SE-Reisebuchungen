package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.Customer;
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
    private final CustomerModelAssembler assembler;
    private final String PATH = "/customers";

    public CustomerController(CustomerRepository repository, CustomerModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    //select all
    @GetMapping(PATH)
    CollectionModel<EntityModel<Customer>> selectAll() {
        List<EntityModel<Customer>> list = repository.findAll().stream().map(assembler::toModel).toList();
        return assembler.toCollection(list);
    }

    //select
    @GetMapping(PATH + "/{id}")
    EntityModel<Customer> select(@PathVariable Long id) {
        Customer value = repository.findById(id).orElseThrow(() ->
                new DataNotFoundException(Customer.class, id));
        return assembler.toModel(value);
    }

    //insert
    @PostMapping(PATH)
    ResponseEntity<?> insert(@RequestBody Customer value) {
        EntityModel<Customer> entityModel = assembler.toModel(repository.save(value));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    //update
    @PutMapping(PATH + "/{id}")
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody Customer value) {
        Customer updated =  repository.findById(id).map(v -> {
            v.setFirstname(value.getFirstname());
            v.setLastname(value.getLastname());
            v.setBirthdate(value.getBirthdate());
            v.setAddressinformationId(value.getAddressinformationId());
            return repository.save(value);
        }).orElseGet(() -> {
            value.setId(id);
            return repository.save(value);
        });

        EntityModel<Customer> entityModel = assembler.toModel(updated);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    //delete
    @DeleteMapping(PATH + "/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}