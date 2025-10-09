package com.example.carrental.controllers;

import com.example.carrental.dto.CarCreateRequest;
import com.example.carrental.dto.CarResponse;
import com.example.carrental.dto.CarSearchRequest;
import com.example.carrental.entities.Car;
import com.example.carrental.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/search")
    public ResponseEntity<List<CarResponse>> searchCars(@RequestBody CarSearchRequest request) {
        List<CarResponse> cars = carService.searchCars(request);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PostMapping("/addcar")
    public ResponseEntity<Car> addCar(@RequestBody CarCreateRequest request) {
        Car newCar = carService.addCar(request);
        return new ResponseEntity<>(newCar, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody CarCreateRequest request) {
        Car updatedCar = carService.updateCar(id, request);
        return updatedCar != null ? new ResponseEntity<>(updatedCar, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PutMapping("/{id}/availability")
//    public ResponseEntity<Car> updateAvailability(@PathVariable Long id) {
//        Car updatedCar = carService.updateAvailability(id);
//        return updatedCar != null ? new ResponseEntity<>(updatedCar, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable Long id) {
        CarResponse car = carService.getCarById(id);
        return car != null ? new ResponseEntity<>(car, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CarResponse>> getAllCars() {
        List<CarResponse> cars = carService.getAllCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
}
