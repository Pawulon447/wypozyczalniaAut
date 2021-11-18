package com.example.wypozyczalniaAut.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Car {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private double price;
    private Boolean isRented =false;
    @OneToOne
    private User renter;

    public Car() {
    }

    public Car(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Car(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getIsRented() {
        return isRented;
    }

    public User getRenter() {
        return renter;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsRented(Boolean isRented) {
        this.isRented = isRented;
    }

    public void setRenter(User renter) {
        this.renter = renter;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", rented=" + isRented +
                ", renter=" + renter +
                '}';
    }
}
