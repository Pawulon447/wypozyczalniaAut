package com.example.wypozyczalniaAut.controller;

import com.example.wypozyczalniaAut.model.Car;
import com.example.wypozyczalniaAut.service.CarService;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {

    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("show-cars")
    public List<Car> showCars(){
        return carService.getCars();
    }


    @PostMapping("/edit-car")
    public void editCars(int id,String name,double price, Boolean rented){
        carService.editCar(id,name,price, rented);


    }
    @PostMapping("/add-car")
    public void addCar(String name,double price){
        carService.addCar(name,price);



    }
    @PostMapping("rent-car")
    public void rentCar(int id){
        carService.checkCar(id);

    }


    @GetMapping("return-car")
    public void returnCar()
    {
        carService.returnCar();
    }

}
