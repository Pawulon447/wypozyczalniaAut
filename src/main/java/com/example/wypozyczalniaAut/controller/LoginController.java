package com.example.wypozyczalniaAut.controller;

import com.example.wypozyczalniaAut.request.UserRequest;
import com.example.wypozyczalniaAut.service.UserService;
import com.example.wypozyczalniaAut.service.UserServiceException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private UserService userService;


    public LoginController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping("api/login-send")
    public String loginAccount(String email, String password) {
        UserRequest userRequest = new UserRequest(email, password);
        System.out.println(userRequest);

        try {
            userService.loginUser(userRequest);
        } catch (UserServiceException e) {
            System.out.println(e.getMessage());
            return "error";
        }

        return "login-success" + "welcome " + email;
    }

    @GetMapping("/api")
    public String index() {
        return "index";
    }

    @GetMapping("login-success")
    public String loginSuccess() {
        return "login-success";
    }

    @GetMapping("api/log-out/{id}")
    public void logOut(@PathVariable int id) {

        userService.logOut(id);

    }
}
