package com.chabbay.dataobjects;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RootController {
    private final String PATH = "/";

    @GetMapping(PATH)
    @ResponseBody
    public EntityModel<?> root() {
        return EntityModel.of(
                linkTo(methodOn(RootController.class).root()).withSelfRel(),
                linkTo(methodOn(AdresseController.class).selectAll()).withRel("adressen"),
                linkTo(methodOn(AnschriftController.class).selectAll()).withRel("anschriften"),
                linkTo(methodOn(HotelController.class).selectAll()).withRel("hotels"),
                linkTo(methodOn(LandController.class).selectAll()).withRel("laender"),
                linkTo(methodOn(ZeitzoneController.class).selectAll()).withRel("zeitzonen"));
    }
}
