package com.example.wypozyczalniaAut.service;

import com.example.wypozyczalniaAut.model.Car;
import com.example.wypozyczalniaAut.model.User;
import com.example.wypozyczalniaAut.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
   private UserService userService;
   private CarRepository carRepository;
   private User loggedUser;


    public CarService(UserService userService, CarRepository carRepository) {
        this.userService = userService;
        this.carRepository = carRepository;
    }

    public List<Car> getCars(){
        List<Car> cars=carRepository.findAll();
        System.out.println();
        cars.forEach(System.out::println);
        return cars;
    }

    public void editCar(int id, String name,double price, Boolean rented){
        Car searchedCar=carRepository.findById(id);
        if (searchedCar==null){
            throw new UserServiceException(" not found");

        }else {
            System.out.println(searchedCar);
            if(!rented&&searchedCar.getRenter()!=null){
                clearRentedCar(searchedCar);
                searchedCar.setRenter(null);
            }
            searchedCar.setName(name);
            searchedCar.setPrice(price);
            searchedCar.setIsRented(rented);

            carRepository.save(searchedCar);
            System.out.println(carRepository.findById(id));
        }
    }

    public void clearRentedCar(Car editedCar){
        Car searchedCar=carRepository.findById(editedCar.getId());
        User searchedUSer=searchedCar.getRenter();
        searchedUSer.setRentedCar(null);
        userService.getUserRepository().save(searchedUSer);
    }


    public void addCar(String name,double price){
        Car car=new Car(name,price);
        carRepository.save(car);


        System.out.println(carRepository.findByName(name));

    }
    public void checkCar(int id){
        Car searchedCar=carRepository.findById(id);
        if(searchedCar==null){
            throw new UserServiceException("car not found");

        }
        if(searchedCar.getIsRented()){
            System.out.println("This car is unavailable");
            throw new UserServiceException("this car is unavailable");
        }
        if(loggedUser.getRentedCar()!=null){
            System.out.println("you already have rented car");

        }else{
            assignCar(searchedCar);
        }
    }

    public void assignCar(Car searchedCar){
        searchedCar.setIsRented(true);
        searchedCar.setRenter(loggedUser);
        carRepository.save(searchedCar);


        loggedUser.setRentedCar(searchedCar);
        userService.getUserRepository().save(loggedUser);
    }


    public void returnCar(){
        loggedUser.getRentedCar().setIsRented(false);
        loggedUser.getRentedCar().setRenter(null);
        carRepository.saveAndFlush(loggedUser.getRentedCar());
        loggedUser.setRentedCar(null);
        userService.getUserRepository().save(loggedUser);
    }



    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }


}
