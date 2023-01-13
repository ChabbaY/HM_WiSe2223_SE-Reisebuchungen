package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.Address;
import com.chabbay.dataobjects.objects.AddressInformation;
import com.chabbay.dataobjects.objects.Country;
import com.chabbay.dataobjects.objects.Timezone;
import com.chabbay.dataobjects.repositories.*;
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
    private final AddressInformationRepository addressInformationRepository;
    private final CountryRepository countryRepository;
    private final TimezoneRepository timezoneRepository;
    private final AddressModelAssembler assembler;
    private final String PATH = "/addresses";

    public AddressController(AddressRepository repository, AddressModelAssembler assembler,
                             AddressInformationRepository addressInformationRepository,
                             CountryRepository countryRepository, TimezoneRepository timezoneRepository) {
        this.repository = repository;
        this.assembler = assembler;
        this.addressInformationRepository = addressInformationRepository;
        this.countryRepository = countryRepository;
        this.timezoneRepository = timezoneRepository;
    }

    /**
     * select all
     *
     * @Produces 200
     */
    @GetMapping(PATH)
    CollectionModel<EntityModel<Address>> selectAll() {
        List<EntityModel<Address>> list = repository.findAll().stream().map(assembler::toModel).toList();
        return assembler.toCollection(list);
    }

    /**
     * select
     *
     * @Produces 200
     * @Produces 404 if not found
     */
    @GetMapping(PATH + "/{id}")
    EntityModel<Address> select(@PathVariable Long id) {
        Address value = repository.findById(id).orElseThrow(() ->
                new DataNotFoundException(Address.class, id));
        return assembler.toModel(value);
    }

    /**
     * insert
     *
     * @Produces 201
     * @Produces 404 if id not found
     */
    @PostMapping(PATH)
    ResponseEntity<?> insert(@RequestBody Address value) {
        //check foreign keys
        addressInformationRepository.findById(value.getAddressInformationId()).orElseThrow(() ->
                new DataNotFoundException(AddressInformation.class, value.getAddressInformationId()));
        countryRepository.findById(value.getCountryId()).orElseThrow(() ->
                new DataNotFoundException(Country.class, value.getCountryId()));
        timezoneRepository.findById(value.getTimezoneId()).orElseThrow(() ->
                new DataNotFoundException(Timezone.class, value.getTimezoneId()));

        EntityModel<Address> entityModel = assembler.toModel(repository.save(value));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    /**
     * update
     *
     * @Produces 201
     */
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

    /**
     * delete
     *
     * @Produces 200
     */
    @DeleteMapping(PATH + "/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}