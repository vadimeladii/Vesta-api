package com.vesta.controller.impl;

import com.vesta.controller.SwaggerController;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SwaggerControllerImpl implements SwaggerController {

    public String greeting() {
        return "Some text";
    }
}
