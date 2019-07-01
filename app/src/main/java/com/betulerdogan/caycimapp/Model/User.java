package com.betulerdogan.caycimapp.Model;

public class User {
    private String Email;
    private String Password;
    private String Phone;


    public User() {
        }

    public User(String email, String password) {
        Email = email;
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
