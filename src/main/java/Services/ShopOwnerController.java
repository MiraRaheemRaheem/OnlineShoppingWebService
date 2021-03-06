package Services;

import Entites.ShopOwner;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.sql.SQLException;
import java.text.ParseException;

@RestController
public class ShopOwnerController extends UserController
{
    private ShopOwner shopOwner;

    @Override
    @RequestMapping(path = "/SignupAsShopOwner/{name}/{email}/{password}/{gender}/{birthdate}/{mobileNo}/{address}", method = RequestMethod.GET )
    public String Signup(@PathVariable String name, @PathVariable String email, @PathVariable
            String password, @PathVariable String gender, @PathVariable String birthdate, @PathVariable String mobileNo, @PathVariable String address) throws ParseException, SQLException
    {

        String token;
        if(sql_dal.CheckEmailAndUserName(email,name) == true && password.length() >= 4)
        {
            token = sql_dal.SaveUser(name,email, password,gender,birthdate, mobileNo,address,2);
            if (token.equals("false"))
                return "Invalid Input";
            else
                return "Signed Up Successfully " + token;
        }
        else
            return "Invalid Input";
    }

}
