package Entites;

public class User {

    String name;
    String date;
    String password;
    String email;
    String gender;
    String mobileNo;
    String address;
    String token;


    public User(String name, String email, String pass, String gender, String birthdate, String mobileNo, String address)
    {
        this.name = name;
        this.email = email;
        this.date = birthdate;
        this.gender = gender;
        this.mobileNo = mobileNo;
        this.password = pass;
        this.address = address;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
