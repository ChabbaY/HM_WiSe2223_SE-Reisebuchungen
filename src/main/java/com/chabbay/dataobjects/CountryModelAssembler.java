package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.Country;
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
public class CountryModelAssembler implements RepresentationModelAssembler<Country, EntityModel<Country>> {
    @Override
    public @NotNull EntityModel<Country> toModel(@NotNull Country entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(CountryController.class).select(entity.getId())).withSelfRel(),
                linkTo(methodOn(CountryController.class).selectAll()).withRel("countries"));
    }

    public CollectionModel<EntityModel<Country>> toCollection(
            List<EntityModel<Country>> entities) {
        return CollectionModel.of(entities,
                linkTo(methodOn(CountryController.class).selectAll()).withSelfRel());
    }
}