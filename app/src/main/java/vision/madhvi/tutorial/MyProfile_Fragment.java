package vision.madhvi.tutorial;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import okio.ByteString;
import vision.madhvi.tutorial.R;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyProfile_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyProfile_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View v;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    Button btnupdateprofile;
    TextView tvcontactId, tvmobile,tvemail,tvdob;
    RadioButton btnrmale,btnrfemale;
    EditText etname,etfathername,etcountry,etstate,etdistric,etcity,etaddress1,etaddress2,etpincode;
    String contactId, mobile,email,fullname,fathername,sex,dob,country,state,distric,city,address1,address2,pincode;
    CircleImageView imageprofile;
    private  static final int PICKE_IMAGE=1;
    Uri imageuri;
    Bitmap bitmap;
    String imagedata;
    LoginShareprefe_Modal loginShareprefe_modal;
    String UPDATE_PROFILE_URL="http://103.207.169.120:8891/api/Profile";
    String gender="";
    Toast toast;
    ProgressDialog progressDialog;
    public MyProfile_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyProfile_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyProfile_Fragment newInstance(String param1, String param2) {
        MyProfile_Fragment fragment = new MyProfile_Fragment();
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
        v= inflater.inflate(R.layout.fragment_my_profile_, container, false);
        tvcontactId=v.findViewById(R.id.Tv_PContactId);
        tvmobile=v.findViewById(R.id.Tv_PMobile);
        tvemail=v.findViewById(R.id.Tv_PEmail);
        imageprofile=v.findViewById(R.id.Im_Profile);
        etname=v.findViewById(R.id.Et_PFullName);
        etfathername=v.findViewById(R.id.Et_PFatherName);
        etcountry=v.findViewById(R.id.Et_PCountry);
        etstate=v.findViewById(R.id.Et_PState);
        etdistric=v.findViewById(R.id.Et_PDistric);
        etcity=v.findViewById(R.id.Et_PCity);
        etaddress1=v.findViewById(R.id.Et_PAddress1);
        etaddress2=v.findViewById(R.id.Et_PAddress2);
        etpincode=v.findViewById(R.id.Et_PPinNumber);
        tvdob=v.findViewById(R.id.Et_PDOB);
        btnrmale=v.findViewById(R.id.BtnrMale);
        btnrfemale=v.findViewById(R.id.BtnrFemale);
        btnupdateprofile=v.findViewById(R.id.Btn_PUpdate);


         loginShareprefe_modal=new LoginShareprefe_Modal(getContext());
        contactId=loginShareprefe_modal.sharedPreLogin.getString("contactId",null);

        mobile=loginShareprefe_modal.sharedPreLogin.getString("Mobile",null);
        email=loginShareprefe_modal.sharedPreLogin.getString("Email",null);
        fullname=loginShareprefe_modal.sharedPreLogin.getString("fullname",null);
        fathername=loginShareprefe_modal.sharedPreLogin.getString("FatherName",null);
        sex=loginShareprefe_modal.sharedPreLogin.getString("Sex",null);
        dob=loginShareprefe_modal.sharedPreLogin.getString("DOB",null);
        country=loginShareprefe_modal.sharedPreLogin.getString("Country",null);
        state=loginShareprefe_modal.sharedPreLogin.getString("State",null);
        distric=loginShareprefe_modal.sharedPreLogin.getString("Distric",null);
        city=loginShareprefe_modal.sharedPreLogin.getString("City",null);
        address1=loginShareprefe_modal.sharedPreLogin.getString("Address1",null);
        address2=loginShareprefe_modal.sharedPreLogin.getString("Address2",null);
        pincode=loginShareprefe_modal.sharedPreLogin.getString("PIN",null);
        Glide.with(getContext()).load(loginShareprefe_modal.sharedPreLogin.getString("image",null)).into(imageprofile);

        tvcontactId.setText(contactId);
        tvmobile.setText(mobile);
        tvemail.setText(email);
        etname.setText(fullname);
        etfathername.setText(fathername);
        tvdob.setText(dob);
        etcountry.setText(country);
        etstate.setText(state);
        etdistric.setText(distric);
        etcity.setText(city);
        etaddress1.setText(address1);
        etaddress2.setText(address2);
        etpincode.setText(pincode);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Waiting..");

        imageprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallary=new Intent();
                gallary.setType("image/*");
                gallary.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallary,"select image"),PICKE_IMAGE);
            }
        });
        tvdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONDAY);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(),onDateSetListener,year,month,day);
                datePickerDialog.show();
            }
        });

        onDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date=dayOfMonth+"/"+month+"/"+year;
                tvdob.setText(date);
            }
        };

        btnupdateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String cidu=tvcontactId.getText().toString();
                final String fulnameu=etname.getText().toString();
                final String fanameu=etfathername.getText().toString();
                final String birthdateu=tvdob.getText().toString();
                final String cotryu=etcountry.getText().toString();
                final String stateu=etstate.getText().toString();
                final String districtu=etdistric.getText().toString();
                final String cityu=etcity.getText().toString();
                final String addred1u=etaddress1.getText().toString();
                final String pinu=etpincode.getText().toString();
                if (imageuri==null){
                    toast=Toast.makeText(getActivity(),"choose image",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
                else if (fulnameu.isEmpty()){
                    toast=Toast.makeText(getActivity(),"Enter Name",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }else {
                   if (btnrmale.isChecked()){
                       gender=btnrmale.getText().toString();
                   }
                   else if (btnrfemale.isChecked()){
                       gender=btnrfemale.getText().toString();
                   }

                    progressDialog.show();
                    StringRequest stringRequest = new StringRequest(Request.Method.PUT, UPDATE_PROFILE_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            toast = Toast.makeText(getActivity(), "Profile update", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            toast = Toast.makeText(getActivity(), "No connection", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> parms = new HashMap<>();
                            imagedata = ImageToString(bitmap);
                            parms.put("ContactId", cidu);
                            parms.put("FullName", fulnameu);
                            parms.put("FName", fanameu);
                            parms.put("DOB", birthdateu);
                            parms.put("Country", cotryu);
                            parms.put("State", stateu);
                            parms.put("District", districtu);
                            parms.put("City", cityu);
                            parms.put("Address1", addred1u);
                            parms.put("PIN", pinu);
                            parms.put("Sex", gender);
                            parms.put("Photo", imagedata);

                            return parms;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                    requestQueue.add(stringRequest);
                }
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==PICKE_IMAGE && resultCode==RESULT_OK){
            imageuri=data.getData();

        }
        try {
            bitmap= MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),imageuri);
            imageprofile.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String ImageToString(Bitmap bitmap){
        ByteArrayOutputStream OutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,OutputStream);
        byte[]imagebyte=OutputStream.toByteArray();
        String encodedImage= Base64.encodeToString(imagebyte,Base64.DEFAULT);
        return encodedImage;
    }
}