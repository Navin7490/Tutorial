package com.example.tutorial;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewDetail_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<SubjectDeatail_Modal> product;

    TextView tvsubjectdetail, tvcourse, tvdescription, tvprice, tvcourseGroup,tvsubtitle,tvsubdes;
    String courseid;
    String courseId, coursename, coursedescription, courseprice, coursegroup;
    String VIEWCOURSEDETAIL_URL = "http://192.168.43.65/tutorial/api/ViewCourseDetail.php";
    String VIEWSUBJECTDETAIL_URL = "http://192.168.43.65/tutorial/api/ViewSubjectbyCourse.php";
    Button btnpurchase;
    ProgressDialog progressDialog;
    String msg;
    String subname, subdescription;

    private static final int TEZ_REQUEST_CODE = 123;
    private static final String GOOGLE_TEZ_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
    Uri uri;
    String upiid = "tinu1316@oksbi";
    String name = "Navin";
    String note = "Madhvi vision Trasaction";
    String staus;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail_);

        recyclerView = findViewById(R.id.Rv_Subject);
        tvcourse = findViewById(R.id.Tv_mycc);
        tvdescription = findViewById(R.id.Tv_mydd);
        tvprice = findViewById(R.id.Tv_mypp);
        tvcourseGroup = findViewById(R.id.Tv_myAA);
        btnpurchase = findViewById(R.id.Btn_Purchase);
        tvsubjectdetail=findViewById(R.id.Tv_SubjectDetail);
        tvsubtitle=findViewById(R.id.Tv_Subjecttit);
        tvsubdes=findViewById(R.id.Tv_subjectdescrrrr);
        getSupportActionBar().setTitle("Course Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnpurchase.setVisibility(View.GONE);
        final Intent intent = getIntent();
        courseid = intent.getStringExtra("courseid");
        progressDialog = new ProgressDialog(ViewDetail_Activity.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Waiting..");
        progressDialog.show();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        product = new ArrayList<>();
        //// view course  detail ______________///
        StringRequest stringRequest = new StringRequest(Request.Method.POST, VIEWCOURSEDETAIL_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // String status=jsonObject.getString("status");
                    JSONArray jsonArray = jsonObject.getJSONArray("product_detail");
                    // if (status.equals("success")){
                    for (int is = 0; is < jsonArray.length(); is++) {
                        JSONObject productv = jsonArray.getJSONObject(is);
                        courseId = productv.getString("id");
                        coursename = productv.getString("course_name");

                        coursedescription = productv.getString("course_description");
                        courseprice = productv.getString("course_price");
                        coursegroup = productv.getString("course_group");

                        tvcourse.setText(coursename);
                        tvdescription.setText(coursedescription);
                        tvprice.setText(courseprice);
                        tvcourseGroup.setText(coursegroup);
                        if (!courseprice.isEmpty()) {
                            btnpurchase.setVisibility(View.VISIBLE);
                        }

                    }

                    // }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(ViewDetail_Activity.this, "connectin Fail", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parm = new HashMap<>();
                parm.put("id", courseid);
                return parm;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

        //// view course  detail end ______________///

        //// view subject  detail start ______________///

  tvsubjectdetail.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          StringRequest stringRequestsub = new StringRequest(Request.Method.POST, VIEWSUBJECTDETAIL_URL, new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                   progressDialog.dismiss();
                   tvsubjectdetail.setEnabled(false);
                   tvsubtitle.setVisibility(View.VISIBLE);
                   tvsubdes.setVisibility(View.VISIBLE);

                  try {
                      JSONObject jsonObjects = new JSONObject(response);
                      // String status=jsonObject.getString("status");
                      JSONArray jsonArrays = jsonObjects.getJSONArray("product_detail");
                      // if (status.equals("success")){
                      for (int i = 0; i < jsonArrays.length(); i++) {
                          JSONObject subject = jsonArrays.getJSONObject(i);
                          //courseId = productv.getString("id");
                          //coursename = subject.getString("course_name");

                          subname = subject.getString("subject_name");
                          subdescription = subject.getString("subject_description");

                          SubjectDeatail_Modal subjectDeatailModal = new SubjectDeatail_Modal();

                          subjectDeatailModal.setSubname(subname);
                          subjectDeatailModal.setSubdescription(subdescription);
                          product.add(subjectDeatailModal);

                          SubjectDetail_Adapter adapter = new SubjectDetail_Adapter(ViewDetail_Activity.this, product);
                          recyclerView.setAdapter(adapter);

                      }

                      // }
                  } catch (JSONException e) {
                      e.printStackTrace();
                  }

              }
          }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                   progressDialog.dismiss();
                  Toast.makeText(ViewDetail_Activity.this, "connectin Fail" , Toast.LENGTH_SHORT).show();
              }
          }) {
              @Override
              protected Map<String, String> getParams() throws AuthFailureError {
                  Map<String, String> parms = new HashMap<>();
                  parms.put("course_name", coursename);
                  return parms;
              }
          };
          RequestQueue requestQueuesub = Volley.newRequestQueue(ViewDetail_Activity.this);
          requestQueuesub.add(stringRequestsub);
      }
  });


        //// view subject  detail end ______________///


        btnpurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginShareprefe_Modal loginShareprefeModal = new LoginShareprefe_Modal(getApplicationContext());
                String email = loginShareprefeModal.sharedPreLogin.getString("email", null);
                String uname = loginShareprefeModal.sharedPreLogin.getString("name", null);
                String umobile = loginShareprefeModal.sharedPreLogin.getString("mobile", null);

                if (email != null) {
                    Intent intentpurche = new Intent(getApplicationContext(), Select_Paymen_Methode_Activity.class);
                    intentpurche.putExtra("courseid", courseid);
                    intentpurche.putExtra("course", coursename);
                    intentpurche.putExtra("courseprice", courseprice);
                    intentpurche.putExtra("email", email);
                    intentpurche.putExtra("name", uname);
                    intentpurche.putExtra("mobile", umobile);

                    startActivity(intentpurche);
                } else {
                    toast = Toast.makeText(getApplicationContext(), "Please Sign In", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }


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


}