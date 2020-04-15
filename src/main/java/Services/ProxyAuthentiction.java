package Services;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

@RestController
public class ProxyAuthentiction implements IAuthentiction
{
    @Override
    @RequestMapping(path = "/LoginByName/{name}/{password}", method = RequestMethod.GET )
    public boolean LoginByName(@PathVariable String name,@PathVariable String password){
        ArrayList<String> prohebeted =new ArrayList<String>( Arrays.asList("null", "select", "join","alter","update","delete"));
        //there's no way to map null with this url but we tried to handle it as much as possible
        if (name!=null && password!=null && (! name.equals(" ")) && (! password.equals(" "))&& password.length()==4)
        {
            if(prohebeted.contains(name.toLowerCase())||prohebeted.contains(password.toLowerCase())){
                return false;
            }
            return true;
        }
        return false;
    }


    @RequestMapping(path = "/LoginByEmail/{email}/{password}", method = RequestMethod.GET )
    public boolean LoginByEmail(@PathVariable String email,@PathVariable  String password){
        ArrayList<String> prohebeted =new ArrayList<String>( Arrays.asList("null", "select", "join","alter","update","delete"));
        //there's no way to map null with this url but we tried to handle it as much as possible
        if (email!=null && password!=null && (! email.equals(" ")) && (! password.equals(" "))&& password.length()==4)
        {
            if(prohebeted.contains(email.toLowerCase())||prohebeted.contains(password.toLowerCase())){
                return false;
            }
            return true;
        }
        return false;
    }
}
