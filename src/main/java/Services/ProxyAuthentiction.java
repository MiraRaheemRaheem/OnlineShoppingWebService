package Services;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class ProxyAuthentiction implements IAuthentiction
{
    static ArrayList<String> prohebeted =new ArrayList<String>( Arrays.asList("null", "select", "join","alter","update","delete"));
    Authentication auth = new Authentication();

    @Override
    @RequestMapping(path = "/LoginByName/{name}/{password}", method = RequestMethod.GET )
    public String LoginByName(@PathVariable String name,@PathVariable String password)
    {
        //there's no way to map null with this url but we tried to handle it as much as possible
        if (name!=null && password!=null && (! name.equals(" ")) && (! password.equals(" "))&& password.length() < 4)
        {
            if(prohebeted.contains(name.toLowerCase())||prohebeted.contains(password.toLowerCase())){
                return "Incorrect Input";
            }
            String token = auth.LoginByName(name,password);

            if(token.equals("false"))
                return "Please SignUp First";

            return " Logged in Successfully " + token;
        }
        return "Incorrect Input";
    }


    @RequestMapping(path = "/LoginByEmail/{email}/{password}", method = RequestMethod.GET )
    public String LoginByEmail(@PathVariable String email,@PathVariable  String password)
    {
        //there's no way to map null with this url but we tried to handle it as much as possible
        if (email!=null && password!=null && (! email.equals(" ")) && (! password.equals(" "))&& password.length() < 4)
        {
            if(prohebeted.contains(email.toLowerCase())||prohebeted.contains(password.toLowerCase())){
                return "InCorrect Input";
            }
            String token = auth.LoginByEmail(email,password);

            if(token.equals("false"))
                return "Please SignUp First";

            return " Logged in Successfully " + token;
        }
        return "InCorrect Input";
    }
}
