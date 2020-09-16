package com.example.tutorial;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btnlogin;
    TextView tvSignup,tvForgotPassword;
    View v;
    Context context;
    EditText etemail,etpassword;
    String email,password;
    String LOGIN_URL="http://192.168.43.65/tutorial/api/user_signIn.php";
    Toast toast;
    public Login_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Login_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Login_Fragment newInstance(String param1, String param2) {
        Login_Fragment fragment = new Login_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_login_, container, false);
         btnlogin=v.findViewById(R.id.Btn_Login);
         tvSignup=v.findViewById(R.id.Tv_gorst);
         tvForgotPassword=v.findViewById(R.id.Tv_Forgote_Password);
         etemail=v.findViewById(R.id.Et_LEmail);
         etpassword=v.findViewById(R.id.Et_LPassword);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=etemail.getText().toString();
                password=etpassword.getText().toString();

                 if (email.isEmpty()) {
                    etemail.requestFocus();
                    etemail.setError("Enter Email");
                }
                 else if (!etemail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    etemail.requestFocus();
                    etemail.setError("Invalid Email");

                }
                 else if (password.isEmpty()){
                     etpassword.requestFocus();
                     etpassword.setError("Enter Password");
                 }
                 else if (password.length() < 6) {
                     etpassword.requestFocus();
                     etpassword.setError("Invalid password");
                 }
                 else {
                     LoginData();
                 }

            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),ForgotPassword_Activity.class);
                startActivity(intent);
            }
        });

        return v;
    }
    public void LoginData(){
        final ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.show();
        progressDialog.setMessage("Plaese Waiting..");
        progressDialog.setCanceledOnTouchOutside(false);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    String status=jsonObject.getString("status");
                    if (status.equals("failed")){

                        toast=Toast.makeText(getContext(),"incorrect email or password",Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.show();
                    }
                    else if (status.equals("Success")){
                        LoginShareprefe_Modal userLoginModal=new LoginShareprefe_Modal(getContext());

                        String mobile=jsonObject.getString("u_phone");
                        String name=jsonObject.getString("u_name");
                        String email=jsonObject.getString("u_email");

                        userLoginModal.setEmail(email);
                        userLoginModal.setName(name);
                        userLoginModal.setMobile(mobile);
                        Intent intent=new Intent(getContext(),Home_User_Activity.class);
                        toast=Toast.makeText(getContext(),"Login successful",Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.show();
                        startActivity(intent);
                        getActivity().finish();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                toast= Toast.makeText( getContext(), "connection fail", Toast.LENGTH_LONG );
                toast.setGravity( Gravity.CENTER,0,0 );
                toast.show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String>parms=new HashMap<>();
                parms.put("u_email",email);
                parms.put("u_password",password);
                return parms;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }
}