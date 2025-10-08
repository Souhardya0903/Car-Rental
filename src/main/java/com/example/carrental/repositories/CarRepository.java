package com.example.carrental.repositories;

import com.example.carrental.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByMakeAndModelAndIsAvailable(String make, String model, boolean isAvailable);

    @Query("SELECT c FROM Car c WHERE c.isAvailable = true")
    List<Car> findAllAvailableCars();

    @Query("SELECT c FROM Car c WHERE " +
           "(:make IS NULL OR c.make = :make) AND " +
           "(:model IS NULL OR c.model = :model) AND " +
           "c.isAvailable = true AND " +
           "c.id NOT IN (" +
           "  SELECT b.car.id FROM Booking b WHERE " +
           "  b.pickupDate < :dropoffTime AND b.dropoffDate > :pickupTime" +
           ")")
    List<Car> findAvailableCars(
        @Param("make") String make,
        @Param("model") String model,
        @Param("pickupTime") LocalDate pickupTime,
        @Param("dropoffTime") LocalDate dropoffTime
    );
}
