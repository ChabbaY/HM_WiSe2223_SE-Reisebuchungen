package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.Address;
import com.chabbay.dataobjects.objects.Country;
import com.chabbay.dataobjects.objects.CountryTimezone;
import com.chabbay.dataobjects.repositories.AddressRepository;
import com.chabbay.dataobjects.repositories.CountryRepository;
import com.chabbay.dataobjects.repositories.CountryTimezoneRepository;
import com.chabbay.errorhandling.exceptions.DataNotFoundException;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * controller for the Country entity that defines endpoints
 *
 * @author Linus Englert
 */
@RestController
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.chabbay"})
@Api(tags="Country")
public class CountryController {
    private final CountryRepository repository;
    private final AddressRepository addressRepository;
    private final CountryTimezoneRepository countryTimezoneRepository;
    private final CountryModelAssembler assembler;
    private final String PATH = "/countries";

    public CountryController(CountryRepository repository, CountryModelAssembler assembler,
                             AddressRepository addressRepository, CountryTimezoneRepository countryTimezoneRepository) {
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
    CollectionModel<EntityModel<Country>> selectAll() {
        List<EntityModel<Country>> list = repository.findAll().stream().map(assembler::toModel).toList();
        return assembler.toCollection(list);
    }

    /**
     * select
     *
     * @Produces 200
     * @Produces 404 if not found
     */
    @GetMapping(PATH + "/{id}")
    EntityModel<Country> select(@PathVariable Long id) {
        Country value = repository.findById(id).orElseThrow(() ->
                new DataNotFoundException(Country.class, id));
        return assembler.toModel(value);
    }

    /**
     * insert
     *
     * @Produces 201
     */
    @PostMapping(PATH)
    ResponseEntity<?> insert(@RequestBody Country value) {
        EntityModel<Country> entityModel = assembler.toModel(repository.save(value));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    /**
     * update
     *
     * @Produces 201
     */
    @PutMapping(PATH + "/{id}")
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody Country value) {
        Country updated =  repository.findById(id).map(v -> {
            v.setName(value.getName());
            v.setLanguage(value.getLanguage());
            v.setIso2(value.getIso2());
            return repository.save(value);
        }).orElseGet(() -> {
            value.setId(id);
            return repository.save(value);
        });

        EntityModel<Country> entityModel = assembler.toModel(updated);
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
        List<Address> addresses = addressRepository.findByCountryId(id);
        List<CountryTimezone> countryTimezones = countryTimezoneRepository.findByCountryId(id);
        if ((addresses.size() > 0) || (countryTimezones.size() > 0)) {
            return ResponseEntity.status(409).build();
        }

        repository.findById(id).orElseThrow(() ->
                new DataNotFoundException(Country.class, id));

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}