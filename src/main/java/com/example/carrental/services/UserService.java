package com.example.carrental.services;

import com.example.carrental.dto.UserLoginRequest;
import com.example.carrental.dto.UserRegistrationRequest;
import com.example.carrental.dto.UserResponse;

public interface UserService {
    UserResponse createUser(UserRegistrationRequest request);
    UserResponse getUserById(Long id);
    UserResponse updateUser(Long id, UserRegistrationRequest request);
    UserResponse login(UserLoginRequest request);
    void deleteUser(Long id);
}
