package com.example.carrental.services;

import com.example.carrental.dto.CarCreateRequest;
import com.example.carrental.dto.CarResponse;
import com.example.carrental.dto.CarSearchRequest;
import com.example.carrental.entities.Car;

import java.util.List;

public interface CarService {
    Car addCar(CarCreateRequest request);
    Car updateCar(Long carId, CarCreateRequest request);
    void deleteCar(Long carId);
    CarResponse getCarById(Long carId);
    List<CarResponse> getAllCars();
    List<CarResponse> searchCars(CarSearchRequest request);
    Car updateAvailability(Long carId, boolean isAvailable);
}
