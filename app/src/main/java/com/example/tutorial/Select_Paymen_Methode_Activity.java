package com.example.tutorial;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.card.MaterialCardView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Select_Paymen_Methode_Activity extends AppCompatActivity {


    String ORDER_URL="http://192.168.43.65/tutorial/api/Insert_Order.php";

    /// paytm start //
  public  static final String PAYTM_PACKAGE_NAME="net.one97.paytm";
    /// paytm end //

    ImageButton btnGpay, btnPatmpay;

    /// google pay ///
    private static final int TEZ_REQUEST_CODE = 123;
    private static final String GOOGLE_TEZ_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
    Uri uri;
    String upiid = "tinu1316@oksbi";
    String name = "Navin";
    String note = "Madhvi vision Trasaction";
    String staus;
    Toast toast;
    String course, uemail, courseprice,uname,umobile;
 /// end ///

    // paytm//
    Uri uripaytm;
    String pname = "Navin";
    String paytId = "tinu1316@oksbi";
    String msgnote = "Madhvi vision";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__paymen__methode_);
        getSupportActionBar().setTitle("Select Payment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnGpay = findViewById(R.id.Btn_CardGpay);
        btnPatmpay = findViewById(R.id.Btn_CardPaytmpay);

        Intent intent = getIntent();
        course = intent.getStringExtra("course");
        courseprice = intent.getStringExtra("courseprice");
        uemail = intent.getStringExtra("email");
        uname = intent.getStringExtra("name");
        umobile = intent.getStringExtra("mobile");

        ///// Start Paytm pay payment event start /////////


        btnPatmpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uripaytm = getPayTmUri(pname, paytId, msgnote, courseprice);
                PayWithPaytm(PAYTM_PACKAGE_NAME);

            }
        });

        ///// End Paytm pay payment event /////////

        ///// google pay pament event start /////////

        btnGpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!courseprice.equals("")) {

                    uri = getUPIPaymentUri(name, upiid, note, courseprice);
                    PayWithGpay(GOOGLE_TEZ_PACKAGE_NAME);
                }


            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == TEZ_REQUEST_CODE) {
//            // Process based on the data in response.
//            Log.d("result", data.getStringExtra("Status"));
//        }
        if (data!=null){
            staus=data.getStringExtra("Status").toLowerCase();
        }
        if ((RESULT_OK==resultCode )&& staus.equals("success")){
            Toast.makeText(getApplicationContext(), "Trasaction successfull", Toast.LENGTH_SHORT).show();
            InsertMyOrder();
            Intent intenth=new Intent(getApplicationContext(),Home_User_Activity.class);
            startActivity(intenth);
        }

        else {
            Toast.makeText(getApplicationContext(), "Trasaction Cancel by user", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(getApplicationContext(),Home_User_Activity.class);
            startActivity(intent);
            finish();
        }

    }

    private void PayWithGpay(String PackegeManeger) {

        if (isAppinstalled(Select_Paymen_Methode_Activity.this, GOOGLE_TEZ_PACKAGE_NAME)) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setPackage(GOOGLE_TEZ_PACKAGE_NAME);
            startActivityForResult(intent, 123);
        } else {
            toast = Toast.makeText(Select_Paymen_Methode_Activity.this, "Google pay is not installed", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    private static Uri getUPIPaymentUri(String name, String UPIId, String note, String amount) {
        return new Uri.Builder()
                .scheme("upi")
                .authority("pay")
                .appendQueryParameter("pa", UPIId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                .build();

    }

    public static boolean isAppinstalled(Context context, String PackgeManeger) {
        try {
            context.getPackageManager().getApplicationInfo(PackgeManeger, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /////  End google pay payment event /////////


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    /// paytm method  ////
    private static Uri getPayTmUri(String name, String upiId, String note, String amount) {
        return new Uri.Builder()
                .scheme("upi")
                .authority("pay")
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                .build();

    }

    private void PayWithPaytm(String PPackagename) {
        if (isAnstalledPaytm(this, PAYTM_PACKAGE_NAME)) {

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(uripaytm);
            i.setPackage(PAYTM_PACKAGE_NAME);
            startActivityForResult(i, 123);
        }
        else {
            Toast.makeText(this, "Paytm is not istalled please install and try again", Toast.LENGTH_SHORT).show();
        }
    }

    /// paytm ///


    /// cheking patym is istalledor not//

    public static boolean isAnstalledPaytm(Context context, String PackegeName) {
        try {
            context.getPackageManager().getApplicationInfo(PackegeName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;

        }
    }

    public  void InsertMyOrder(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, ORDER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //progressDialog.dismiss();
                Log.d("order",response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String status=jsonObject.getString("status");
                    if (status.equals("success")){
                        toast=Toast.makeText(Select_Paymen_Methode_Activity.this, "Purchase Course Confirm", Toast.LENGTH_SHORT);
                        toast.show();
                        toast.setGravity(Gravity.CENTER,0,0);


                    }
                    else{
                        Toast.makeText(Select_Paymen_Methode_Activity.this, "fail", Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Select_Paymen_Methode_Activity.this, "try again connection fail", Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>parms=new HashMap<>();
                parms.put("order_id","");
                parms.put("course_name",course);
                parms.put("u_name",uname);
                parms.put("u_email",uemail);
                parms.put("u_phone",umobile);
                return parms;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(Select_Paymen_Methode_Activity.this);
        requestQueue.add(stringRequest);
    }

}