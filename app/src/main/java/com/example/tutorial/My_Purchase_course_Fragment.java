package com.example.tutorial;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
   String MYCOURSE_URL="http://192.168.43.65/tutorial/api/mycourse.php";
   String uemail;
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
        LoginShareprefe_Modal loginShareprefeModal=new LoginShareprefe_Modal(getContext());
        uemail=loginShareprefeModal.sharedPreLogin.getString("email",null);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, MYCOURSE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("order_detail");

                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject purches=jsonArray.getJSONObject(i);
                        String orderid=purches.getString("order_id");
                        String coursename=purches.getString("course_name");

                        MyPurchaseCourse_Modal modal=new MyPurchaseCourse_Modal();
                        modal.setPurchaseId(orderid);
                        modal.setCoursename(coursename);
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
                Toast.makeText(getContext(), "No connection", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>parm=new HashMap<>();
                parm.put("u_email",uemail);
                return parm;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);


        return v;
    }
}