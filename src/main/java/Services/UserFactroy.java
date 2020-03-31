package Services;

import Entites.NormalUser;
import Entites.User;

public class UserFactroy
{

    public User Create(int choice, String name, String email, String pass, String gender, String birthdate, String mobileNo)
    {
        User u = null;
        if(choice == 1)
        {
            u = new NormalUser(name, email, pass, gender, birthdate, mobileNo);
        }

        return u;
    }
}
