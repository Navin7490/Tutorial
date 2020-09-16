package com.example.tutorial;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Course_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Course_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    ArrayList<Course_Modal>productcourse;
    RecyclerView.LayoutManager layoutManager;
    View v;
    String VIEWCOURSES_URL="http://192.168.43.65/tutorial/api/ViewCourses.php";

    String courseid, cours,courseprice,description,image;
    ProgressDialog progressDialog;
    Toast toast;
    public Course_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Course_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Course_Fragment newInstance(String param1, String param2) {
        Course_Fragment fragment = new Course_Fragment();
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
        v= inflater.inflate(R.layout.fragment_course_, container, false);
        recyclerView=v.findViewById(R.id.Rv_Home_Courses);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        productcourse=new ArrayList<>();

     progressDialog=new ProgressDialog(getContext());
     progressDialog.show();
     progressDialog.setMessage("Please waiting");
     progressDialog.setCanceledOnTouchOutside(false);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, VIEWCOURSES_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              progressDialog.dismiss();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("course_detail");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject Courses=jsonArray.getJSONObject(i);

                        courseid=Courses.getString("id");
                        cours=Courses.getString("course_name");
                        courseprice=Courses.getString("course_description");
                        description=Courses.getString("course_description");
                        image=Courses.getString("course_image");
                        Course_Modal course_modal=new Course_Modal();

                        course_modal.setCourseId(courseid);
                        course_modal.setCoursename(cours);
                        course_modal.setCoursedescription(description);
                        course_modal.setCoursePrice(courseprice);
                        course_modal.setCourseimage(image);
                        productcourse.add(course_modal);
                        Course_Adapter adapter=new Course_Adapter(getContext(),productcourse);
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
                toast=Toast.makeText(getContext(),"No Connection",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

        return v;
    }
}