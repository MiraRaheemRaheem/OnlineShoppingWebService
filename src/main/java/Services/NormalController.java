package Services;

import Entites.NormalUser;
import Presistance.UserDAL;
import Presistance.UserSQL_DAL;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.ParseException;

@RestController

public class NormalController extends UserController
{
    private NormalUser normalUser;

    @Override
    @RequestMapping(path = "/SignupAsNormal/{name}/{email}/{password}/{gender}/{birthdate}/{mobileNo}/{address}", method = RequestMethod.GET )
    public boolean Signup(@PathVariable String name, @PathVariable String email, @PathVariable
    String password,@PathVariable String gender, @PathVariable String birthdate, @PathVariable String mobileNo,@PathVariable String address) throws SQLException, ParseException {

        r.SaveUser(name,email, password,gender,birthdate, mobileNo,address,1);
        return true;
    }
}