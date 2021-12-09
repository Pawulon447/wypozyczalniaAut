package com.example.wypozyczalniaAut.service;

import com.example.wypozyczalniaAut.model.User;
import com.example.wypozyczalniaAut.repository.UserRepository;
import com.example.wypozyczalniaAut.request.UserRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public void checkUser(UserRequest userRequest) {
        System.out.println(userRequest);
        User searchedAccount = userRepository.findByEmail(userRequest.getEmail());
        if (searchedAccount != null) {
            System.out.println("account exists");
            throw new UserServiceException("account exists");
        }
        if (!userRequest.getPassword1().equals(userRequest.getPassword2())) {

            System.out.println("passwords do not match");
            throw new UserServiceException("passwords do not match");

        } else {
            createAccount(userRequest);
        }
    }


    public void createAccount(UserRequest userRequest) {
        User account = new User(userRequest.getEmail(), userRequest.getName(), userRequest.getSurname(), userRequest.getPassword1());
        userRepository.save(account);
        showAccount(account.getEmail());
    }

    public void showAccount(String email) {
        System.out.println(userRepository.findByEmail(email));
    }

    public void logOut(int id) {
        User searchedUser = userRepository.findById(id).get();
        searchedUser.setLogged(false);

    }

    public void loginUser(UserRequest userRequest) {
        User searchedAccount = userRepository.findByEmail(userRequest.getEmail());

        System.out.println(searchedAccount);
        if (null == searchedAccount) {
            throw new UserServiceException("account email not found");
        } else if (userRequest.getPassword1().equals(searchedAccount.getPassword())) {
            System.out.println("welcome " + searchedAccount.getEmail());
            searchedAccount.setLogged(true);
            userRepository.save(searchedAccount);
        } else {
            throw new UserServiceException("wrong password");
        }
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

}
