package com.example.wypozyczalniaAut.controller;

import com.example.wypozyczalniaAut.request.UserRequest;
import com.example.wypozyczalniaAut.service.UserService;
import com.example.wypozyczalniaAut.service.UserServiceException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("register")
    public String register()
    {
        return "register";
    }

    @PostMapping("/register-send")
    public String registerAccount(String email,String name,String surname, String password1, String password2){
        UserRequest userRequest=new UserRequest(email,name,surname, password1,password2);
        try {
            userService.checkUser(userRequest);
        }catch (UserServiceException e){

            return "error";
        }


        return "register-success";
    }


    @GetMapping("/register-success")
    public String registerSuccess()
    {
        return "register-success";
    }

}
