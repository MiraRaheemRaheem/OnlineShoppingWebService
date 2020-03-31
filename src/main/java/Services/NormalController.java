package Services;

import Entites.NormalUser;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController

public class NormalController extends UserController {
    private NormalUser normalUser;

    @RequestMapping(path = "/SignupAsNormal/{name}/{email}/{password}/{gender}/{birthdate}/{mobileNo}", method = RequestMethod.GET )
    public boolean SignupAsNormal(@PathVariable String name, @PathVariable String email, @PathVariable
    String password,@PathVariable String gender, @PathVariable String birthdate, @PathVariable String mobileNo) {

        service.Signup(name,email, password,gender,birthdate, mobileNo);
        System.out.println(name+" "+email+" "+password+" "+gender+" "+birthdate+" "+ mobileNo);
        return true;
    }
}