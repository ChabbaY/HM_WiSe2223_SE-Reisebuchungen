package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.Timezone;
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
public class TimezoneModelAssembler implements RepresentationModelAssembler<Timezone, EntityModel<Timezone>> {
    @Override
    public @NotNull EntityModel<Timezone> toModel(@NotNull Timezone entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(TimezoneController.class).select(entity.getId())).withSelfRel(),
                linkTo(methodOn(TimezoneController.class).selectAll()).withRel("timezones"));
    }

    public CollectionModel<EntityModel<Timezone>> toCollection(
            List<EntityModel<Timezone>> entities) {
        return CollectionModel.of(entities,
                linkTo(methodOn(TimezoneController.class).selectAll()).withSelfRel());
    }
}