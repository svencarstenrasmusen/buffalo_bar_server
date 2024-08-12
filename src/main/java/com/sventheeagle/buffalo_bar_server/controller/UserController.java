package com.sventheeagle.buffalo_bar_server.controller;

import com.sventheeagle.buffalo_bar_server.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @GetMapping
    public String getUser() {
        return "PLACEHOLDER FOR USER";
    }
}
