package Entites;

import Presistance.UserSQL_DAL;

public class NormalUser extends User {
    //ShoppingCart shopingCart;

    public NormalUser(String name, String email, String pass, String gender, String birthdate, String mobileNo,String address)
    {
        super(name, email, pass, gender, birthdate, mobileNo,address);

    }

}


