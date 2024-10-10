package org.example.repository;

import org.example.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {
    List<Car> findByModel(String Model);
    List<Car> findByModelAndPriceLessThanEqual(String model,BigDecimal price);

}

