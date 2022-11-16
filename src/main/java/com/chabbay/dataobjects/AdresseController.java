package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.Adresse;
import com.chabbay.dataobjects.repositories.AdresseRepository;
import com.chabbay.errorhandling.exceptions.AdresseNotFoundException;
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
 * controller for the adresse entity that defines endpoints
 *
 * @author Linus Englert
 */
@RestController
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.chabbay"})
@Api(tags="Adresse")
public class AdresseController {
    private final AdresseRepository repository;
    private final AdresseModelAssembler assembler;
    private final String PATH = "/adressen";

    public AdresseController(AdresseRepository repository, AdresseModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    //select all
    @GetMapping(PATH)
    CollectionModel<EntityModel<Adresse>> selectAll() {
        List<EntityModel<Adresse>> list = repository.findAll().stream().map(assembler::toModel).toList();
        return assembler.toCollection(list);
    }

    //select
    @GetMapping(PATH + "/{id}")
    EntityModel<Adresse> select(@PathVariable Long id) {
        Adresse value = repository.findById(id).orElseThrow(() -> new AdresseNotFoundException(id));
        return assembler.toModel(value);
    }

    //insert
    @PostMapping(PATH)
    ResponseEntity<?> insert(@RequestBody Adresse value) {
        EntityModel<Adresse> entityModel = assembler.toModel(repository.save(value));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    //update
    @PutMapping(PATH + "/{id}")
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody Adresse value) {
        Adresse updated =  repository.findById(id).map(v -> {
            v.setStrasse(value.getStrasse());
            v.setHausnummer(value.getHausnummer());
            v.setPostleitzahl(value.getPostleitzahl());
            v.setOrt(value.getOrt());
            v.setAdresszusatz(value.getAdresszusatz());
            return repository.save(value);
        }).orElseGet(() -> {
            value.setId(id);
            return repository.save(value);
        });

        EntityModel<Adresse> entityModel = assembler.toModel(updated);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    //delete
    @DeleteMapping(PATH + "/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}