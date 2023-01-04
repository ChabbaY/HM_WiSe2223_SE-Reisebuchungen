package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.CountryTimezone;
import com.chabbay.dataobjects.repositories.CountryTimezoneRepository;
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
 * controller for the CountryTimezone entity that defines endpoints
 *
 * @author Linus Englert
 */
@RestController
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.chabbay"})
@Api(tags="CountryTimezone")
public class CountryTimezoneController {
    private final CountryTimezoneRepository repository;
    private final CountryTimezoneModelAssembler assembler;
    private final String PATH = "/country-timezone-mappings";

    public CountryTimezoneController(CountryTimezoneRepository repository, CountryTimezoneModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    //select all
    @GetMapping(PATH)
    CollectionModel<EntityModel<CountryTimezone>> selectAll() {
        List<EntityModel<CountryTimezone>> list = repository.findAll().stream().map(assembler::toModel).toList();
        return assembler.toCollection(list);
    }

    //select
    @GetMapping(PATH + "/{id}")
    EntityModel<CountryTimezone> select(@PathVariable Long id) {
        CountryTimezone value = repository.findById(id).orElseThrow(() ->
                new DataNotFoundException(CountryTimezone.class, id));
        return assembler.toModel(value);
    }

    //insert
    @PostMapping(PATH)
    ResponseEntity<?> insert(@RequestBody CountryTimezone value) {
        EntityModel<CountryTimezone> entityModel = assembler.toModel(repository.save(value));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    //update
    @PutMapping(PATH + "/{id}")
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody CountryTimezone value) {
        CountryTimezone updated =  repository.findById(id).map(v -> {
            v.setCountryId(value.getCountryId());
            v.setTimezoneId(value.getTimezoneId());
            return repository.save(value);
        }).orElseGet(() -> {
            value.setId(id);
            return repository.save(value);
        });

        EntityModel<CountryTimezone> entityModel = assembler.toModel(updated);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    //delete
    @DeleteMapping(PATH + "/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}