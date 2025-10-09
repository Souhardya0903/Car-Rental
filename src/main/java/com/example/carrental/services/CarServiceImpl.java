package com.example.carrental.services;

import com.example.carrental.dto.CarCreateRequest;
import com.example.carrental.dto.CarResponse;
import com.example.carrental.dto.CarSearchRequest;
import com.example.carrental.entities.Car;
import com.example.carrental.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car addCar(CarCreateRequest request) {
        Car car = new Car();
        mapRequestToEntity(request, car);
        car.setAvailable(true);
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Long carId, CarCreateRequest request) {
        return carRepository.findById(carId).map(car -> {
            mapRequestToEntity(request, car);
            return carRepository.save(car);
        }).orElse(null);
    }

    @Override
    public void deleteCar(Long carId) {
        carRepository.deleteById(carId);
    }

    @Override
    public CarResponse getCarById(Long carId) {
        return carRepository.findById(carId)
                .map(this::mapEntityToResponse)
                .orElse(null);
    }

    @Override
    public List<CarResponse> getAllCars() {
        return carRepository.findAll().stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarResponse> searchCars(CarSearchRequest request) {
        List<Car> availableCars = carRepository.findAvailableCars(
                request.getMake(),
                request.getModel(),
                request.getPickupTime(),
                request.getDropofftime()
        );

        return availableCars.stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Car updateAvailability(Long carId, boolean isAvailable) {
        return carRepository.findById(carId).map(car -> {
            car.setAvailable(isAvailable);
            return carRepository.save(car);
        }).orElse(null);
    }

    private void mapRequestToEntity(CarCreateRequest request, Car car) {
        car.setMake(request.getMake());
        car.setModel(request.getModel());
        car.setYear(request.getYear());
        car.setPrice(request.getPrice()); // Added this line
        car.setSpecifications(request.getSpecifications());
        car.setImageUrl(request.getImageUrl());
    }

    private CarResponse mapEntityToResponse(Car car) {
        CarResponse response = new CarResponse();
        response.setId(car.getId());
        response.setMake(car.getMake());
        response.setModel(car.getModel());
        response.setYear(car.getYear());
        response.setPrice(car.getPrice()); // Added this line
        response.setAvailable(car.isAvailable());
        response.setSpecifications(car.getSpecifications());
        response.setImageUrl(car.getImageUrl());
        return response;
    }
}
