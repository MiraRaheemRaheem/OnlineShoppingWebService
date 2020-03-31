package Presistance;

import Entites.User;
import Services.UserFactroy;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public abstract class UserDAL
{

    protected UserFactroy f;

    public abstract boolean SaveUser(String name, String email, String password,
                            String gender, String birthdate, String mobileNo, int type) throws SQLException, ParseException;

    public abstract List<User> LoadUser();

    public abstract UserDAL getInstance();
}
