package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.RoomType;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * decorates a hotel with links
 *
 * @author Linus Englert
 */
@Component
public class RoomTypeModelAssembler implements RepresentationModelAssembler<RoomType, EntityModel<RoomType>> {
    @Override
    public @NotNull EntityModel<RoomType> toModel(@NotNull RoomType entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(RoomTypeController.class).select(entity.getId())).withSelfRel(),
                linkTo(methodOn(RoomTypeController.class).selectAll()).withRel("room types"));
    }

    public CollectionModel<EntityModel<RoomType>> toCollection(
            List<EntityModel<RoomType>> entities) {
        return CollectionModel.of(entities,
                linkTo(methodOn(RoomTypeController.class).selectAll()).withSelfRel());
    }
}