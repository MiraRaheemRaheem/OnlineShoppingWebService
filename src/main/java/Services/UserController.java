package Services;
import Presistance.UserSQL_DAL;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.text.ParseException;

@RestController
public abstract class UserController
{
    protected UserSQL_DAL r = (UserSQL_DAL) UserSQL_DAL.getInstance();

    public abstract String Signup(String name, String email, String password, String gender, String birthdate, String mobileNo, String address) throws SQLException, ParseException;
    //Logout(): void
}
