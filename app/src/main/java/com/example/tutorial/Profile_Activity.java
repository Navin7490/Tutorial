package com.example.tutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile_Activity extends AppCompatActivity {
    CircleImageView imageView;
    TextView tvname, tvusername;
    String image, name, username;
     Button btnUpdate,btnSignOut;
     String email;
     Toast toast;
    LoginShareprefe_Modal loginShareprefeModal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);

        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvname = findViewById(R.id.Tv_ProfileName);
        tvusername = findViewById(R.id.Tv_ProMobile);
        btnSignOut=findViewById(R.id.Btn_ProfileLogout);
        btnUpdate=findViewById(R.id.Btn_ProfileUpdate);
        imageView=findViewById(R.id.Im_Profile);

         loginShareprefeModal = new LoginShareprefe_Modal(Profile_Activity.this);
      Uri uri= Uri.parse(image=loginShareprefeModal.sharedPreLogin.getString("image",null));
         name = loginShareprefeModal.sharedPreLogin.getString("profileName", null);
         username = loginShareprefeModal.sharedPreLogin.getString("UserName", null);

        tvname.setText(name);
        tvusername.setText(username);
        imageView.setImageURI(uri);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             showlogoutdailog();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void showlogoutdailog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Exit");
        builder.setMessage("you want to logout");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loginShareprefeModal =new LoginShareprefe_Modal( Profile_Activity.this );
                loginShareprefeModal.removeuser();
                toast = Toast.makeText( Profile_Activity.this,"Logout successful",Toast.LENGTH_SHORT );
                toast.setGravity( Gravity.CENTER,0,0 );
                toast.show();
                Intent intent=new Intent( getApplicationContext(), Home_Activity.class );
                startActivity( intent );
                finish();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        Dialog dialog= builder.create();
        dialog.show();
        // dialog.getWindow().setBackgroundDrawableResource(R.color.blue);
        Button no=((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
        no.setTextColor(Color.BLACK);
        Button yes=((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
        yes.setTextColor(Color.BLACK);

    }
}