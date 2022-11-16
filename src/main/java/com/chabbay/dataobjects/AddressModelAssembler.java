package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.Address;
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
public class AddressModelAssembler implements RepresentationModelAssembler<Address, EntityModel<Address>> {
    @Override
    public EntityModel<Address> toModel(Address entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(AddressController.class).select(entity.getId())).withSelfRel(),
                linkTo(methodOn(AddressController.class).selectAll()).withRel("addresses"));
    }

    public CollectionModel<EntityModel<Address>> toCollection(List<EntityModel<Address>> entities) {
        return CollectionModel.of(entities, linkTo(methodOn(AddressController.class).selectAll()).withSelfRel());
    }
}