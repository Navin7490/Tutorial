package com.example.tutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class VerifyOTP_Activity extends AppCompatActivity {
  EditText etname,etmobile,etotp;
  Button btnsend,btnverify;
  int randomnumber;
  String course,name,email,mobile,password,otp;
  String SIGNUP_URL="http://192.168.43.65/tutorial/api/user_signUp.php";
  Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_o_t_p_);
//        etname=findViewById(R.id.Et_Otp_Name);
//        etmobile=findViewById(R.id.Et_Otp_Mobile);
        etotp=findViewById(R.id.Et_Otp_Etnter);

      //  btnsend=findViewById(R.id.Btn_OTp_send);
        btnverify=findViewById(R.id.Btn_otp_Verify);

        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Madhvi Vision");

        final Intent intent=getIntent();
       course=intent.getStringExtra("course");
       name=intent.getStringExtra("name");
       mobile=intent.getStringExtra("mobile");
       email=intent.getStringExtra("email");
       password=intent.getStringExtra("password");
       otp=intent.getStringExtra("otp");
//        btnsend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    // Construct data
//                    String apiKey = "apikey=" + "eo/1x8BBlAs-re18SbJlYoNTVIxqlGk0PRx15h48iB";
//                    Random random=new Random();
//                    randomnumber=random.nextInt(999999);
//                    String message = "&message=" + "Hey "+etname.getText().toString()+" your OTP is "+randomnumber;
//                    String sender = "&sender=" + "TXTLCL";
//                    String numbers = "&numbers=" +etmobile.getText().toString();
//
//                    // Send data
//                    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
//                    String data = apiKey + numbers + message + sender;
//                    conn.setDoOutput(true);
//                    conn.setRequestMethod("POST");
//                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
//                    conn.getOutputStream().write(data.getBytes("UTF-8"));
//                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                    final StringBuffer stringBuffer = new StringBuffer();
//                    String line;
//                    while ((line = rd.readLine()) != null) {
//                        stringBuffer.append(line);
//                    }
//                    rd.close();
//                    Toast.makeText(VerifyOTP_Activity.this, "OTP Send Successfully", Toast.LENGTH_SHORT).show();
//                   // return stringBuffer.toString();
//                } catch (Exception e) {
//                    //System.out.println("Error SMS "+e);
//                   // return "Error "+e;
//                    Toast.makeText(VerifyOTP_Activity.this, "Error SMS"+e, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(VerifyOTP_Activity.this, "ERROR"+e, Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });
        btnverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String votp=etotp.getText().toString();
                if (votp.isEmpty()){
                    etotp.requestFocus();
                    etotp.setError("Enter OTP");
                }
//                else {
//                  SignUpUserData();
//
//                }
              else   if (votp==otp){
                    Intent intent=new Intent(getApplicationContext(),Home_Activity.class);
                    startActivity(intent);
                    finish();
                    SignUpUserData();
                    Toast.makeText(VerifyOTP_Activity.this, "Verify Successfully", Toast.LENGTH_SHORT).show();

                }
                else {
                    etotp.requestFocus();
                    etotp.setError("OTP is Wrong");
                    //Toast.makeText(VerifyOTP_Activity.this, "Wrong OTP", Toast.LENGTH_SHORT).show();

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
    public  void SignUpUserData(){

        StringRequest stringRequestsigup=new StringRequest(Request.Method.POST, SIGNUP_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");

                    if (status.equals("user already created")) {

                        toast = Toast.makeText(getApplicationContext(), "Email already existing...", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();

                    }
                    else  {
                        etotp.setText("");

                        toast = Toast.makeText(getApplicationContext(), "Registration Successful..", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();

                        LoginShareprefe_Modal loginShareprefeModal=new LoginShareprefe_Modal(VerifyOTP_Activity.this);
                        loginShareprefeModal.setCourse(course);
                        loginShareprefeModal.setName(name);
                        loginShareprefeModal.setEmail(email);
                        loginShareprefeModal.setMobile(mobile);
                        loginShareprefeModal.setPassword(password);
                        Intent intentlog=new Intent(getApplicationContext(),Home_User_Activity.class);
                        startActivity(intentlog);
                        finish();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "No Connection", Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> parms=new HashMap<>();
                 parms.put("u_name",name);
                parms.put("u_email",email);
                parms.put("u_phone",mobile);
                parms.put("u_password",password);
                parms.put("u_course",course);

                return parms;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequestsigup);
    }

    }
