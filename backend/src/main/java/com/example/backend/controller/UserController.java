package com.example.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.UserRequestdto;
import com.example.backend.dto.UserResponsedto;
import com.example.backend.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("user")
public class UserController {

    private UserService us;

    public UserController(UserService us) {
        this.us = us;
    }

    @PostMapping()
    public ResponseEntity<UserResponsedto> add(@Valid @RequestBody UserRequestdto u) {
        return ResponseEntity.status(201).body(us.addUser(u));
    }
}
