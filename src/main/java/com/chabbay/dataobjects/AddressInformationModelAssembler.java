package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.AddressInformation;
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
public class AddressInformationModelAssembler implements RepresentationModelAssembler<AddressInformation, EntityModel<AddressInformation>> {
    @Override
    public EntityModel<AddressInformation> toModel(AddressInformation entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(AddressInformationController.class).select(entity.getId())).withSelfRel(),
                linkTo(methodOn(AddressInformationController.class).selectAll()).withRel("address information"));
    }

    public CollectionModel<EntityModel<AddressInformation>> toCollection(List<EntityModel<AddressInformation>> entities) {
        return CollectionModel.of(entities, linkTo(methodOn(AddressInformationController.class).selectAll()).withSelfRel());
    }
}