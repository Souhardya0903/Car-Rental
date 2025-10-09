package com.example.carrental.controllers;

import com.example.carrental.dto.UserLoginRequest;
import com.example.carrental.dto.UserRegistrationRequest;
import com.example.carrental.dto.UserResponse;
import com.example.carrental.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // --- Public User Endpoints ---

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRegistrationRequest request) {
        UserResponse response = userService.createUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody UserLoginRequest request) {
        UserResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }

    // --- Authenticated User Endpoints ---

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMyProfile(@RequestParam Long userId) {
        UserResponse response = userService.getUserById(userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/me")
    public ResponseEntity<UserResponse> updateMyProfile(@RequestParam Long userId, @Valid @RequestBody UserRegistrationRequest request) {
        UserResponse response = userService.updateUser(userId, request);
        return ResponseEntity.ok(response);
    }

    // --- Admin Endpoints ---

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id, @RequestParam Long adminId) {
        UserResponse admin = userService.getUserById(adminId);
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            return new ResponseEntity<>("Action forbidden: User is not an admin.", HttpStatus.FORBIDDEN);
        }
        UserResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody UserRegistrationRequest request, @RequestParam Long adminId) {
        UserResponse admin = userService.getUserById(adminId);
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            return new ResponseEntity<>("Action forbidden: User is not an admin.", HttpStatus.FORBIDDEN);
        }
        UserResponse response = userService.updateUser(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id, @RequestParam Long adminId) {
        UserResponse admin = userService.getUserById(adminId);
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
