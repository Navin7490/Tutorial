package vision.madhvi.tutorial;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
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
    String courseid, cours, courseprice, description, image, coursegroupid,coursegroupname, orderid;

    RecyclerView recyclerView;
    ArrayList<Course_Modal> productcourse;
    RecyclerView.LayoutManager layoutManager;
    View v;

    //String VIEWCOURSES_URL = "http://192.168.43.65/tutorial/api/ViewCourses.php";

    ProgressDialog progressDialog;
    Toast toast;
    Spinner spinner;
    String viewgroupdata;
    String group;
    String groupid;
    ArrayList<String> productcoursegroup;
    String COURSE_GOUP_URL = "http://103.207.169.120:8891/api/CourseGroup";
    JSONObject course;
    Dialog dialog;
    ArrayList<Group_Modal> productgroup;

    Group_Adapter adaptergroup;

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
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.all_courses_group);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_course_, container, false);
        recyclerView = v.findViewById(R.id.Rv_Home_Courses);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        productcourse = new ArrayList<>();
        spinner = v.findViewById(R.id.SP_Courese_group);
        productcoursegroup = new ArrayList<>();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please waiting");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        productgroup = new ArrayList<>();


        StringRequest stringRequestSpinner = new StringRequest(Request.Method.GET, COURSE_GOUP_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("courseGroups");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        course = jsonArray.getJSONObject(i);
                        coursegroupname = course.getString("CourseGroupName");
                        groupid = course.getString("CourseGroupId");
                        Group_Modal groupModal = new Group_Modal();
                        groupModal.setGroupid(groupid);
                        groupModal.setGroupname(coursegroupname);
                        productgroup.add(groupModal);
                        adaptergroup = new Group_Adapter(getContext(), productgroup);
                        spinner.setAdapter(adaptergroup);

                        //   ArrayAdapter<String> adaptertt = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_item, productcoursegroup);
                        //  spinner.setAdapter(adaptertt);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();


                Toast.makeText(getContext(), "No connection", Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(stringRequestSpinner);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                progressDialog.show();
                // group=   spinner.getFirstVisiblePosition();
                // group= String.valueOf(spinner.getItemIdAtPosition(position+1));


                final Group_Modal groupModal = (Group_Modal) parent.getItemAtPosition(position);

                String gname = groupModal.getGroupname();
                String gid = groupModal.getGroupid();


                String VIEWCOURSES_URL = "http://103.207.169.120:8891/api/Course/" + gid;
                StringRequest stringRequest = new StringRequest(Request.Method.GET, VIEWCOURSES_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.d("response", response);
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("courses");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject Courses = jsonArray.getJSONObject(i);

                                courseid = Courses.getString("CourseId");
                                cours = Courses.getString("CourseName");
                                courseprice = Courses.getString("Price");
                                description = Courses.getString("Description");
                                coursegroupid = Courses.getString("CourseGroupId");
                                image = Courses.getString("Path");
                                Group_Modal groupModal1=new Group_Modal();
                                String groupname=groupModal1.getGroupname();
                                Course_Modal course_modal = new Course_Modal();
                                course_modal.setCourseId(courseid);
                                course_modal.setCoursename(cours);
                                course_modal.setCoursedescription(description);
                                course_modal.setCoursePrice(courseprice);
                                course_modal.setCourseGroupId(coursegroupid);
                                course_modal.setCourseGroupname(groupname);
                                course_modal.setCourseimage(image);
                                productcourse.add(course_modal);
                                Course_Adapter adapter = new Course_Adapter(getContext(), productcourse);
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
                        toast = Toast.makeText(getContext(), "No Connection", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();

                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                requestQueue.add(stringRequest);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return v;
    }

}

