package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.Hotel;
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
public class HotelModelAssembler implements RepresentationModelAssembler<Hotel, EntityModel<Hotel>> {
    @Override
    public EntityModel<Hotel> toModel(Hotel entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(HotelController.class).select(entity.getId())).withSelfRel(),
                linkTo(methodOn(HotelController.class).selectAll()).withRel("hotels"));
    }

    public CollectionModel<EntityModel<Hotel>> toCollection(List<EntityModel<Hotel>> entities) {
        return CollectionModel.of(entities,
                linkTo(methodOn(HotelController.class).selectAll()).withSelfRel(),
                linkTo(methodOn(RootController.class).root()).withRel("root"));
    }
}