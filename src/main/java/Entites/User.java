package Entites;

import Services.SignUpService;

import java.util.Date;

public class User {

    String name;
    String date;
    String password;
    String email;
    String gender;
    String mobileNo;

    public User(String name, String email, String pass, String gender, String birthdate, String mobileNo)
    {
        this.name = name;
        this.email = email;
        this.date = birthdate;
        this.gender = gender;
        this.mobileNo = mobileNo;
        this.password = pass;
    }
}
