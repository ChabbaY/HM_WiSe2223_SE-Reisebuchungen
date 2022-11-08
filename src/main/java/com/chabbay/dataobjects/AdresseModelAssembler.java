package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.Adresse;
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
public class AdresseModelAssembler implements RepresentationModelAssembler<Adresse, EntityModel<Adresse>> {
    @Override
    public EntityModel<Adresse> toModel(Adresse entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(AdresseController.class).select(entity.getId())).withSelfRel(),
                linkTo(methodOn(AdresseController.class).selectAll()).withRel("adressen"));
    }

    public CollectionModel<EntityModel<Adresse>> toCollection(List<EntityModel<Adresse>> entities) {
        return CollectionModel.of(entities,
                linkTo(methodOn(AdresseController.class).selectAll()).withSelfRel(),
                linkTo(methodOn(RootController.class).root()).withRel("root"));
    }
}