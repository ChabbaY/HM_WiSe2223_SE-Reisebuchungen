package com.chabbay.errorhandling;

import io.swagger.annotations.Api;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags="Error")
public class ErrorMapping implements ErrorController {
    @GetMapping("/error")
    @ResponseBody
    public String error() {
        return "❗❗❗No Mapping found!❗❗❗";
    }
}