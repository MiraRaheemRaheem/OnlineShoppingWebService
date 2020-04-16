package Services;
import org.apache.commons.codec.DecoderException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

@RestController
public class ProxyAuthentiction implements IAuthentiction
{
    static ArrayList<String> prohebeted =new ArrayList<String>( Arrays.asList("null", "select", "join","alter","update","delete"));
    Authentication auth = new Authentication();

    @Override
    @RequestMapping(path = "/LoginByName/{name}/{password}", method = RequestMethod.GET )
    public String LoginByName(@PathVariable String name,@PathVariable String password){
        //there's no way to map null with this url but we tried to handle it as much as possible
        if (name!=null && password!=null && (! name.equals(" ")) && (! password.equals(" "))&& password.length()==4)
        {
            if(prohebeted.contains(name.toLowerCase())||prohebeted.contains(password.toLowerCase())){
                return "Incorrect Input";
            }
            return "Logged in Successfully";
        }
        return "Please SignUp First";
    }


    @RequestMapping(path = "/LoginByEmail/{email}/{password}", method = RequestMethod.GET )
    public String LoginByEmail(@PathVariable String email,@PathVariable  String password)
    {

        //there's no way to map null with this url but we tried to handle it as much as possible
        if (email!=null && password!=null && (! email.equals(" ")) && (! password.equals(" "))&& password.length()==4)
        {
            if(prohebeted.contains(email.toLowerCase())||prohebeted.contains(password.toLowerCase())){
                return "InCorrect Input";
            }
            String token = auth.LoginByEmail(email,password);
            if(token.length() != 0)
                return token;

            return "Please SignUp First";
        }
        return "InCorrect Input";
    }
}
