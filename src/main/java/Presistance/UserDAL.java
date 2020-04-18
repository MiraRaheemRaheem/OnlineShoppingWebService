package Presistance;

import Entites.User;
import Services.UserFactroy;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public abstract class UserDAL
{

    protected UserFactroy f = new UserFactroy();

    public abstract String SaveUser(String name, String email, String password,
                            String gender, String birthdate, String mobileNo,String address, int type) throws SQLException, ParseException;

    public abstract List<User> LoadUser();

    public abstract boolean CheckEmailAndUserName(String email, String username);

    public abstract String IsAvailableAccount(String emailOrName, String pass, String Type);

    public abstract String CreateToken(int type, String name, String email, String pass, Date currentDate);

    public abstract boolean CheckLoggedIn(String token) ;

    public static UserDAL getInstance(){return null;}
}
