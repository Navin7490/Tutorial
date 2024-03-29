package vision.madhvi.tutorial.ui.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.provider.Settings;
import android.telephony.TelephonyManager;
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
import com.google.android.material.snackbar.Snackbar;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vision.madhvi.tutorial.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUp_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUp_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btnSignup;
    View v;
    EditText etname, etemail, etmobile,etusername, etpassword, etcpassword;
    TextView tvcourse;
    Context context;
    String name, email, mobile,username, password, cpassword;
    String cours;
    ProgressDialog progressDialog;
    ArrayList<String> product;

   // String SIGNUP_URL="http://192.168.43.65/tutorial/api/checkemailinTable.php";
    Snackbar snackbar;
    Toast toast;
    int randomnumber;
    String radomotp="";
    String OtpMess="Dear Customer of Madhvi Vision ";
// IMIEnumber
    Button btn;
    TelephonyManager tm;
    String imeinumber;
    Dialog dialog;
    TextView tvok;
    public SignUp_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUp_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUp_Fragment newInstance(String param1, String param2) {
        SignUp_Fragment fragment = new SignUp_Fragment();
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
        v = inflater.inflate(R.layout.fragment_sign_up_, container, false);
        dialog=new Dialog(requireContext());

//        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
        btnSignup = v.findViewById(R.id.Btn_SignUp);
        etname = v.findViewById(R.id.Et_Name);
        etemail = v.findViewById(R.id.Et_Email);
        etmobile = v.findViewById(R.id.Et_Mobile);
        etusername=v.findViewById(R.id.Et_UserName);
        etpassword = v.findViewById(R.id.Et_Password);
        etcpassword = v.findViewById(R.id.Et_CPassword);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        product = new ArrayList<String>();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Waiting..");


        dialog.setContentView(R.layout.dialog_registration_success);
        dialog.create();
        tvok= dialog.findViewById(R.id.TvOk);
        tvok.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               dialog.dismiss();
           }
       });


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"MissingPermission", "HardwareIds"})
            @Override
            public void onClick(View view) {

//                tm = (TelephonyManager) Objects.requireNonNull(getActivity()). getSystemService(Context.TELEPHONY_SERVICE);
//                imeinumber = tm.getDeviceId();

                imeinumber= Settings.Secure.getString(getActivity().getContentResolver(),Settings.Secure.ANDROID_ID);

                name = etname.getText().toString().trim();
                email = etemail.getText().toString().trim();
                mobile = etmobile.getText().toString().trim();
                username=etusername.getText().toString().trim();
                password = etpassword.getText().toString().trim();
                cpassword = etcpassword.getText().toString().trim();
//                 if (imeinumber==null){
//                     Toast.makeText(getActivity(), "something wrong", Toast.LENGTH_SHORT).show();
//                 }
                if (name.isEmpty()) {
                    etname.requestFocus();
                    etname.setError("Enter Name");
                } else if (name.length() < 4) {
                    etname.requestFocus();
                    etname.setError("Enter Full Name");
                } else if (!etname.getText().toString().matches("[a-z A-Z]*")) {
                    etname.requestFocus();
                    etname.setError("Enter Only Character");

                } else if (email.isEmpty()) {
                    etemail.requestFocus();
                    etemail.setError("Enter Email");
                } else if (!etemail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    etemail.requestFocus();
                    etemail.setError("Invalid Email");

                } else if (mobile.isEmpty()) {
                    etmobile.requestFocus();
                    etmobile.setError("Enter Mobile Number");
                } else if (mobile.length() < 10) {
                    etmobile.requestFocus();
                    etmobile.setError("Enter Full Mobile Number");


                } else if (!etmobile.getText().toString().matches("[0-9]{10}")) {
                    etmobile.requestFocus();
                    etmobile.setError("Invalid Mobie Number");
                }else if (username.isEmpty()){
                    etusername.requestFocus();
                    etusername.setError("Enter username");
                }
                else if (password.isEmpty()) {
                    etpassword.requestFocus();
                    etpassword.setError("Enter Password");
                } else if (password.length() < 6) {
                    etpassword.requestFocus();
                    etpassword.setError("Password Must be Long");
                } else if (cpassword.isEmpty()) {
                    etcpassword.requestFocus();
                    etcpassword.setError("Enter Confirm Paasowrd");
                } else if (imeinumber.isEmpty()) {
                    Toast.makeText(getActivity(), "IMEI Not Found", Toast.LENGTH_SHORT).show();

                } else {

                    if (!password.matches(cpassword)) {
                        etcpassword.requestFocus();
                        etcpassword.setError("No Match Paasowrd");
                    }
                    else {

                       SignupData();
                    }

                }


            }
        });

        return v;
    }
        public  void SignupData(){
        String SIGNUP_URL="http://103.207.169.120:8891/api/Registration";
        progressDialog.show();
        StringRequest stringRequestsigup=new StringRequest(Request.Method.POST, SIGNUP_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("msg");

                        switch (status) {
                        case "Email is already exist!":
                            toast = Toast.makeText(getContext(), "Email already existing", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            break;
                        case "User name is already exist!":
                            toast = Toast.makeText(getContext(), "UserName  already existing", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            break;
                        case "IMEI is already exist!":
                            toast = Toast.makeText(getContext(), "IMEI  already existing", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            break;
                        default:

                            etname.setText("");
                            etemail.setText("");
                            etmobile.setText("");
                            etusername.setText("");
                            etpassword.setText("");
                            etcpassword.setText("");
                            // SendOtp();
                            radomotp = String.valueOf(randomnumber);
                            dialog.show();

//                        Intent intent = new Intent(getContext(), VerifyOTP_Activity.class);
//                        // intent.putExtra("course",cours);
//                        intent.putExtra("name",name);
//                        intent.putExtra("email",email);
//                        intent.putExtra("mobile",mobile);
//                        intent.putExtra("password",password);
//                        intent.putExtra("random",radomotp);
//
//                        startActivity(intent);
                            //getActivity().finish();
//                            toast = Toast.makeText(getContext(), "Registration SuccessFull " , Toast.LENGTH_LONG);
//                            toast.setGravity(Gravity.CENTER, 0, 0);
//                            toast.show();

                            break;
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();

                Toast.makeText(getContext(), "No connection", Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>parms=new HashMap<>();
                parms.put("RegistrationId","0");
                parms.put("EmailId",email);
                parms.put("UserName",username);
                parms.put("Mobile",mobile);
                parms.put("Password",password);
                parms.put("FullName",name);
                parms.put("isEVerify","0");
                parms.put("isMVerify","0");
                parms.put("IMEI",imeinumber);


                return parms;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequestsigup);
    }

//    public void SendOtp(){
//
//        try {
//                    // Construct data
//                    String apiKey = "apikey=" + "eo/1x8BBlAs-re18SbJlYoNTVIxqlGk0PRx15h48iB";
//                    Random random=new Random();
//                      randomnumber=random.nextInt(999999);
//                    String message = "&message=" + "Hey "+OtpMess+" your OTP is "+randomnumber;
//                    String sender = "&sender=" + "TXTLCL";
//                    String numbers = "&numbers=" +mobile;
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
////                  toast=  Toast.makeText(getContext(), "OTP Send Successfully"+randomnumber, Toast.LENGTH_SHORT);
////                  toast.setGravity(Gravity.CENTER,0,0);
////                  toast.show();
//                   // return stringBuffer.toString();
//                } catch (Exception e) {
//                    //System.out.println("Error SMS "+e);
//                   // return "Error "+e;
//
//                    Toast.makeText(getContext(), "Error SMS"+e, Toast.LENGTH_SHORT).show();
//
//                }
//    }
    // Permission Allow method start
    public void  permissionallow(){

        PermissionListener permissionListener=new PermissionListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onPermissionGranted() {

            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                getActivity().finish();
                Toast.makeText(getActivity(), "Required All Permission", Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.with(getActivity())
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.READ_PHONE_STATE,Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }

    // permisssion end

}