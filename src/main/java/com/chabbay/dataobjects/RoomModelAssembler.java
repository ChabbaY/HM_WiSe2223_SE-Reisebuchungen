package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.Room;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * decorates a room with links
 *
 * @author Linus Englert
 */
@Component
public class RoomModelAssembler implements RepresentationModelAssembler<Room, EntityModel<Room>> {
    @Override
    public @NotNull EntityModel<Room> toModel(@NotNull Room entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(RoomController.class).select(entity.getId())).withSelfRel(),
                linkTo(methodOn(RoomController.class).selectAll()).withRel("rooms"));
    }

    public CollectionModel<EntityModel<Room>> toCollection(
            List<EntityModel<Room>> entities) {
        return CollectionModel.of(entities,
                linkTo(methodOn(RoomController.class).selectAll()).withSelfRel());
    }
}