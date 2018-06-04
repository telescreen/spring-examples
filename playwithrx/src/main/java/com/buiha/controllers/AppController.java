package com.buiha.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class AppController {

    @RequestMapping("/create")
    public String create() {
        return "app_create";
    }
}
