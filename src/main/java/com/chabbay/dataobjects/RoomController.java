package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.Room;
import com.chabbay.dataobjects.repositories.RoomRepository;
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
 * controller for the Room entity that defines endpoints
 *
 * @author Linus Englert
 */
@RestController
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.chabbay"})
@Api(tags="Room")
public class RoomController {
    private final RoomRepository repository;
    private final RoomModelAssembler assembler;
    private final String PATH = "/rooms";

    public RoomController(RoomRepository repository, RoomModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    //select all
    @GetMapping(PATH)
    CollectionModel<EntityModel<Room>> selectAll() {
        List<EntityModel<Room>> list = repository.findAll().stream().map(assembler::toModel).toList();
        return assembler.toCollection(list);
    }

    //select
    @GetMapping(PATH + "/{id}")
    EntityModel<Room> select(@PathVariable Long id) {
        Room value = repository.findById(id).orElseThrow(() ->
                new DataNotFoundException(Room.class, id));
        return assembler.toModel(value);
    }

    //insert
    @PostMapping(PATH)
    ResponseEntity<?> insert(@RequestBody Room value) {
        EntityModel<Room> entityModel = assembler.toModel(repository.save(value));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    //update
    @PutMapping(PATH + "/{id}")
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody Room value) {
        Room updated =  repository.findById(id).map(v -> {
            v.setHotelId(value.getHotelId());
            v.setRoomTypeId(value.getRoomTypeId());
            return repository.save(value);
        }).orElseGet(() -> {
            value.setId(id);
            return repository.save(value);
        });

        EntityModel<Room> entityModel = assembler.toModel(updated);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    //delete
    @DeleteMapping(PATH + "/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}