package Services;

import Presistance.UserSQL_DAL;
import org.apache.commons.codec.DecoderException;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.jws.soap.SOAPBinding;
import java.io.UnsupportedEncodingException;

public class Authentication implements IAuthentiction
{
    UserSQL_DAL r = (UserSQL_DAL) UserSQL_DAL.getInstance();
    public String LoginByName(String userName, String password)
    {
        return "";
    }

    public String LoginByEmail(String email, String password)  {
        String token = r.IsAvailableAccount(email, password);

        byte[] decoded = Base64.decodeBase64(token.getBytes());

        System.out.println("Base 64 Decoded  String : " + new String(decoded));

        return token;
    }
}
