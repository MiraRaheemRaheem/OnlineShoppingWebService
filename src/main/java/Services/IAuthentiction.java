package Services;

public interface IAuthentiction
{
    String LoginByName(String userName, String password);
    String LoginByEmail(String email, String password) ;
}
