package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.*;
import com.chabbay.dataobjects.repositories.CountryRepository;
import com.chabbay.dataobjects.repositories.CountryTimezoneRepository;
import com.chabbay.dataobjects.repositories.TimezoneRepository;
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
    private final CountryRepository countryRepository;
    private final TimezoneRepository timezoneRepository;
    private final CountryTimezoneModelAssembler assembler;
    private final String PATH = "/country-timezone-mappings";

    public CountryTimezoneController(CountryTimezoneRepository repository, CountryTimezoneModelAssembler assembler,
                                     CountryRepository countryRepository, TimezoneRepository timezoneRepository) {
        this.repository = repository;
        this.assembler = assembler;
        this.countryRepository = countryRepository;
        this.timezoneRepository = timezoneRepository;
    }

    /**
     * select all
     *
     * @Produces 200
     */
    @GetMapping(PATH)
    CollectionModel<EntityModel<CountryTimezone>> selectAll() {
        List<EntityModel<CountryTimezone>> list = repository.findAll().stream().map(assembler::toModel).toList();
        return assembler.toCollection(list);
    }

    /**
     * select
     *
     * @Produces 200
     * @Produces 404 if not found
     */
    @GetMapping(PATH + "/{id}")
    EntityModel<CountryTimezone> select(@PathVariable Long id) {
        CountryTimezone value = repository.findById(id).orElseThrow(() ->
                new DataNotFoundException(CountryTimezone.class, id));
        return assembler.toModel(value);
    }

    /**
     * insert
     *
     * @Produces 201
     * @Produces 404 if id not found
     */
    @PostMapping(PATH)
    ResponseEntity<?> insert(@RequestBody CountryTimezone value) {
        checkForeignKeys(value);
        EntityModel<CountryTimezone> entityModel = assembler.toModel(repository.save(value));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    /**
     * update
     *
     * @Produces 201
     * @Produces 404 if id not found
     */
    @PutMapping(PATH + "/{id}")
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody CountryTimezone value) {
        checkForeignKeys(value);
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

    /**
     * delete
     *
     * @Produces 200
     * @Produces 404 if not found
     */
    @DeleteMapping(PATH + "/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        repository.findById(id).orElseThrow(() ->
                new DataNotFoundException(CountryTimezone.class, id));

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * checks if the referenced Objects exist
     *
     * @throws DataNotFoundException if id does not exist, produces 404
     */
    void checkForeignKeys(CountryTimezone value) throws DataNotFoundException {
        countryRepository.findById(value.getCountryId()).orElseThrow(() ->
                new DataNotFoundException(Country.class, value.getCountryId()));
        timezoneRepository.findById(value.getTimezoneId()).orElseThrow(() ->
                new DataNotFoundException(Timezone.class, value.getTimezoneId()));
    }
}