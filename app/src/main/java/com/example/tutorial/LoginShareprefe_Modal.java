package com.example.tutorial;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginShareprefe_Modal {


    Context context;
    public SharedPreferences sharedPreLogin;



    String course;
    String name;
    String email;
    String password;
    String mobile;
    String image;

    public LoginShareprefe_Modal(Context context) {
        this.context = context;
        sharedPreLogin =context.getSharedPreferences("LoginDetail",Context.MODE_PRIVATE);
    }
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
        sharedPreLogin.edit().putString("course",course);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        sharedPreLogin.edit().putString("name",name).commit();
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
