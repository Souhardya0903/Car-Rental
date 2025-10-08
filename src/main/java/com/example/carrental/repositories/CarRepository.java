package com.example.carrental.repositories;

import com.example.carrental.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByMakeAndModelAndIsAvailable(String make, String model, boolean isAvailable);

    @Query("SELECT c FROM Car c WHERE c.isAvailable = true")
    List<Car> findAllAvailableCars();
}
