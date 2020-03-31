package Services;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    SignUpService service= new SignUpService();
    //Login(name:String, password:String ): void
    //Logout(): void
}
