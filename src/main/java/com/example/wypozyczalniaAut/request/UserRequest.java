package com.example.wypozyczalniaAut.request;

public class UserRequest {
    private String email;
    private String name;
    private String surname;
    private String password1;
    private String password2;


    public String getEmail() {
        return email;
    }

    public String getPassword1() {
        return password1;
    }

    public String getPassword2() {
        return password2;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public UserRequest(String email, String name, String surname, String password1, String password2) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.password1 = password1;
        this.password2 = password2;
    }

    public UserRequest(String email, String password1) {
        this.email = email;
        this.password1 = password1;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "email='" + email + '\'' +
                ", password1='" + password1 + '\'' +
                ", password2='" + password2 + '\'' +
                '}';
    }
}
