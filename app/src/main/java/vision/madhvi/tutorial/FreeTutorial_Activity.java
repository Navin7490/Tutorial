package vision.madhvi.tutorial;

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

public class FreeTutorial_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<FreeTutorial_Modal>product;
    String coursid;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_tutorial_);
        getSupportActionBar().setTitle("Free Video");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        progressDialog=new ProgressDialog(FreeTutorial_Activity.this);
        progressDialog.setMessage("Please Waite");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        Intent intent=getIntent();
        coursid=intent.getStringExtra("courseid");

        recyclerView=findViewById(R.id.Rv_FreeTutorila);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        product=new ArrayList<>();
        String FREEVIDEO_URL="http://103.207.169.120:8891/api/FreeTutorial/" + coursid;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, FREEVIDEO_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("freeTutorials");

                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject freedemo=jsonArray.getJSONObject(i);
                        String  ftid=freedemo.getString("TutorialId");
                        String ftname=freedemo.getString("TutorialName");
                        String ftdescription=freedemo.getString("Description");
                        String ftpath=freedemo.getString("Path");
                        String ftlessionid=freedemo.getString("LessonId");
                        String ftorderid=freedemo.getString("OrderId");


                        FreeTutorial_Modal modal=new FreeTutorial_Modal();

                        modal.setFtutorialid(ftid);
                        modal.setFtutorialname(ftname);
                        modal.setFtutorialdescription(ftdescription);
                        modal.setFtutorialpath(ftpath);
                        modal.setFtutoriallessionid(ftlessionid);
                        modal.setFtutorialorderid(ftorderid);

                        product.add(modal);

                    }
                    FreeTutorial_Adapter adapter=new FreeTutorial_Adapter(getApplicationContext(),product);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(FreeTutorial_Activity.this, "No Connection", Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue queue= Volley.newRequestQueue(FreeTutorial_Activity.this);
        queue.add(stringRequest);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

