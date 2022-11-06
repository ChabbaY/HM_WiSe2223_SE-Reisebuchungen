package com.chabbay.data;

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
public class HotelModelAssembler implements RepresentationModelAssembler<Hotel, EntityModel<Hotel>> {
    @Override
    public EntityModel<Hotel> toModel(Hotel entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(HotelController.class).select(entity.getId())).withSelfRel(),
                linkTo(methodOn(HotelController.class).selectAll()).withRel("hotels"));
    }
}