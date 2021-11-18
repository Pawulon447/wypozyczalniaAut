package com.example.wypozyczalniaAut.controller;

import com.example.wypozyczalniaAut.model.User;
import com.example.wypozyczalniaAut.request.UserRequest;
import com.example.wypozyczalniaAut.service.CarService;
import com.example.wypozyczalniaAut.service.UserService;
import com.example.wypozyczalniaAut.service.UserServiceException;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/all")
public class LoginController {
    UserService userService;
    CarService carService;
    String loggedUserEmail;

    public LoginController(UserService userService, CarService carService) {
        this.userService = userService;
        this.carService = carService;
    }


    @PostMapping("/login-send")
    public String loginAccount(String email, String password){

        UserRequest userRequest=new UserRequest(email,password);
        try {
            userService.loginUser(userRequest);


        }catch (UserServiceException e){

            System.out.println(e.getMessage());

            return "error";
        }
        loggedUserEmail=email;
        carService.setLoggedUser(userService.getLoggedUser());
        System.out.println("welcome "+email);
        return "login-success";
    }


    @GetMapping("/")
    public String index(){
        return "index";
    }



    @GetMapping("log-out")
    public void logOut(){
        System.out.println("have a nice day "+loggedUserEmail);

        loggedUserEmail=null;
        carService.setLoggedUser(null);
        userService.setLoggedUser(null);
    }


}
