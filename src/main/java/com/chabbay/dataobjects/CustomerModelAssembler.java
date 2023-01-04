package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.Customer;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * decorates a customer with links
 *
 * @author Linus Englert
 */
@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<Customer, EntityModel<Customer>> {
    @Override
    public @NotNull EntityModel<Customer> toModel(@NotNull Customer entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(CustomerController.class).select(entity.getId())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).selectAll()).withRel("customers"));
    }

    public CollectionModel<EntityModel<Customer>> toCollection(
            List<EntityModel<Customer>> entities) {
        return CollectionModel.of(entities,
                linkTo(methodOn(CustomerController.class).selectAll()).withSelfRel());
    }
}