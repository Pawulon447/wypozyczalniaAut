package com.example.wypozyczalniaAut.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class Car {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private double price;
    private boolean isRented = false;


    public Car() {
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


    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setIsRented(Boolean isRented) {
        this.isRented = isRented;
    }


    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && Double.compare(car.price, price) == 0 && isRented == car.isRented && Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, isRented);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", isRented=" + isRented +

                '}';
    }
}
