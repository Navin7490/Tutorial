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

import org.json.JSONArray;
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
        btnpay = findViewById(R.id.BtnPay);

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
               // SetUpPayment();
                InsertMyOrder();
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
            options.put("amount",amount );//pass amount in currency subunits
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
        String PURCHASE_URL="http://103.207.169.120:8891/api/Sales";
        StringRequest stringRequestpurchase=new StringRequest(Request.Method.POST, PURCHASE_URL, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {

               try {
                   JSONObject jsonObjectpu=new JSONObject(response);

                   String stspurchobject=jsonObjectpu.getString("msg");
                   if (stspurchobject.equals("Inserted Successfully")){
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
       }){
           @Override
           protected Map<String, String> getParams() throws AuthFailureError {

               Map<String,String>parmiter=new HashMap<>();
               parmiter.put("Action","Insert");
               parmiter.put("BillNo","0");
               parmiter.put("BillDate","02/11/2020");
               parmiter.put("ContactId",contactid);
               parmiter.put("CourseId",courseid);
               parmiter.put("TaxType","0");
               parmiter.put("Tax","0");
               parmiter.put("DiscountType","0");
               parmiter.put("Discount","0");
               parmiter.put("BillAmount",courseprice);
               parmiter.put("Comment","Comment");
               parmiter.put("MOP","MOP");
               parmiter.put("Name",fname);
               parmiter.put("Bank","0");
               parmiter.put("Branch","0");
               parmiter.put("IFSC","0");
               parmiter.put("Card","0");
               parmiter.put("ExpirationDate","30/11/2020");
               parmiter.put("CVV","123");
               parmiter.put("Mobile",umobile);
               parmiter.put("EmailId",uemail);
               parmiter.put("MatchAmount",courseprice);


               return parmiter;
           }
       };
       RequestQueue queue=Volley.newRequestQueue(Payment_Activity.this);
       queue.add(stringRequestpurchase);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}