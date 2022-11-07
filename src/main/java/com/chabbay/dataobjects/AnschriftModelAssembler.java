package com.chabbay.dataobjects;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * decorates a hotel with links
 *
 * @author Linus Englert
 */
@Component
public class AnschriftModelAssembler implements RepresentationModelAssembler<Anschrift, EntityModel<Anschrift>> {
    @Override
    public EntityModel<Anschrift> toModel(Anschrift entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(AnschriftController.class).select(entity.getId())).withSelfRel(),
                linkTo(methodOn(AnschriftController.class).selectAll()).withRel("anschriften"));
    }
}