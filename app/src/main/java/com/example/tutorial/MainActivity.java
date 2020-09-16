package com.example.tutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LoginShareprefe_Modal loginShareprefeModal1=new LoginShareprefe_Modal(getApplicationContext());
                String email=   loginShareprefeModal1.sharedPreLogin.getString("email",null);

                if (email !=null){
                    Intent intent=new Intent(getApplicationContext(),Home_User_Activity.class);
                    startActivity(intent);
                    finish();
                }
                else {

                    Intent intenth=new Intent(getApplicationContext(),Home_Activity.class);
                    startActivity(intenth);
                    finish();

                }

            }
        },2000);
    }

}