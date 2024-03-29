package vision.madhvi.tutorial.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import vision.madhvi.tutorial.model.MyPurchaseCourse_Modal;
import vision.madhvi.tutorial.adapter.MyPurchase_Adapter;
import vision.madhvi.tutorial.R;
import vision.madhvi.tutorial.model.LoginShareprefe_Modal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link My_Purchase_course_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class My_Purchase_course_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
   RecyclerView recyclerView;
   ArrayList<MyPurchaseCourse_Modal>product;
   View v;
  // String MYCOURSE_URL="http://192.168.43.65/tutorial/api/mycourse.php";
   String contactId;
   ProgressDialog progressDialog;
    public My_Purchase_course_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment My_Purchase_course_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static My_Purchase_course_Fragment newInstance(String param1, String param2) {
        My_Purchase_course_Fragment fragment = new My_Purchase_course_Fragment();
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
        v= inflater.inflate(R.layout.fragment_my__purchase_course_, container, false);
        recyclerView=v.findViewById(R.id.Rv_My_Purchase_course);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        product=new ArrayList<>();
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Please Waite");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        LoginShareprefe_Modal loginShareprefeModal=new LoginShareprefe_Modal(getContext());
        contactId=loginShareprefeModal.sharedPreLogin.getString("contactId",null);
        String MYCOURSE_URL="http://103.207.169.120:8891/api/Course?ContactId="+contactId;

        StringRequest stringRequest=new StringRequest(Request.Method.GET, MYCOURSE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("courses");

                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject purches=jsonArray.getJSONObject(i);
                        String CourseId=purches.getString("CourseId");
                        String coursename=purches.getString("CourseName");
                        String image=purches.getString("Path");
                        String description=purches.getString("Description");

                        MyPurchaseCourse_Modal modal=new MyPurchaseCourse_Modal();
                        modal.setPurchaseId(CourseId);
                        modal.setCoursename(coursename);
                        modal.setCoursedetail(description);
                        modal.setImage(image);
                        product.add(modal);
                        MyPurchase_Adapter adapter=new MyPurchase_Adapter(getContext(),product);
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
                Toast.makeText(getContext(), "No connection", Toast.LENGTH_SHORT).show();
            }
        });
//
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String>parm=new HashMap<>();
//                parm.put("u_email",contactId);
//                return parm;
//            }
//        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);


        return v;
    }
}