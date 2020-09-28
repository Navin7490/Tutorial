package vision.madhvi.tutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import vision.madhvi.tutorial.R;
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LoginShareprefe_Modal loginShareprefeModal1=new LoginShareprefe_Modal(getApplicationContext());
                String UserName=   loginShareprefeModal1.sharedPreLogin.getString("UserName",null);

                if (UserName !=null){
                    Intent intent=new Intent(getApplicationContext(),Home_User_Activity.class);
                    startActivity(intent);
                    finish();
                }
                else {

                    Intent intenth=new Intent(getApplicationContext(),Home_Activity.class);
                    startActivity(intenth);
                    finish();

                }

            }
        },2000);
    }

}