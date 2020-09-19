package com.example.tutorial;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginShareprefe_Modal {


    Context context;
    public SharedPreferences sharedPreLogin;



    String Id;

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

    String contactId;
    String email;
    String password;
    String mobile;
    String image;

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
        sharedPreLogin.edit().putString("email",email).commit();

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        sharedPreLogin.edit().putString("password",password).commit();

    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
        sharedPreLogin.edit().putString("mobile",mobile).commit();

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
