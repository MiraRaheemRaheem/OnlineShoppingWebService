package Entites;

public class ShopOwner extends User {
    String name;
    String email;
    String password;
    String gender;
    String birthdate;
    String mobilePhone;

    public ShopOwner(String name, String email, String pass, String gender, String birthdate, String mobileNo, String address) {
        super(name, email, pass, gender, birthdate, mobileNo, address);
    }
}
