package Services;

import Entites.Administrator;
import Entites.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class AdministratorController extends UserController
{
    private Administrator admin;
    private List<User> usersInSytem = new ArrayList<>();

    @Override
    @RequestMapping(path = "/SignUpAsAdmin/{name}/{email}/{password}/{gender}/{birthdate}/{mobileNo}/{address}", method = RequestMethod.GET )
    public String Signup(@PathVariable String name, @PathVariable String email, @PathVariable
            String password, @PathVariable String gender, @PathVariable String birthdate, @PathVariable String mobileNo, @PathVariable String address) throws ParseException, SQLException
    {
        String token;
        if(r.CheckEmailAndUserName(email,name) == true && password.length() >= 4)
        {
            token = r.SaveUser(name,email, password,gender,birthdate, mobileNo,address,3);
            if (token.equals("false"))
                return "Invalid Input";
            else
                return "Signed Up Successfully " + token;
        }
        else
            return "Invalid Input";
    }

    @RequestMapping(path = "/GetRegisteredUsers/{token}", method = RequestMethod.GET )
    public List<User> GetRegisteredUsersInSystem(@PathVariable String token)
    {

        if(r.CheckLoggedIn(token)) {
            usersInSytem = r.LoadUser();
            return usersInSytem;
        }
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,
                "You do not have the permission to use this function");
    }

    /*public boolean AddNewAdmin()
    {
        //call signup method

    }*/
}
