package com.example.demo.service;

import com.example.demo.AutoResponseDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public AutoResponseDto<List<User>> getAllEmployees(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<User> pagedResult = userRepository.findAll(paging);
        AutoResponseDto<List<User>> autoResponse = new AutoResponseDto(true);
        if(pagedResult.hasContent()) {
            autoResponse.Result = pagedResult.getContent();
        } else {
            autoResponse.StatusCode = HttpStatus.NOT_FOUND;
            autoResponse.Message = "No users found";
            autoResponse.Success = false;
        }
        return  autoResponse;
    }

    public AutoResponseDto<User> getUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User foundUser = user.get();
            AutoResponseDto<User> autoResponse = new AutoResponseDto(true);
            autoResponse.Result = foundUser;
            return autoResponse;
        }
        AutoResponseDto<User> autoResponse = new AutoResponseDto(false);
        autoResponse.Message = "No such user";
        autoResponse.StatusCode = HttpStatus.NOT_FOUND;
        return autoResponse;
    }

    public AutoResponseDto<User> createUser(User newUser) {
        AutoResponseDto<User> autoResponse = new AutoResponseDto(true);
        if (newUser.getName() == null || newUser.getCity() == null) {
            autoResponse.StatusCode = HttpStatus.BAD_REQUEST;
            autoResponse.Message = "Fields cannot be null";
            autoResponse.Success = false;
        } else if (newUser.getName().trim().isEmpty() || newUser.getCity().trim().isEmpty()) {
            autoResponse.StatusCode = HttpStatus.BAD_REQUEST;
            autoResponse.Message = "Fields cannot be empty";
            autoResponse.Success = false;
        } else {
            User user = new User();
            user.setCity(newUser.getCity());
            user.setName(newUser.getName());
            userRepository.save(user);
            autoResponse.Result = user;
            autoResponse.Message = "User created Successfully!";
        }
        return autoResponse;
    }

    public AutoResponseDto<User> updateUser(User newUser, Integer id) {
        Optional<User> user = userRepository.findById(id);
        AutoResponseDto<User> autoResponse = new AutoResponseDto(true);
        if (user.isPresent()) {
            if (newUser.getName() == null || newUser.getCity() == null) {
                autoResponse.StatusCode = HttpStatus.BAD_REQUEST;
                autoResponse.Message = "Fields cannot be null";
                autoResponse.Success = false;
            } else if (newUser.getName().trim().isEmpty() || newUser.getCity().trim().isEmpty()) {
                autoResponse.StatusCode = HttpStatus.BAD_REQUEST;
                autoResponse.Message = "Fields cannot be empty";
                autoResponse.Success = false;
            } else {
                User prevUser = user.get();
                prevUser.setCity(newUser.getCity());
                prevUser.setName(newUser.getName());
                userRepository.save(prevUser);
                autoResponse.Result = prevUser;
                autoResponse.Message = "User created Successfully!";
            }
            return autoResponse;
        }
        autoResponse.Success = false;
        autoResponse.Message = "No such user";
        autoResponse.StatusCode = HttpStatus.NOT_FOUND;
        return autoResponse;
    }

    public AutoResponseDto<User> deleteUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        AutoResponseDto<User> autoResponse = new AutoResponseDto(true);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            autoResponse.Result = user.get();
            autoResponse.Message = "User deleted successfully";
            return autoResponse;
        }
        autoResponse.Success = false;
        autoResponse.Message = "No such user";
        return autoResponse;
    }

}
