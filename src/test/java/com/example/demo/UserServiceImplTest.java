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
    void testGetUserFailure() {
        User user = new User();
        user.setId(2);
        user.setName("Muhammad Nauman");
        user.setCity("Karachi");
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        AutoResponseDto<User> foundUser = userService.getUser(3);
        Assert.isTrue(!foundUser.Success, foundUser.Message);
        Assert.isTrue(foundUser.StatusCode == HttpStatus.NOT_FOUND, foundUser.Message);
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

    @Test
    void testUpdateUserSuccess() {
        User user = new User();
        user.setId(2);
        user.setName("Nauman");
        user.setCity("Karachi");
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        User updatedUser = new User();
        updatedUser.setId(2);
        updatedUser.setName("Nauman Yousuf");
        updatedUser.setCity("Karachi");
        AutoResponseDto<User> foundUser = userService.updateUser(updatedUser, 2);
        Assert.isTrue(foundUser.StatusCode == HttpStatus.OK, foundUser.Message);
        Assert.isTrue(foundUser.Success, foundUser.Message);
    }

    @Test
    void testUpdateUserWithNullFields() {
        User user = new User();
        user.setId(2);
        user.setName("Nauman");
        user.setCity("Karachi");
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        User updatedUser = new User();
        updatedUser.setId(2);
        updatedUser.setName(null);
        updatedUser.setCity(null);
        AutoResponseDto<User> foundUser = userService.updateUser(updatedUser, 2);
        Assert.isTrue(foundUser.StatusCode == HttpStatus.BAD_REQUEST, foundUser.Message);
        Assert.isTrue(!foundUser.Success, foundUser.Message);
    }

    @Test
    void testUpdateUserWithEmptyFields() {
        User user = new User();
        user.setId(2);
        user.setName("Nauman");
        user.setCity("Karachi");
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        User updatedUser = new User();
        updatedUser.setId(2);
        updatedUser.setName("");
        updatedUser.setCity("");
        AutoResponseDto<User> foundUser = userService.updateUser(updatedUser, 2);
        Assert.isTrue(foundUser.StatusCode == HttpStatus.BAD_REQUEST, foundUser.Message);
        Assert.isTrue(!foundUser.Success, foundUser.Message);
    }

    @Test
    void testUpdateUserWithWrongId() {
        User user = new User();
        user.setId(2);
        user.setName("Nauman");
        user.setCity("Karachi");
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        User updatedUser = new User();
        updatedUser.setId(2);
        updatedUser.setName(null);
        updatedUser.setCity(null);
        AutoResponseDto<User> foundUser = userService.updateUser(updatedUser, 3);
        Assert.isTrue(foundUser.StatusCode == HttpStatus.NOT_FOUND, foundUser.Message);
        Assert.isTrue(!foundUser.Success, foundUser.Message);
    }

    @Test
    void testDeleteUserWithWrongId() {
        User user = new User();
        user.setId(2);
        user.setName("Nauman");
        user.setCity("Karachi");
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        AutoResponseDto<User> foundUser = userService.deleteUser(3);
        Assert.isTrue(foundUser.StatusCode == HttpStatus.NOT_FOUND, foundUser.Message);
        Assert.isTrue(!foundUser.Success, foundUser.Message);
    }

    @Test
    void testDeleteUserSuccess() {
        User user = new User();
        user.setId(2);
        user.setName("Nauman");
        user.setCity("Karachi");
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        AutoResponseDto<User> foundUser = userService.deleteUser(2);
        Assert.isTrue(foundUser.StatusCode == HttpStatus.OK, foundUser.Message);
        Assert.isTrue(foundUser.Success, foundUser.Message);
    }

}
