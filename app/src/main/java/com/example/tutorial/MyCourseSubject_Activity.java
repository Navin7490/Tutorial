package com.example.tutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyCourseSubject_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<MyCourseSubject_Modal> prduct;
    String SUBJECT_URL = "http://192.168.43.65/tutorial/api/mycourseSubject.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_course_subject_);
        recyclerView = findViewById(R.id.Rv_My_Purchse_Subject);
        Intent intent = getIntent();
        final String coursename = intent.getStringExtra("course_name");


        getSupportActionBar().setTitle("Subject");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        prduct = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        StringRequest stringRequest = new StringRequest(Request.Method.POST, SUBJECT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("subject_detail");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject courseSubject = jsonArray.getJSONObject(i);
                        String subjectcousre = courseSubject.getString("course_name");
                        String subject = courseSubject.getString("subject_name");

                        MyCourseSubject_Modal modal = new MyCourseSubject_Modal();
                        modal.setSubcourse(subjectcousre);
                        modal.setSubjectname(subject);
                        prduct.add(modal);
                        MyCourseSubject_Adapter adapter = new MyCourseSubject_Adapter(getApplicationContext(), prduct);
                        recyclerView.setAdapter(adapter);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "No connection", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parm = new HashMap<>();
                parm.put("course_name", coursename);
                return parm;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}