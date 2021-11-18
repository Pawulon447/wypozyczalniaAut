package com.example.wypozyczalniaAut.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String email;
    private String name;
    private String surname;
    private String password;
    @OneToOne
    private Car rentedCar;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String name, String surname, String password) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.password = password;

    }

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Car getRentedCar() {
        return rentedCar;
    }

    public String getName() {
        return name;
    }

    public void setRentedCar(Car rentedCar) {
        this.rentedCar = rentedCar;
    }


}
