package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.Land;
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
public class LandModelAssembler implements RepresentationModelAssembler<Land, EntityModel<Land>> {
    @Override
    public EntityModel<Land> toModel(Land entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(LandController.class).select(entity.getId())).withSelfRel(),
                linkTo(methodOn(LandController.class).selectAll()).withRel("laender"));
    }

    public CollectionModel<EntityModel<Land>> toCollection(List<EntityModel<Land>> entities) {
        return CollectionModel.of(entities, linkTo(methodOn(LandController.class).selectAll()).withSelfRel());
    }
}