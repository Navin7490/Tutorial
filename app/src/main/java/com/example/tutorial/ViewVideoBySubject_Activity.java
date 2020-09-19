package com.example.tutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.VideoView;

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

public class ViewVideoBySubject_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<LessonModal> lessonlist;
    String subject, lessionid, videourl, video;
    VideoView videodemo;
    //String COURSE_DETAIL_URL="http://192.168.43.65/tutorial/api/mycourseSubjectVideo.php";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_video_by_subject_);
        recyclerView = findViewById(R.id.Rv_VideobySub);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        lessionid = intent.getStringExtra("lessionid");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        lessonlist = new ArrayList<>();
        progressDialog = new ProgressDialog(ViewVideoBySubject_Activity.this);
        progressDialog.setMessage("Please Waite");
        progressDialog.show();
        String LESSION_VIDEO_URL = "http://103.207.169.120:8891/api/Tutorial/" + lessionid;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, LESSION_VIDEO_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("tutorials");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject coursedetail = jsonArray.getJSONObject(i);
                        String videoid = coursedetail.getString("TutorialId");
                        String videotitle = coursedetail.getString("TutorialName");
                        String videourl = coursedetail.getString("Path");

                        LessonModal lessonModal = new LessonModal();
                        lessonModal.setVideoId(videoid);
                        lessonModal.setLessonName(videotitle);
                        lessonModal.setVideourl(videourl);
                        lessonModal.setTvlink(videourl);
                        lessonlist.add(lessonModal);

                        LessonAdapter adapter = new LessonAdapter(getApplicationContext(), lessonlist);
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
                Toast.makeText(ViewVideoBySubject_Activity.this, "No Connection" + error, Toast.LENGTH_SHORT).show();

            }
        }) ;

//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> parm = new HashMap<>();
//                parm.put("course_name", coures);
//                parm.put("subject_name", subject);
//                return parm;
//            }
//        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}