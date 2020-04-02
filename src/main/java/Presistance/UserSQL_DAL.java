package Presistance;
import Entites.User;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserSQL_DAL extends UserDAL
{
    static UserSQL_DAL r;
    static Connection con;
    static Statement statement;
    String url = "jdbc:mysql://localhost:3306/online_shopping";
    String userName = "root";
    String password = "123456789";

    private UserSQL_DAL()
    {
        if(con == null) {
            try {
                con = DriverManager.getConnection(url, userName, password);
                statement = con.createStatement();
                System.out.println("In The IF.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean SaveUser(String name, String email, String pass, String gender, String birthdate, String mobileNo,String address, int type) throws ParseException, SQLException
    {
        String query = "INSERT INTO `user_`(`USER_TYPE`, `USER_NAME`, `BDATE`, `USER_PASSWORD`, `EMAIL`, `GENDER`, `MOBILE_NUM`, `Address`) VALUES (?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt(1, type);
            preparedStmt.setString(2, name);

            Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(birthdate);
            preparedStmt.setDate(3,  new java.sql.Date(date1.getTime()));

            preparedStmt.setString(4, pass);
            preparedStmt.setString(5, email);

            preparedStmt.setString(6, gender);
            preparedStmt.setString(7, mobileNo);
            preparedStmt.setString(8, address);

            preparedStmt.execute();

        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    @Override
    public List<User> LoadUser()
    {

        ArrayList<User> user_InSystem = new ArrayList<>();
        User user = null;

        try
        {
            String query = "SELECT * FROM user_ ";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                int type = resultSet.getInt(2);
                String name = resultSet.getString(3);
                Date date = resultSet.getDate(4);
                String pass = resultSet.getString(5);
                String email = resultSet.getString(6);
                String gender = resultSet.getString(7);
                String mobileNo = resultSet.getString(8);
                String d = date.toString();
                String address = resultSet.getString(9);

                user = f.Create(type,name, email, pass, gender, d, mobileNo, address);
                user_InSystem.add(user);
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        return user_InSystem;
    }

    @Override
    public boolean CheckEmail(String email)
    {
        try
        {
            String query = "SELECT EMAIL FROM `user_` WHERE EMAIL = '"+email+"' "  ;
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();

            if(resultSet.getString(1).length() == 0)
                return true;

        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
        }

        return false;
    }

    public static UserDAL getInstance()
    {
        if(r == null) {
            r  = new UserSQL_DAL();
        }
        return r;
    }
}
