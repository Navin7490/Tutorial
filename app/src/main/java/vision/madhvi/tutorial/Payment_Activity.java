package vision.madhvi.tutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Payment_Activity extends AppCompatActivity implements PaymentResultListener {

    Toast toast;
    String contactid,courseid, uemail, courseprice, fname, umobile;
    TextView tvamountpayble;
    Button btnpay;

    String totalamount;
    double total;
    String amoutpayble = "Amount Payable ₹:";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_);

        getSupportActionBar().setTitle("Payment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvamountpayble=findViewById(R.id.Tv_amoutapayable);
        btnpay = findViewById(R.id.BtnPurchaese);

        Intent intent = getIntent();
        contactid=intent.getStringExtra("contactId");
        courseid = intent.getStringExtra("courseid");
        courseprice = intent.getStringExtra("courseprice");
        uemail = intent.getStringExtra("email");
        fname = intent.getStringExtra("name");
        umobile = intent.getStringExtra("mobile");
        total= Double.parseDouble(courseprice);
        ///// Start Paytm pay payment event start /////////
        tvamountpayble.setText(amoutpayble.concat(courseprice));

        btnpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetUpPayment();
            }
        });
    }
    public void SetUpPayment(){
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_live_v1lezGFouPayNY");
        Checkout.preload(getApplicationContext());
        totalamount = String.valueOf(total * 100);
        int amount=1*100;
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", fname);
            options.put("description", " PAYING");
           // options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");

            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //  options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#2196f3");
            options.put("currency", "INR");
            options.put("amount",totalamount );//pass amount in currency subunits
            options.put("prefill.email", uemail);
            options.put("prefill.contact", umobile);
            checkout.open(activity, options);
        } catch (Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
            toast=    Toast.makeText(this, "Error in starting Razorpay Checkout" , Toast.LENGTH_LONG);
            toast.show();
            toast.setGravity(Gravity.CENTER, 0, 0);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        InsertMyOrder();
        toast=  Toast.makeText(this, "Payment SuccessFull" , Toast.LENGTH_LONG);
        toast.show();
        toast.setGravity(Gravity.CENTER, 0, 0);
    }

    @Override
    public void onPaymentError(int i, String s) {
        toast=    Toast.makeText(this, "Transaction Failed" , Toast.LENGTH_LONG);
        toast.show();
        toast.setGravity(Gravity.CENTER, 0, 0);

    }
    public void InsertMyOrder(){
        String ORDER_URL="http://103.207.169.120:8891/api/Sales";
        StringRequest stringRequest = new StringRequest(Request.Method.POST,ORDER_URL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //progressDialog.dismiss();
                Log.d("order", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("msg");
                    //String failstatus=jsonObject.getString("Message");

                   if (status.equals("Inserted Successfully")) {
                        toast = Toast.makeText(Payment_Activity.this, "Purchase Course Confirm", Toast.LENGTH_LONG);
                        toast.show();
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        Intent intent=new Intent(getApplicationContext(),Home_User_Activity.class);
                        startActivity(intent);
                        finish();


                    }

                    else {
                        toast = Toast.makeText(Payment_Activity.this, "Something Went Wrong", Toast.LENGTH_LONG);
                        toast.show();
                        toast.setGravity(Gravity.CENTER, 0, 0);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                toast = Toast.makeText(Payment_Activity.this, "try again connection fail", Toast.LENGTH_LONG);
                toast.show();
                toast.setGravity(Gravity.CENTER, 0, 0);

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parms = new HashMap<>();
                parms.put("Action","Insert");
                parms.put("BillNo","0");
                parms.put("BillDate","03/10/2020");
                parms.put("ContactId",contactid);
                parms.put("CourseId",courseid);
                parms.put("TaxType", "0");
                parms.put("Tax", "0");
                parms.put("DiscountType", "0");
                parms.put("Discount", "0");
                parms.put("BillAmount",courseprice);
                parms.put("Comment", "Comment");
                parms.put("MOP", "G pay");
                parms.put("Name",fname);
                parms.put("Bank", "0");
                parms.put("Branch", "0");
                parms.put("IFSC", "0");
                parms.put("Card", "0");
                parms.put("ExpirationDate", "12/12/2020");
                parms.put("CVV", "123");
                parms.put("Mobile", umobile);
                parms.put("EmailId", uemail);
                parms.put("MatchAmount",courseprice);

                return parms;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Payment_Activity.this);
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