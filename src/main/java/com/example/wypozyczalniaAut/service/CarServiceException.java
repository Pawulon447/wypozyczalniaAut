package com.example.wypozyczalniaAut.service;

public class CarServiceException extends RuntimeException {
    private int status;

    public CarServiceException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
