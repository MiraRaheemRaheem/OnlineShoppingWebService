package Services;

import Entites.ShopOwner;
import Presistance.UserSQL_DAL;
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

    @RequestMapping(path = "/SignupAsShopOwner/{name}/{email}/{password}/{gender}/{birthdate}/{mobileNo}/{address}", method = RequestMethod.GET )
    public boolean SignupAsShopOwner(@PathVariable String name, @PathVariable String email, @PathVariable
            String password, @PathVariable String gender, @PathVariable String birthdate, @PathVariable String mobileNo, @PathVariable String address) throws ParseException, SQLException
    {

        System.out.println(name+" "+email+" "+password+" "+gender+" "+birthdate+" "+ mobileNo);

        UserSQL_DAL r = (UserSQL_DAL) UserSQL_DAL.getInstance();
        r.SaveUser(name,email, password,gender,birthdate, mobileNo,address,2);
        return true;
    }

}
