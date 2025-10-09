package com.example.carrental.controllers;

import com.example.carrental.dto.CarCreateRequest;
import com.example.carrental.dto.CarResponse;
import com.example.carrental.dto.CarSearchRequest;
import com.example.carrental.dto.UserResponse;
import com.example.carrental.entities.Car;
import com.example.carrental.services.CarService;
import com.example.carrental.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    private final CarService carService;
    private final UserService userService;

    public CarController(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }

    // --- PUBLIC ENDPOINTS --- //

    @PostMapping("/search")
    public ResponseEntity<List<CarResponse>> searchCars(@RequestBody CarSearchRequest request) {
        List<CarResponse> cars = carService.searchCars(request);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable Long id) {
        CarResponse car = carService.getCarById(id);
        return car != null ? new ResponseEntity<>(car, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<CarResponse>> getAllCars() {
        List<CarResponse> cars = carService.getAllCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    // --- ADMIN-ONLY ENDPOINTS --- //

    @PostMapping
    public ResponseEntity<?> addCar(@RequestBody CarCreateRequest request, @RequestParam Long adminId) {
        UserResponse admin = userService.getUserById(adminId);
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            return new ResponseEntity<>("Action forbidden: User is not an admin.", HttpStatus.FORBIDDEN);
        }
        Car newCar = carService.addCar(request);
        return new ResponseEntity<>(newCar, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Long id, @RequestBody CarCreateRequest request, @RequestParam Long adminId) {
        UserResponse admin = userService.getUserById(adminId);
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            return new ResponseEntity<>("Action forbidden: User is not an admin.", HttpStatus.FORBIDDEN);
        }
        Car updatedCar = carService.updateCar(id, request);
        return updatedCar != null ? new ResponseEntity<>(updatedCar, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id, @RequestParam Long adminId) {
        UserResponse admin = userService.getUserById(adminId);
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<?> updateAvailability(@PathVariable Long id, @RequestBody boolean isAvailable, @RequestParam Long adminId) {
        UserResponse admin = userService.getUserById(adminId);
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            return new ResponseEntity<>("Action forbidden: User is not an admin.", HttpStatus.FORBIDDEN);
        }
        Car updatedCar = carService.updateAvailability(id, isAvailable);
        return updatedCar != null ? new ResponseEntity<>(updatedCar, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
