package com.chabbay.data;

import com.chabbay.errorhandling.HotelNotFoundException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * controller for the hotel entity that defines endpoints
 *
 * @author Linus Englert
 */
@RestController
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.chabbay"})
public class HotelController {
    private final HotelRepository repository;
    private final HotelModelAssembler assembler;

    public HotelController(HotelRepository repository, HotelModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    //select all
    @GetMapping("/hotels")
    CollectionModel<EntityModel<Hotel>> selectAll() {
        List<EntityModel<Hotel>> list = repository.findAll().stream().map(assembler::toModel).toList();
        return CollectionModel.of(list, linkTo(methodOn(HotelController.class).selectAll()).withSelfRel());
    }

    //select
    @GetMapping("/hotels/{id}")
    EntityModel<Hotel> select(@PathVariable Long id) {
        Hotel value = repository.findById(id).orElseThrow(() -> new HotelNotFoundException(id));
        return assembler.toModel(value);
    }

    //insert
    @PostMapping("/hotels")
    ResponseEntity<?> insert(@RequestBody Hotel value) {
        EntityModel<Hotel> entityModel = assembler.toModel(repository.save(value));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    //update
    @PutMapping("/hotels/{id}")
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody Hotel value) {
        Hotel updated =  repository.findById(id).map(v -> {
            v.setName(value.getName());
            return repository.save(value);
        }).orElseGet(() -> {
            value.setId(id);
            return repository.save(value);
        });

        EntityModel<Hotel>  entityModel = assembler.toModel(updated);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    //delete
    @DeleteMapping("/hotels/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}