package Services;

public interface IAuthentiction
{
    public boolean LoginByName(String userName, String password);
    public boolean LoginByEmail(String email, String password);
}
