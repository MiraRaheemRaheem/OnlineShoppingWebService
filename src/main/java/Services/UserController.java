package Services;

import Presistance.UserSQL_DAL;
import org.springframework.web.bind.annotation.RestController;
import java.sql.SQLException;
import java.text.ParseException;

@RestController
public abstract class UserController
{
    protected UserSQL_DAL sql_dal = (UserSQL_DAL) UserSQL_DAL.getInstance();

    public abstract String Signup(String name, String email, String password, String gender, String birthdate, String mobileNo, String address) throws SQLException, ParseException;
}
