package com.example.wypozyczalniaAut.service;

import com.example.wypozyczalniaAut.model.Car;
import com.example.wypozyczalniaAut.model.User;
import com.example.wypozyczalniaAut.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarService {
    private UserService userService;
    private CarRepository carRepository;


    public CarService(UserService userService, CarRepository carRepository) {
        this.userService = userService;
        this.carRepository = carRepository;
    }

    public List<Car> getCars() {
        List<Car> cars = carRepository.findAll();
        System.out.println(cars);
        return cars;
    }

    public void editCar(int id, Car editedCar) {
        Car searchedCar = getCarById(id);
        if (searchedCar == null) {
            throw new UserServiceException(" not found");
        } else {
            System.out.println(searchedCar);

            if (!editedCar.getIsRented() && searchedCar.getIsRented() != null) {
                clearRentedCar(searchedCar);
            }
            carRepository.save(editedCar);
            System.out.println(carRepository.findById(id));
        }
    }

    public void clearRentedCar(Car editedCar) {
        User searchedUSer = userService.getUserRepository().findByRentedCar(editedCar);
        searchedUSer.setRentedCar(null);
        userService.getUserRepository().save(searchedUSer);
    }


    public void addCar(Car car) {
        carRepository.save(car);
    }

    public void rentCarAdmin(int carId, int userId) {
        Car searchedCar = getCarById(carId);
        User searchedUser = userService.getUserRepository().findById(userId).orElse(null);
        checkCar(searchedCar, searchedUser);
        assignCar(searchedCar, searchedUser);
    }


    public void checkCar(Car searchedCar, User searchedUser) {

        if (searchedCar == null) {
            throw new CarServiceException("car not found", 404);
        }
        if (searchedUser == null) {
            throw new UserServiceException("user not found");
        }
        if (searchedCar.getIsRented()) {

            throw new CarServiceException("This car is unavailable", 409);
        }
        if (searchedUser.getRentedCar() != null) {
            throw new CarServiceException("you already have rented car", 409);
        }
    }

    public void assignCar(Car searchedCar, User searchedUser) {
        searchedCar.setIsRented(true);
        carRepository.save(searchedCar);
        searchedUser.setRentedCar(searchedCar);
        userService.getUserRepository().save(searchedUser);
    }

    public void returnCarCheck(int userId) {
        User searchedUser = userService.getUserRepository().findById(userId).orElse(null);
        if (searchedUser == null) {
            throw new UserServiceException("User not found");
        }
        if (searchedUser.getRentedCar() == null) {
            throw new CarServiceException("This user doesn't have a rented car ", 409);
        } else {
            returnCar(searchedUser);
        }
    }

    public void returnCar(User searchedUser) {
        searchedUser.getRentedCar().setIsRented(false);
        carRepository.saveAndFlush(searchedUser.getRentedCar());
        searchedUser.setRentedCar(null);
        userService.getUserRepository().save(searchedUser);
    }

    public void deleteCar(int id) {
        Car deletedCar = carRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Car with this id not found"));
        clearRentedCar(carRepository.findById(id).get());
        carRepository.delete(deletedCar);
    }

    public Car getCarById(int id) {
        return carRepository.findById(id).orElse(null);
    }
}
