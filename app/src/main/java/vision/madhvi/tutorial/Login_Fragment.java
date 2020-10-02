package vision.madhvi.tutorial;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import vision.madhvi.tutorial.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Login_Fragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    Button btnlogin;
    TextView tvSignup, tvForgotPassword;
    View v;
    Context context;
    EditText eteusername, etpassword;
    String username, password;
    Toast toast;
     ProgressDialog progressDialog;
    public Login_Fragment() {
    }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_login_, container, false);
        btnlogin = v.findViewById(R.id.Btn_Login);
        tvSignup = v.findViewById(R.id.Tv_gorst);
        tvForgotPassword = v.findViewById(R.id.Tv_Forgote_Password);
        eteusername = v.findViewById(R.id.Et_LEmail);
        etpassword = v.findViewById(R.id.Et_LPassword);
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Waite");

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = eteusername.getText().toString();
                password = etpassword.getText().toString();

                if (username.isEmpty()) {
                    eteusername.requestFocus();
                    eteusername.setError("Enter UserName");
                } else if (password.isEmpty()) {
                    etpassword.requestFocus();
                    etpassword.setError("Enter Password");
                } else {
                    LoginData();
                }

            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ForgotPassword_Activity.class);
                startActivity(intent);
            }
        });

        return v;
    }

    public void LoginData() {
        progressDialog.show();
        String LOGIN_URL = "http://103.207.169.120:8891/api/Login?UserName=" + username + "&Password=" + password;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("paaa", response);
         progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("logins");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject detail = jsonArray.getJSONObject(i);
                        String status = detail.getString("Status");
                        String UserName = detail.getString("UserName");
                        String ContactId = detail.getString("ContactId");
                        String FullName = detail.getString("FullName");
                        String FatherName=detail.getString("FName");
                        String Sex=detail.getString("Sex");
                        String DOB=detail.getString("DOB");
                        String Address1=detail.getString("Address1");
                        String Address2=detail.getString("Address2");
                        String City=detail.getString("City");
                        String District=detail.getString("District");
                        String State=detail.getString("State");
                        String Country=detail.getString("Country");
                        String PIN=detail.getString("PIN");
                        String Mobile=detail.getString("Mobile");
                        String EmailId=detail.getString("EmailId");
                        String ProfileId = detail.getString("ProfileId");
                        String ProfileName = detail.getString("ProfileName");
                        String LastLoginDate = detail.getString("LastLoginDate");
                        String Photo = detail.getString("Photo");
                        String Password = detail.getString("Password");
                        String Id = detail.getString("Id");
                        String uType = detail.getString("uType");

                        if (status.equals("Success")) {
                            toast = Toast.makeText(getContext(), "Login Successfull", Toast.LENGTH_SHORT);
                            toast.show();
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            LoginShareprefe_Modal shareprefeModal = new LoginShareprefe_Modal(getActivity());
                            shareprefeModal.setContactId(ContactId);
                            shareprefeModal.setUserName(UserName);
                            shareprefeModal.setFullName(FullName);
                            shareprefeModal.setFathername(FatherName);
                            shareprefeModal.setSex(Sex);
                            shareprefeModal.setDOB(DOB);
                            shareprefeModal.setAddress1(Address1);
                            shareprefeModal.setAddress2(Address2);
                            shareprefeModal.setCity(City);
                            shareprefeModal.setDistric(District);
                            shareprefeModal.setState(State);
                            shareprefeModal.setCountry(Country);
                            shareprefeModal.setPIN(PIN);
                            shareprefeModal.setMobile(Mobile);
                            shareprefeModal.setEmail(EmailId);

                            shareprefeModal.setProfileId(ProfileId);
                            shareprefeModal.setProfileName(ProfileName);
                            shareprefeModal.setLastLoginDate(LastLoginDate);
                            shareprefeModal.setImage(Photo);
                            shareprefeModal.setPassword(Password);
                            shareprefeModal.setId(Id);
                            shareprefeModal.setuType(uType);
                            startActivity(new Intent(getActivity(), Home_User_Activity.class));
                            getActivity().finish();


                        } else if (status.equals("Failed")) {
                            toast = Toast.makeText(getContext(), "Incorrect Email or Password", Toast.LENGTH_SHORT);
                            toast.show();
                            toast.setGravity(Gravity.CENTER, 0, 0);


                        }


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                toast = Toast.makeText(getContext(), "No Connection", Toast.LENGTH_SHORT);
                toast.show();
                toast.setGravity(Gravity.CENTER, 0, 0);


            }
        });
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String, String> param = new HashMap<>();
//                param.put("UserName",email);
//                param.put("Password",password);
//                return param;
//            }
//        };

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(stringRequest);

    }
}

