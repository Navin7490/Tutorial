package vision.madhvi.tutorial.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import vision.madhvi.tutorial.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Select_Paymen_Methode_Activity extends AppCompatActivity {


   // String ORDER_URL = "http://192.168.43.65/tutorial/api/Insert_Order.php";
   String ORDER_URL = "http://103.207.169.120:8891/api/Sales";

    /// paytm start //
    public static final String PAYTM_PACKAGE_NAME = "net.one97.paytm";
    /// paytm end //
    public static final String PHONE_PAY_PACKAGE_NAME="com.phonepe.app";

    public static final String PAYPAL_PAY_PACKAGE_NAME="com.paypal.android.p2pmobile";
    ImageView btnGpay, btnPatmpay,btnPhonpe,btnPaypal;

    /// google pay ///
    private static final int TEZ_REQUEST_CODE = 123;
    private static final String GOOGLE_TEZ_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";

    String upiid = "hiteshkumar685@okhdfcbank";
    String name = "Digital learning by Navin";

   //  String upiid = "dhruvmicro@oksbi";
   // String name = "DHRUV PRASAD SHARMA";
    String note = "Paying For Madhvi vision";
    String staus;
    Toast toast;
    String contactid,courseid, uemail, courseprice, fname, umobile;
    TextView tvamountpayble;
    /// end ///

    // paytm//
   String courseamount="15";
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__paymen__methode_);
        getSupportActionBar().setTitle("Select Payment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvamountpayble=findViewById(R.id.Tv_amoutapayable);
        btnGpay = findViewById(R.id.Im_Pay_Gpay);
        btnPatmpay = findViewById(R.id.Im_Pay_Paytm);
        btnPhonpe=findViewById(R.id.Im_Pay_Phone);
        btnPaypal=findViewById(R.id.Im_Pay_PayPaypal);

        Intent intent = getIntent();
        contactid=intent.getStringExtra("contactId");
        courseid = intent.getStringExtra("courseid");
        courseprice = intent.getStringExtra("courseprice");
        uemail = intent.getStringExtra("email");
        fname = intent.getStringExtra("name");
        umobile = intent.getStringExtra("mobile");

        ///// Start Paytm pay payment event start /////////
        tvamountpayble.setText(courseprice);

        btnPatmpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          if (!courseprice.equals("")) {
              uri = getUPIPaymentUri(name, upiid, note, courseprice);
              PayWithPaytm(PAYTM_PACKAGE_NAME);
          }
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
        btnPhonpe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!courseprice.equals("")) {
                    uri = getUPIPaymentUri(name, upiid, note, courseprice);
                    PayWithPhonePay(PHONE_PAY_PACKAGE_NAME);
                }
            }
        });
        btnPaypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!courseprice.equals("")) {
                    uri = getUPIPaymentUri(name, upiid, note, courseprice);
                    PayWithPayPal(PAYPAL_PAY_PACKAGE_NAME);
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
        if (data != null) {
            staus = data.getStringExtra("Status").toLowerCase();
        }
        if ((RESULT_OK == resultCode) && staus.equals("success")) {
            toast = Toast.makeText(getApplicationContext(), "Trasaction successfull", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            InsertMyOrder();
            Intent intenth = new Intent(getApplicationContext(), Home_User_Activity.class);
            startActivity(intenth);
            finish();
        } else {
            toast = Toast.makeText(getApplicationContext(), "Trasaction Cancel by user", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            Intent intent = new Intent(getApplicationContext(), Home_User_Activity.class);
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

    private void PayWithPhonePay(String phonePayPackageName) {
        if (isAnstalledPhonePay(this, PHONE_PAY_PACKAGE_NAME)) {

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(uri);
            i.setPackage(PHONE_PAY_PACKAGE_NAME);
            startActivityForResult(i, 123);
        } else {
            Toast.makeText(this, "PhonePe is not istalled", Toast.LENGTH_SHORT).show();
        }
    }


    private void PayWithPayPal(String paypalPayPackageName) {
        if (isAnstalledPayPal(this, PAYPAL_PAY_PACKAGE_NAME)) {

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(uri);
            i.setPackage(PAYPAL_PAY_PACKAGE_NAME);
            startActivityForResult(i, 123);
        } else {
            Toast.makeText(this, "PayPal is not istalled", Toast.LENGTH_SHORT).show();
        }
    }


    public static boolean isAnstalledPayPal(Context context, String PackegeName) {
        try {
            context.getPackageManager().getApplicationInfo(PackegeName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;

        }
    }

    public static boolean isAnstalledPhonePay(Context context, String PackegeName) {
        try {
            context.getPackageManager().getApplicationInfo(PackegeName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;

        }
    }

    private static Uri getUPIPaymentUri(String name, String upiId, String note, String amount) {
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




    private void PayWithPaytm(String PPackagename) {
        if (isAnstalledPaytm(this, PAYTM_PACKAGE_NAME)) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(uri);
            i.setPackage(PAYTM_PACKAGE_NAME);
            startActivityForResult(i, 123);
        } else {
            Toast.makeText(this, "Paytm is not istalled ", Toast.LENGTH_SHORT).show();
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

    public void InsertMyOrder() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ORDER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //progressDialog.dismiss();
                //Log.d("order", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("msg");
                    if (status.equals("Inserted Successfully")) {
                        toast = Toast.makeText(Select_Paymen_Methode_Activity.this, "Purchase Course Confirm", Toast.LENGTH_SHORT);
                        toast.show();
                        toast.setGravity(Gravity.CENTER, 0, 0);


                    } else {
                        Toast.makeText(Select_Paymen_Methode_Activity.this, "fail", Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Select_Paymen_Methode_Activity.this, "try again connection fail"+error, Toast.LENGTH_SHORT).show();

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
        RequestQueue requestQueue = Volley.newRequestQueue(Select_Paymen_Methode_Activity.this);
        requestQueue.add(stringRequest);
    }

}