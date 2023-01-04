package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.Booking;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * decorates a booking with links
 *
 * @author Linus Englert
 */
@Component
public class BookingModelAssembler implements RepresentationModelAssembler<Booking, EntityModel<Booking>> {
    @Override
    public @NotNull EntityModel<Booking> toModel(@NotNull Booking entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(BookingController.class).select(entity.getId())).withSelfRel(),
                linkTo(methodOn(BookingController.class).selectAll()).withRel("bookings"));
    }

    public CollectionModel<EntityModel<Booking>> toCollection(
            List<EntityModel<Booking>> entities) {
        return CollectionModel.of(entities,
                linkTo(methodOn(BookingController.class).selectAll()).withSelfRel());
    }
}