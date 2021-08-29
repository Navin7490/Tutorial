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

import vision.madhvi.tutorial.adapter.MySub_Lession_Adapter;
import vision.madhvi.tutorial.model.MySub_Lession_Modal;
import vision.madhvi.tutorial.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Mysub_Lession_Activity extends AppCompatActivity {
     RecyclerView recyclerView;
     ArrayList<MySub_Lession_Modal>product;
     String subjectid;
     ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysub__lession_);

        getSupportActionBar().setTitle("Select Lesson");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        progressDialog=new ProgressDialog(Mysub_Lession_Activity.this);
        progressDialog.setMessage("Please Waite");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        Intent intent=getIntent();
        subjectid=intent.getStringExtra("subjectid");

        recyclerView=findViewById(R.id.Rv_Sub_lession);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        product=new ArrayList<>();
        String LESSION_URL="http://103.207.169.120:8891/api/Lesson/"+subjectid;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, LESSION_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
        progressDialog.dismiss();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("lessons");

                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject lessiondetail=jsonArray.getJSONObject(i);
                        String  lid=lessiondetail.getString("LessonId");
                        String lname=lessiondetail.getString("LessonName");

                        MySub_Lession_Modal modal=new MySub_Lession_Modal();

                        modal.setLessionid(lid);
                        modal.setLessiontitle(lname);
                        product.add(modal);
                        MySub_Lession_Adapter adapter=new MySub_Lession_Adapter(getApplicationContext(),product);
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
                Toast.makeText(Mysub_Lession_Activity.this, "No Connection", Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue queue= Volley.newRequestQueue(Mysub_Lession_Activity.this);
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