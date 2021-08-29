package vision.madhvi.tutorial.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vision.madhvi.tutorial.R;
import com.google.android.material.textfield.TextInputLayout;

public class ForgotPassword_Activity extends AppCompatActivity {
   Button btnverify,btnsubmit;
   EditText etemail,etpassword,etcpassword;
   String email,password,cpassword;
   TextInputLayout layoutfp,layoutfc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_);
        getSupportActionBar().setTitle("Forgot Passsword");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etemail=findViewById(R.id.Et_FEnteEmail);
        etpassword=findViewById(R.id.Et_FPassword);
        etcpassword=findViewById(R.id.Et_FCPaasword);

        btnverify=findViewById(R.id.Btn_FPVEmail);
        btnsubmit=findViewById(R.id.Btn_FPSubmit);
        layoutfp=findViewById(R.id.LayouFP);
        layoutfc=findViewById(R.id.LayoutFC);

        btnsubmit.setVisibility(View.GONE);
        etpassword.setVisibility(View.GONE);
        etcpassword.setVisibility(View.GONE);
        layoutfp.setVisibility(View.GONE);
        layoutfc.setVisibility(View.GONE);


        btnverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=etemail.getText().toString();
                if (email.isEmpty()){
                    etemail.requestFocus();
                    etemail.setError("Enter email");
                }
                else if (!etemail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    etemail.requestFocus();
                    etemail.setError("Invalid Email");

                }
                else {
                    etemail.setVisibility(View.GONE);
                    btnverify.setVisibility(View.GONE);
                    btnsubmit.setVisibility(View.VISIBLE);
                    etpassword.setVisibility(View.VISIBLE);
                    etcpassword.setVisibility(View.VISIBLE);
                    layoutfp.setVisibility(View.VISIBLE);
                    layoutfc.setVisibility(View.VISIBLE);
                }
            }
        });


        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=etpassword.getText().toString();
                cpassword=etcpassword.getText().toString();
                if (password.isEmpty()){
                    etpassword.requestFocus();
                    etpassword.setError("Enter New Password");
                }
                else if (password.length() < 6) {
                    etpassword.requestFocus();
                    etpassword.setError("Enter strong password");
                }
                else if (cpassword.isEmpty()){

                    etcpassword.requestFocus();
                    etcpassword.setError("Enter confirm Password");
                }
               else if (!password.matches(cpassword)){
                    etcpassword.requestFocus();
                    etcpassword.setError("No Match Password");
                }
               else {
                    etemail.setText("");
                    etemail.setVisibility(View.VISIBLE);
                    btnverify.setVisibility(View.VISIBLE);
                    btnsubmit.setVisibility(View.GONE);
                    etpassword.setVisibility(View.GONE);
                    etcpassword.setVisibility(View.GONE);
                    layoutfp.setVisibility(View.GONE);
                    layoutfc.setVisibility(View.GONE);
                    Intent intent=new Intent(getApplicationContext(), Home_Activity.class);
                    startActivity(intent);
                    Toast.makeText(ForgotPassword_Activity.this, "Password Reset Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}