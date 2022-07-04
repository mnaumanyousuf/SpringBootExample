package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetUserSuccess() {
        User user = new User();
        user.setId(2);
        user.setName("Muhammad Nauman");
        user.setCity("Karachi");
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        AutoResponseDto<User> foundUser = userService.getUser(user.getId());
        Assert.isTrue(foundUser.Success, foundUser.Message);
        Assert.isTrue(foundUser.StatusCode == HttpStatus.OK, foundUser.Message);
    }

    @Test
    void testCreateUserWithFieldsNull() {
        User user = new User();
        user.setId(2);
        when(userRepository.save(user)).thenReturn(user);
        AutoResponseDto<User> foundUser = userService.createUser(user);
        Assert.isTrue(foundUser.StatusCode == HttpStatus.BAD_REQUEST, foundUser.Message);
        Assert.isTrue(!foundUser.Success, foundUser.Message);
    }

    @Test
    void testCreateUserWithFieldsEmpty() {
        User user = new User();
        user.setId(2);
        user.setName("");
        user.setCity("");
        when(userRepository.save(user)).thenReturn(user);
        AutoResponseDto<User> foundUser = userService.createUser(user);
        Assert.isTrue(foundUser.StatusCode == HttpStatus.BAD_REQUEST, foundUser.Message);
        Assert.isTrue(!foundUser.Success, foundUser.Message);
    }

    @Test
    void testCreateUserSuccess() {
        User user = new User();
        user.setId(2);
        user.setName("Nauman");
        user.setCity("Karachi");
        when(userRepository.save(user)).thenReturn(user);
        AutoResponseDto<User> foundUser = userService.createUser(user);
        Assert.isTrue(foundUser.StatusCode == HttpStatus.OK, foundUser.Message);
        Assert.isTrue(foundUser.Success, foundUser.Message);
    }

}
