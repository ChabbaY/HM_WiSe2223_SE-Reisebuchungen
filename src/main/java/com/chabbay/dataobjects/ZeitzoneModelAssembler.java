package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.Zeitzone;
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
public class ZeitzoneModelAssembler implements RepresentationModelAssembler<Zeitzone, EntityModel<Zeitzone>> {
    @Override
    public EntityModel<Zeitzone> toModel(Zeitzone entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ZeitzoneController.class).select(entity.getId())).withSelfRel(),
                linkTo(methodOn(ZeitzoneController.class).selectAll()).withRel("zeitzonen"));
    }

    public CollectionModel<EntityModel<Zeitzone>> toCollection(List<EntityModel<Zeitzone>> entities) {
        return CollectionModel.of(entities, linkTo(methodOn(ZeitzoneController.class).selectAll()).withSelfRel());
    }
}