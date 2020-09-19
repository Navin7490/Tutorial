package com.example.tutorial;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.TextView;
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

public class Tutorial_Details_Activity extends Activity {
   RecyclerView recyclerView,recyclerViewdemo;
   TextView tvcoursename;
   ArrayList<LessonModal>lessonlist;
   String subject,coures,videourl,video;
   VideoView videodemo;
   String COURSE_DETAIL_URL="http://192.168.43.65/tutorial/api/mycourseSubjectVideo.php";
   ProgressDialog progressDialog;
   Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial__details_);
       // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        getSupportActionBar().setTitle("Course Detail");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        videodemo=findViewById(R.id.Video_Demo);
        tvcoursename=findViewById(R.id.Tv_CourseDetailName);

       // String videoPath="android.resource://"+getPackageName()+"/"+R.raw.video;
        Intent intent=getIntent();
        coures=intent.getStringExtra("course_name");
        subject=intent.getStringExtra("subject_name");

//        Intent intent1=getIntent();
//        video=intent1.getStringExtra("video");
//
//       uri=Uri.parse(video);
//        videodemo.setVideoPath(String.valueOf(uri));

        MediaController mediaController=new MediaController(Tutorial_Details_Activity.this);
        videodemo.setMediaController(mediaController);
        mediaController.setAnchorView(videodemo);
        tvcoursename.setText(coures+" Wel come to "+subject+" Tutorial");

        recyclerViewdemo=findViewById(R.id.Rv_Demovideo);
        recyclerView=findViewById(R.id.Rv_LessonList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        lessonlist=new ArrayList<>();

        recyclerViewdemo.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerViewdemo.setHasFixedSize(true);

//        productdemo=new ArrayList<>();
//
//        productdemo.add(new LessonDemo_Modal("Java basic demo video1",R.raw.video));
//        productdemo.add(new LessonDemo_Modal("Java basic demo video2",R.raw.video));
//        productdemo.add(new LessonDemo_Modal("Java basic demo video3",R.raw.video));
//
//        LessionDemo_Adapter adapter=new LessionDemo_Adapter(getApplicationContext(),productdemo);
//        recyclerViewdemo.setAdapter(adapter);




        progressDialog=new ProgressDialog(getApplicationContext());
        progressDialog.setMessage("Please Wait");
        progressDialog.setCanceledOnTouchOutside(false);
       // progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, COURSE_DETAIL_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("video_detail");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject coursedetail=jsonArray.getJSONObject(i);
                        String videotitle=coursedetail.getString("videotitle");
                        String videourl=coursedetail.getString("videourl");

                        LessonModal lessonModal=new LessonModal();
                        lessonModal.setLessonName(videotitle);
                        lessonModal.setVideourl(videourl);
                        lessonlist.add(lessonModal);

                        LessonAdapter adapter=new LessonAdapter(getApplicationContext(),lessonlist);
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
                Toast.makeText(Tutorial_Details_Activity.this, "No Connection"+error, Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>parm=new HashMap<>();
                parm.put("course_name",coures);
                parm.put("subject_name",subject);
                return parm;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}