package Services;

import Entites.NormalUser;
import Entites.User;

public class UserFactroy
{

    public User Create(int choice, String name, String email, String pass, String gender, String birthdate, String mobileNo, String address)
    {
        User u = null;
        if(choice == 1)
        {
            u = new NormalUser(name, email, pass, gender, birthdate, mobileNo,address);
        }

        return u;
    }
}
