package vision.madhvi.tutorial.ui.activity;

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

import vision.madhvi.tutorial.adapter.MyCourseSubject_Adapter;
import vision.madhvi.tutorial.model.MyCourseSubject_Modal;
import vision.madhvi.tutorial.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyCourseSubject_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<MyCourseSubject_Modal> prduct;
    //String SUBJECT_URL = "http://192.168.43.65/tutorial/api/mycourseSubject.php";
    String couserid;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_course_subject_);
        recyclerView = findViewById(R.id.Rv_My_Purchse_Subject);
        Intent intent = getIntent();
        couserid = intent.getStringExtra("couserid");
        progressDialog=new ProgressDialog(MyCourseSubject_Activity.this);


        getSupportActionBar().setTitle("Subject");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        prduct = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Waite");
        progressDialog.show();
        String SUBJECT_URL = "http://103.207.169.120:8891/api/Subject/"+couserid;


        StringRequest stringRequest = new StringRequest(Request.Method.GET, SUBJECT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("subjects");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject courseSubject = jsonArray.getJSONObject(i);
                        String SubjectId = courseSubject.getString("SubjectId");
                        String subject = courseSubject.getString("SubjectName");
                        String description = courseSubject.getString("Description");

                        String image=courseSubject.getString("CoverPage");

                        MyCourseSubject_Modal modal = new MyCourseSubject_Modal();
                        modal.setSubid(SubjectId);
                        modal.setSubjectname(subject);
                        modal.setDescription(description);
                        modal.setImage(image);
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