package com.penguin.vueboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AuthController {
    @GetMapping("/unAuth")
    public String toLogin() {
        return "unAuth";
    }




}


