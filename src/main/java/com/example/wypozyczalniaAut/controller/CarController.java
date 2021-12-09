package com.example.wypozyczalniaAut.controller;

import com.example.wypozyczalniaAut.model.Car;
import com.example.wypozyczalniaAut.service.CarService;
import com.example.wypozyczalniaAut.service.CarServiceException;
import com.example.wypozyczalniaAut.service.UserServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("api/cars")
    public List<Car> getCars() {
        return carService.getCars();
    }

    @GetMapping("api/cars/{id}")
    public Car getCarById(@PathVariable int id) {
        return carService.getCarById(id);
    }

    @PostMapping("api/cars/{id}")
    public void editCars(@PathVariable int id, @RequestBody Car car) {
        car.setId(id);
        carService.editCar(id, car);
    }

    @PostMapping("api/cars")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        System.out.println(car);
        carService.addCar(car);
        return ResponseEntity.created(URI.create("api/cars/" + car.getId())).body(car);
    }

    @PostMapping("api/cars/{carId}/users/{userId}")
    public ResponseEntity<Void> rentCar(@PathVariable int carId, @PathVariable int userId) {

        try {
            carService.rentCarAdmin(carId, userId);

        } catch (CarServiceException e) {
            return ResponseEntity.status(e.getStatus()).header("error", e.getMessage()).build();
        } catch (UserServiceException e) {
            return ResponseEntity.notFound().header("error", e.getMessage()).build();
        }
        return ResponseEntity.ok().build();
    }


    @GetMapping("api/return-car/{id}")
    public void returnCar(int id) {
        carService.returnCarCheck(id);
    }

    @DeleteMapping("api/cars/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable int id) {
        try {
            carService.deleteCar(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().header("error", e.getMessage()).build();
        }
        return ResponseEntity.noContent().build();

    }
}
