package Services;

import org.apache.commons.codec.DecoderException;

import java.io.UnsupportedEncodingException;

public interface IAuthentiction
{
    public String LoginByName(String userName, String password);
    public String LoginByEmail(String email, String password) ;
}
