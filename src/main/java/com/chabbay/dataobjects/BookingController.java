package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.Booking;
import com.chabbay.dataobjects.repositories.BookingRepository;
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
 * controller for the Booking entity that defines endpoints
 *
 * @author Linus Englert
 */
@RestController
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.chabbay"})
@Api(tags="Booking")
public class BookingController {
    private final BookingRepository repository;
    private final BookingModelAssembler assembler;
    private final String PATH = "/bookings";

    public BookingController(BookingRepository repository, BookingModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    //select all
    @GetMapping(PATH)
    CollectionModel<EntityModel<Booking>> selectAll() {
        List<EntityModel<Booking>> list = repository.findAll().stream().map(assembler::toModel).toList();
        return assembler.toCollection(list);
    }

    //select
    @GetMapping(PATH + "/{id}")
    EntityModel<Booking> select(@PathVariable Long id) {
        Booking value = repository.findById(id).orElseThrow(() ->
                new DataNotFoundException(Booking.class, id));
        return assembler.toModel(value);
    }

    //insert
    @PostMapping(PATH)
    ResponseEntity<?> insert(@RequestBody Booking value) {
        EntityModel<Booking> entityModel = assembler.toModel(repository.save(value));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    //update
    @PutMapping(PATH + "/{id}")
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody Booking value) {
        Booking updated =  repository.findById(id).map(v -> {
            v.setDate(value.getDate());
            v.setStatus(value.getStatus());
            v.setCustomerId(value.getCustomerId());
            return repository.save(value);
        }).orElseGet(() -> {
            value.setId(id);
            return repository.save(value);
        });

        EntityModel<Booking> entityModel = assembler.toModel(updated);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    //delete
    @DeleteMapping(PATH + "/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}