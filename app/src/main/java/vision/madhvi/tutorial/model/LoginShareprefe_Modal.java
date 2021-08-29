package vision.madhvi.tutorial.model;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginShareprefe_Modal {


    Context context;
    public SharedPreferences sharedPreLogin;
    String Id;
    String fathername;
    String sex;
    String DOB;
    String contactId;
    String email;
    String password;
    String mobile;
    String image;
    String Address1;
    String Address2;
    String City;
    String Distric;
    String State;
    String Country;
    String PIN;

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
        sharedPreLogin.edit().putString("FatherName",fathername).commit();

    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
        sharedPreLogin.edit().putString("Sex",sex).commit();

    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
        sharedPreLogin.edit().putString("DOB",DOB).commit();

    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
        sharedPreLogin.edit().putString("Address1",address1).commit();

    }

    public String getAddress2() {
        return Address2;
    }

    public void setAddress2(String address2) {
        Address2 = address2;
        sharedPreLogin.edit().putString("Address2",address2).commit();

    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
        sharedPreLogin.edit().putString("City",city).commit();

    }

    public String getDistric() {
        return Distric;
    }

    public void setDistric(String distric) {
        Distric = distric;
        sharedPreLogin.edit().putString("Distric",distric).commit();

    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
        sharedPreLogin.edit().putString("State",state).commit();

    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
        sharedPreLogin.edit().putString("Country",country).commit();

    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
        sharedPreLogin.edit().putString("PIN",PIN).commit();

    }



    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
        sharedPreLogin.edit().putString("Id",id).commit();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
        sharedPreLogin.edit().putString("fullname",fullName).commit();
    }

    String fullName;
    String userName;


    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
        sharedPreLogin.edit().putString("contactId",contactId).commit();
    }



    public String getuType() {
        return uType;
    }

    public void setuType(String uType) {
        this.uType = uType;
        sharedPreLogin.edit().putString("uType",uType);
    }

    String uType;

    public String getProfileId() {
        return ProfileId;
    }

    public void setProfileId(String profileId) {
        ProfileId = profileId;
        sharedPreLogin.edit().putString("ProfileId",profileId).commit();
    }

    public String getProfileName() {
        return ProfileName;
    }

    public void setProfileName(String profileName) {
        ProfileName = profileName;
        sharedPreLogin.edit().putString("profileName",profileName).commit();
    }

    public String getLastLoginDate() {
        return LastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        LastLoginDate = lastLoginDate;
        sharedPreLogin.edit().putString("lastLoginDate",lastLoginDate).commit();

    }

    String ProfileId;
    String ProfileName;
    String LastLoginDate;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
        sharedPreLogin.edit().putString("UserName",userName).commit();
    }

    String UserName;

    public LoginShareprefe_Modal(Context context) {
        this.context = context;
        sharedPreLogin =context.getSharedPreferences("LoginDetail",Context.MODE_PRIVATE);
    }




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        sharedPreLogin.edit().putString("Email",email).commit();

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        sharedPreLogin.edit().putString("Password",password).commit();

    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
        sharedPreLogin.edit().putString("Mobile",mobile).commit();

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        sharedPreLogin.edit().putString("image",image).commit();

    }
    public void removeuser(){
        sharedPreLogin.edit().clear().commit();
    }


}
