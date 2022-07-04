package com.example.demo.service;

import com.example.demo.AutoResponseDto;
import com.example.demo.model.User;

public interface UserService {

    AutoResponseDto getUser(Integer id);
    AutoResponseDto createUser(User newUser);
    AutoResponseDto updateUser(User newUser, Integer id);
    AutoResponseDto deleteUser(Integer id);

}
