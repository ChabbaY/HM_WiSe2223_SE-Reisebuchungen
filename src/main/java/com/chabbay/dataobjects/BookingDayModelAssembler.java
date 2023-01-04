package com.chabbay.dataobjects;

import com.chabbay.dataobjects.objects.BookingDay;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * decorates a booking day with links
 *
 * @author Linus Englert
 */
@Component
public class BookingDayModelAssembler implements RepresentationModelAssembler<BookingDay, EntityModel<BookingDay>> {
    @Override
    public @NotNull EntityModel<BookingDay> toModel(@NotNull BookingDay entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(BookingDayController.class).select(entity.getId())).withSelfRel(),
                linkTo(methodOn(BookingDayController.class).selectAll()).withRel("countries"));
    }

    public CollectionModel<EntityModel<BookingDay>> toCollection(
            List<EntityModel<BookingDay>> entities) {
        return CollectionModel.of(entities,
                linkTo(methodOn(BookingDayController.class).selectAll()).withSelfRel());
    }
}