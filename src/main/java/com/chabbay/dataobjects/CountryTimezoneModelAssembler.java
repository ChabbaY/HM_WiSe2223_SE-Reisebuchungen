package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.CountryTimezone;
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
public class CountryTimezoneModelAssembler implements RepresentationModelAssembler<CountryTimezone, EntityModel<CountryTimezone>> {
    @Override
    public @NotNull EntityModel<CountryTimezone> toModel(@NotNull CountryTimezone entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(CountryTimezoneController.class).select(entity.getId())).withSelfRel(),
                linkTo(methodOn(CountryTimezoneController.class).selectAll()).withRel("country-timezone-mappings"));
    }

    public CollectionModel<EntityModel<CountryTimezone>> toCollection(
            List<EntityModel<CountryTimezone>> entities) {
        return CollectionModel.of(entities,
                linkTo(methodOn(CountryTimezoneController.class).selectAll()).withSelfRel());
    }
}