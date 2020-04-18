package Services;

import Entites.NormalUser;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.ParseException;

@RestController

public class NormalController extends UserController
{
    private NormalUser normalUser;

    @Override
    @RequestMapping(path = "/SignupAsNormal/{name}/{email}/{password}/{gender}/{birthdate}/{mobileNo}/{address}", method = RequestMethod.GET )
    public String Signup(@PathVariable String name, @PathVariable String email, @PathVariable
    String password,@PathVariable String gender, @PathVariable String birthdate, @PathVariable String mobileNo,@PathVariable String address) throws SQLException, ParseException
    {
        String token;
        if(sql_dal.CheckEmailAndUserName(email,name) == true && password.length() >= 4)
        {
            token = sql_dal.SaveUser(name,email, password,gender,birthdate, mobileNo,address,1);
            if (token.equals("false"))
                return "Invalid Input";
            else
                return "Signed Up Successfully " + token;
        }
        else
            return "Invalid Input";
    }
}