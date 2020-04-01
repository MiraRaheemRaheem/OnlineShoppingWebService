package Entites;

public class Administrator extends User
{
    boolean loggedIn;

    public Administrator(String name, String email, String pass, String gender, String birthdate, String mobileNo, String address) {
        super(name, email, pass, gender, birthdate, mobileNo, address);
    }
}
