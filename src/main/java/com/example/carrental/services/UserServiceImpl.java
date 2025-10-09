package com.example.carrental.services;

import com.example.carrental.dto.UserLoginRequest;
import com.example.carrental.dto.UserRegistrationRequest;
import com.example.carrental.dto.UserResponse;
import com.example.carrental.entities.Users;
import com.example.carrental.exceptions.UsersNotFoundException;
import com.example.carrental.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createUser(UserRegistrationRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent())
            throw new RuntimeException("Email exists");

        Users users = new Users();
        users.setFirstName(request.getFirstName());
        users.setLastName(request.getLastName());
        users.setEmail(request.getEmail());
        users.setPassword(request.getPassword());
        users.setPhoneNumber(request.getPhoneNumber());
        users.setRole(request.getRole());
        userRepository.save(users);
        return mapToResponse(users);
    }

    @Override
    public UserResponse getUserById(Long id) {
        Users users = userRepository.findById(id)
                .orElseThrow(() -> new UsersNotFoundException("User not found with id: " + id));
        return mapToResponse(users);
    }

    @Override
    public UserResponse updateUser(Long id, UserRegistrationRequest request) {
        Users users = userRepository.findById(id)
                .orElseThrow(()-> new UsersNotFoundException("User not found with id: " + id));

        users.setFirstName(request.getFirstName());
        users.setLastName(request.getLastName());
        users.setPhoneNumber(request.getPhoneNumber());
        if(request.getPassword()!=null && !request.getPassword().isEmpty())
            users.setPassword(request.getPassword());
        userRepository.save(users);
        return mapToResponse(users);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UsersNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse login(UserLoginRequest request) {
        Users users = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new RuntimeException("Invalid credentials"));

        if(!request.getPassword().equals(users.getPassword()))
        {
            throw new RuntimeException("Invalid credentials");
        }

        return mapToResponse(users);
    }

    private UserResponse mapToResponse(Users users) {
        UserResponse response = new UserResponse();
        response.setId(users.getId());
        response.setFirstName(users.getFirstName());
        response.setLastName(users.getLastName());
        response.setEmail(users.getEmail());
        response.setPhoneNumber(users.getPhoneNumber());
        response.setRole(users.getRole());
        return response;
    }
}
