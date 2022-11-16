package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.Timezone;
import com.chabbay.dataobjects.repositories.TimezoneRepository;
import com.chabbay.errorhandling.exceptions.TimezoneNotFoundException;
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
 * controller for the Timezone entity that defines endpoints
 *
 * @author Linus Englert
 */
@RestController
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.chabbay"})
@Api(tags="Timezone")
public class TimezoneController {
    private final TimezoneRepository repository;
    private final TimezoneModelAssembler assembler;
    private final String PATH = "/timezones";

    public TimezoneController(TimezoneRepository repository, TimezoneModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    //select all
    @GetMapping(PATH)
    CollectionModel<EntityModel<Timezone>> selectAll() {
        List<EntityModel<Timezone>> list = repository.findAll().stream().map(assembler::toModel).toList();
        return assembler.toCollection(list);
    }

    //select
    @GetMapping(PATH + "/{id}")
    EntityModel<Timezone> select(@PathVariable Long id) {
        Timezone value = repository.findById(id).orElseThrow(() -> new TimezoneNotFoundException(id));
        return assembler.toModel(value);
    }

    //insert
    @PostMapping(PATH)
    ResponseEntity<?> insert(@RequestBody Timezone value) {
        EntityModel<Timezone> entityModel = assembler.toModel(repository.save(value));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    //update
    @PutMapping(PATH + "/{id}")
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody Timezone value) {
        Timezone updated =  repository.findById(id).map(v -> {
            v.setDesignation(value.getDesignation());
            v.setDiffUtc(value.getDiffUtc());
            return repository.save(value);
        }).orElseGet(() -> {
            value.setId(id);
            return repository.save(value);
        });

        EntityModel<Timezone> entityModel = assembler.toModel(updated);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    //delete
    @DeleteMapping(PATH + "/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}