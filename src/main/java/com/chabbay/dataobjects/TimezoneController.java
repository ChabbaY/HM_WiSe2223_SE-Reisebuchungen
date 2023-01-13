package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.Address;
import com.chabbay.dataobjects.objects.Country;
import com.chabbay.dataobjects.objects.CountryTimezone;
import com.chabbay.dataobjects.objects.Timezone;
import com.chabbay.dataobjects.repositories.AddressRepository;
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
    private final AddressRepository addressRepository;
    private final CountryTimezoneRepository countryTimezoneRepository;
    private final TimezoneModelAssembler assembler;
    private final String PATH = "/timezones";

    public TimezoneController(TimezoneRepository repository, TimezoneModelAssembler assembler,
                              AddressRepository addressRepository,
                              CountryTimezoneRepository countryTimezoneRepository) {
        this.repository = repository;
        this.assembler = assembler;
        this.addressRepository = addressRepository;
        this.countryTimezoneRepository = countryTimezoneRepository;
    }

    /**
     * select all
     *
     * @Produces 200
     */
    @GetMapping(PATH)
    CollectionModel<EntityModel<Timezone>> selectAll() {
        List<EntityModel<Timezone>> list = repository.findAll().stream().map(assembler::toModel).toList();
        return assembler.toCollection(list);
    }

    /**
     * select
     *
     * @Produces 200
     * @Produces 404 if not found
     */
    @GetMapping(PATH + "/{id}")
    EntityModel<Timezone> select(@PathVariable Long id) {
        Timezone value = repository.findById(id).orElseThrow(() ->
                new DataNotFoundException(Timezone.class, id));
        return assembler.toModel(value);
    }

    /**
     * insert
     *
     * @Produces 201
     */
    @PostMapping(PATH)
    ResponseEntity<?> insert(@RequestBody Timezone value) {
        EntityModel<Timezone> entityModel = assembler.toModel(repository.save(value));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    /**
     * update
     *
     * @Produces 201
     */
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

    /**
     * delete
     * block if referenced by Address or CountryTimezone
     *
     * @Produces 200
     * @Produces 404 if not found
     * @Produces 409 if referenced
     */
    @DeleteMapping(PATH + "/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        List<Address> addresses = addressRepository.findByTimezoneId(id);
        List<CountryTimezone> countryTimezones = countryTimezoneRepository.findByTimezoneId(id);
        if ((addresses.size() > 0) || (countryTimezones.size() > 0)) {
            return ResponseEntity.status(409).build();
        }

        repository.findById(id).orElseThrow(() ->
                new DataNotFoundException(Timezone.class, id));

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}