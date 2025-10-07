package com.example.carrental.services;

import com.example.carrental.entities.Car;
import com.example.carrental.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void deleteCar(Long carId) {
        carRepository.deleteById(carId);
    }

    @Override
    public Car getCarById(Long carId) {
        return carRepository.findById(carId).orElse(null);
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> searchCars(String make, String model, boolean isAvailable) {
        return carRepository.findByMakeAndModelAndIsAvailable(make, model, isAvailable);
    }
}
