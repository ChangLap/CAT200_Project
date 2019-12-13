package com.example.cat200;

public class userDetail {
    private String Email, Password, CarPlate;
    private double Ewallet;

    public userDetail() {
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

    public String getCarPlate() {
        return CarPlate;
    }

    public void setCarPlate(String carPlate) {
        CarPlate = carPlate;
    }

    public double getEwallet() {
        return Ewallet;
    }

    public void setEwallet(float ewallet) {
        Ewallet = ewallet;
    }
}
