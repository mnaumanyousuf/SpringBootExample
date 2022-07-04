package com.example.demo.controller;

import com.example.demo.AutoResponseDto;
import com.example.demo.model.User;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public AutoResponseDto<List<User>> getAllEmployees(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize)
    {
        return userService.getAllEmployees(pageNo, pageSize);
    }

    @GetMapping("/{id}")
    public AutoResponseDto<User> getUser(@PathVariable("id") Integer id) {
        return userService.getUser(id);
    }

    @PostMapping
    public AutoResponseDto<User> createUser(@Valid @RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    @PutMapping("/{id}")
    public AutoResponseDto<User> updateUser(@Valid @RequestBody User newUser, @PathVariable Integer id) {
        return userService.updateUser(newUser, id);
    }

    @DeleteMapping("/{id}")
    public AutoResponseDto<User> deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

}
