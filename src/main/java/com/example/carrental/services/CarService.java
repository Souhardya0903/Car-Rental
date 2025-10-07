package com.example.carrental.services;

import com.example.carrental.entities.Car;

import java.util.List;

public interface CarService {

    Car addCar(Car car);

    Car updateCar(Car car);

    void deleteCar(Long carId);

    Car getCarById(Long carId);

    List<Car> getAllCars();

    List<Car> searchCars(String make, String model, boolean isAvailable);
}
