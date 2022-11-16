package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.Anschrift;
import com.chabbay.dataobjects.repositories.AnschriftRepository;
import com.chabbay.errorhandling.exceptions.AnschriftNotFoundException;
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
 * controller for the anschrift entity that defines endpoints
 *
 * @author Linus Englert
 */
@RestController
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.chabbay"})
@Api(tags="Anschrift")
public class AnschriftController {
    private final AnschriftRepository repository;
    private final AnschriftModelAssembler assembler;
    private final String PATH = "/anschriften";

    public AnschriftController(AnschriftRepository repository, AnschriftModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    //select all
    @GetMapping(PATH)
    CollectionModel<EntityModel<Anschrift>> selectAll() {
        List<EntityModel<Anschrift>> list = repository.findAll().stream().map(assembler::toModel).toList();
        return assembler.toCollection(list);
    }

    //select
    @GetMapping(PATH + "/{id}")
    EntityModel<Anschrift> select(@PathVariable Long id) {
        Anschrift value = repository.findById(id).orElseThrow(() -> new AnschriftNotFoundException(id));
        return assembler.toModel(value);
    }

    //insert
    @PostMapping(PATH)
    ResponseEntity<?> insert(@RequestBody Anschrift value) {
        EntityModel<Anschrift> entityModel = assembler.toModel(repository.save(value));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    //update
    @PutMapping(PATH + "/{id}")
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody Anschrift value) {
        Anschrift updated =  repository.findById(id).map(v -> {
            v.setAnrede(value.getAnrede());
            v.setTelefon(value.getTelefon());
            v.setEmail(value.getEmail());
            return repository.save(value);
        }).orElseGet(() -> {
            value.setId(id);
            return repository.save(value);
        });

        EntityModel<Anschrift> entityModel = assembler.toModel(updated);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    //delete
    @DeleteMapping(PATH + "/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}