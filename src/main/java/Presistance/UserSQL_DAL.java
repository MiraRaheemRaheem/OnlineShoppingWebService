package Presistance;

import Entites.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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
        if (con == null) {
            try {
                con = DriverManager.getConnection(url, userName, password);
                statement = con.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public String SaveUser(String name, String email, String pass, String gender, String birthdate, String mobileNo, String address, int type) throws ParseException, SQLException
    {
        String query = "INSERT INTO `user_`(`USER_TYPE`, `USER_NAME`, `BDATE`, `USER_PASSWORD`, `EMAIL`, `GENDER`, `MOBILE_NUM`, `Address`) VALUES (?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt(1, type);
            preparedStmt.setString(2, name);

            Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(birthdate);
            preparedStmt.setDate(3, new java.sql.Date(date1.getTime()));

            preparedStmt.setString(4, pass);
            preparedStmt.setString(5, email);

            preparedStmt.setString(6, gender);
            preparedStmt.setString(7, mobileNo);
            preparedStmt.setString(8, address);

            preparedStmt.execute();

        } catch (SQLException e) {
            return "false";
        }

        long time = System.currentTimeMillis();
        java.sql.Date currentDate = new java.sql.Date(time);
        String token = CreateToken(type,name,email,pass,currentDate);

        return token;
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

                user = f.Create(type, name, email, pass, gender, d, mobileNo, address);
                user_InSystem.add(user);
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        return user_InSystem;
    }

    @Override
    public boolean CheckEmailAndUserName(String email, String username)
    {
        try
        {
            String query = "SELECT EMAIL, USER_NAME FROM `user_` WHERE EMAIL = '" + email + "' and USER_NAME = '"+ username+"'";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();

            resultSet.getString(1);
            return false;
        }
        catch (SQLException e1)
        {
            return true;
        }
    }

    public static UserDAL getInstance()
    {
        if (r == null) {
            r = new UserSQL_DAL();
        }
        return r;
    }

    public String IsAvailableAccount(String emailOrName, String pass, String type)
    {
        String query = null ;
        ResultSet resultSet ;
        byte[] byteToken = new byte[255];
        String token ;

        if(type.equals("email"))
        {
            query = "SELECT USER_TYPE,USER_NAME,expire_date,token FROM `user_`" +
                    " WHERE EMAIL = '" + emailOrName + "' And USER_PASSWORD = '" + pass + "' ";
        }
        else if (type.equals("username"))
        {
            query = "SELECT USER_TYPE,USER_NAME,expire_date,token FROM `user_`" +
                    " WHERE USER_NAME = '" + emailOrName + "' And USER_PASSWORD = '" + pass + "' ";
        }

        long time = System.currentTimeMillis();
        java.sql.Date currentDate = new java.sql.Date(time);

        try
        {
            resultSet = statement.executeQuery(query);
            resultSet.next();

            if (resultSet.getString(2).length() != 0)
            {
                int usertype = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Date expireDate = resultSet.getDate(3);
                byteToken = resultSet.getBytes(4);

                if (currentDate.compareTo(expireDate) == 1)
                {
                    token = CreateToken(usertype,name,emailOrName,pass,currentDate) ;
                    return token;
                }
            }
        }
        catch(SQLException e)
        {
            return "false";
        }
        token = new String(byteToken); // if the date not expired
        return token;
    }

    public static String CreateToken (int type,String name,String email,String pass,Date currentDate)
    {
        String encode ;
        encode = type + "." + name + "." + email + "." + pass + "." + currentDate;
        byte[] byteToken = Base64.encodeBase64(encode.getBytes());
        String token = new String(byteToken);

        LocalDateTime newExpireDate = LocalDateTime.now();

        try
        {
            String query = "update`user_` set `token` = ? , `expire_date` = ? where Email = ?";

            PreparedStatement preparedStmt ;

            preparedStmt = con.prepareStatement(query);

            preparedStmt.setString(1, token);
            preparedStmt.setTimestamp(2,  Timestamp.valueOf(newExpireDate.plusHours(2)));
            preparedStmt.setString(3, email);

            preparedStmt.execute();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return token;
    }

    public boolean CheckLoggedIn(String token) {
        byte[] decoded = Base64.decodeBase64(token.getBytes());
        System.out.println("Base 64 Decoded  String : " + new String(decoded));

        String decodedtoken = new String(decoded);
        String[] decodedData = decodedtoken.split("\\.");

        String query = "SELECT USER_TYPE,expire_date FROM `user_`" + " WHERE token = '" + token + "' ";

        ResultSet resultSet = null;
        try
        {
            resultSet = statement.executeQuery(query);
            resultSet.next();

            int usertype = resultSet.getInt(1);
            Date expireDate = resultSet.getDate(2);

            long time = System.currentTimeMillis();
            java.sql.Date currentDate = new java.sql.Date(time);

            if(usertype == Integer.parseInt(decodedData[0]) && expireDate.compareTo(currentDate) == 1)
                return true;

        }
        catch (SQLException e)
        {
            return false;
        }

        return false;
    }
}
