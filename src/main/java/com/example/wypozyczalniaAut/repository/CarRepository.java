package com.example.wypozyczalniaAut.repository;

import com.example.wypozyczalniaAut.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {
    public Car findById(int id);

    public Car findByName(String name);


}
