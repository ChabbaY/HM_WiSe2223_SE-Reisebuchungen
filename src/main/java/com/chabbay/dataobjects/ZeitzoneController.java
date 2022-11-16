package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.Zeitzone;
import com.chabbay.dataobjects.repositories.ZeitzoneRepository;
import com.chabbay.errorhandling.exceptions.ZeitzoneNotFoundException;
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
 * controller for the hotel entity that defines endpoints
 *
 * @author Linus Englert
 */
@RestController
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.chabbay"})
@Api(tags="Zeitzone")
public class ZeitzoneController {
    private final ZeitzoneRepository repository;
    private final ZeitzoneModelAssembler assembler;
    private final String PATH = "/zeitzonen";

    public ZeitzoneController(ZeitzoneRepository repository, ZeitzoneModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    //select all
    @GetMapping(PATH)
    CollectionModel<EntityModel<Zeitzone>> selectAll() {
        List<EntityModel<Zeitzone>> list = repository.findAll().stream().map(assembler::toModel).toList();
        return assembler.toCollection(list);
    }

    //select
    @GetMapping(PATH + "/{id}")
    EntityModel<Zeitzone> select(@PathVariable Long id) {
        Zeitzone value = repository.findById(id).orElseThrow(() -> new ZeitzoneNotFoundException(id));
        return assembler.toModel(value);
    }

    //insert
    @PostMapping(PATH)
    ResponseEntity<?> insert(@RequestBody Zeitzone value) {
        EntityModel<Zeitzone> entityModel = assembler.toModel(repository.save(value));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    //update
    @PutMapping(PATH + "/{id}")
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody Zeitzone value) {
        Zeitzone updated =  repository.findById(id).map(v -> {
            v.setBezeichnung(value.getBezeichnung());
            v.setDiffUtc(value.getDiffUtc());
            return repository.save(value);
        }).orElseGet(() -> {
            value.setId(id);
            return repository.save(value);
        });

        EntityModel<Zeitzone> entityModel = assembler.toModel(updated);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    //delete
    @DeleteMapping(PATH + "/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}