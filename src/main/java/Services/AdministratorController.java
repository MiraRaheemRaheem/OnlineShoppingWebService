package Services;

import Entites.Administrator;
import Entites.ShopOwner;
import Entites.User;
import Presistance.UserSQL_DAL;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AdministratorController extends UserController
{
    private Administrator admin;
    private List<User> usersInSytem = new ArrayList<>();

    @Override
    @RequestMapping(path = "/SignUpAsAdmin/{name}/{email}/{password}/{gender}/{birthdate}/{mobileNo}/{address}", method = RequestMethod.GET )
    public boolean Signup(@PathVariable String name, @PathVariable String email, @PathVariable
            String password, @PathVariable String gender, @PathVariable String birthdate, @PathVariable String mobileNo, @PathVariable String address) throws ParseException, SQLException
    {
        if(r.CheckEmail(email) == true)
        {
            r.SaveUser(name,email, password,gender,birthdate, mobileNo,address,3);
            return true;
        }
        else
            return false;
    }

    @RequestMapping(path = "/GetRegisteredUsers", method = RequestMethod.GET )
    public List<User> GetRegisteredUsersInSystem()
    {
        usersInSytem = r.LoadUser();

        return usersInSytem;
    }

    /*public boolean AddNewAdmin()
    {
        //call signup method

    }*/
}
