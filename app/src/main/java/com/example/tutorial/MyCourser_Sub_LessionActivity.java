package com.example.tutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

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

public class MyCourser_Sub_LessionActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    String subjectid;
    ProgressDialog progressDialog;
     ArrayList<MySub_Lession_Modal>products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_courser__sub__lession);
        getSupportActionBar().setTitle("Select Lession");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        subjectid = intent.getStringExtra("subjectid");
        recyclerView = findViewById(R.id.Rv_Sub_Lessoin);
        recyclerView.setHasFixedSize(true);
        products = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        progressDialog = new ProgressDialog(MyCourser_Sub_LessionActivity.this);
        progressDialog.setMessage("Please Wite");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        String LESSION_URL = "http://103.207.169.120:8891/api/Lesson/"+subjectid;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, LESSION_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("lessons");
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject lessoindetail = jsonArray.getJSONObject(i);
                        String id = lessoindetail.getString("LessonId");
                        String name = lessoindetail.getString("LessonName");

                        MySub_Lession_Modal lessonModal = new MySub_Lession_Modal();
                        lessonModal.setLessionid(id);
                        lessonModal.setLessiontitle(name);
                        products.add(lessonModal);

                        MySub_Lession_Adapter adapter = new MySub_Lession_Adapter(getApplicationContext(), products);
                        recyclerView.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();

                Toast.makeText(getApplicationContext(), "No connection", Toast.LENGTH_SHORT).show();
            }
        });

//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> parm = new HashMap<>();
//                parm.put("course_name", couserid);
//                return parm;
//            }
//        };
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