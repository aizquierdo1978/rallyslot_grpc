package com.alberto.rallyslot.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rallyslot")
public abstract class BaseController {

    protected String getUSer(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
