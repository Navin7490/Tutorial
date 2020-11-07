package vision.madhvi.tutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import vision.madhvi.tutorial.R;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import static android.text.TextUtils.concat;

public class Home_User_Activity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle adt;
    ImageView imageView;
    Button btnpdprofile,btnpdlogout;
    TextView tvcourses, tvmarquee;
    FrameLayout frameLayoutCourses, frameLayoutProfile;
    Toast toast;
    Myreciver myreciver;

    Dialog dialog;

    //dialog
    CircleImageView imagepro;
    TextView tvname,tvprofilename, tvusername;
    String name,profilename, username;
    Button btnUpdate, btnSignOut;
    String welcom="Welcome ";
    String lastlogindate="Last Login: ";
    String n,pn,un,ld;
    LoginShareprefe_Modal loginShareprefeModal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user_);
        drawerLayout = findViewById(R.id.DrawerLyout);
        navigationView = findViewById(R.id.NaviGation);
        myreciver=new Myreciver();
        dialog=new Dialog(Home_User_Activity.this);
        dialog.setContentView(R.layout.profile_dialog);


        /// dialog

        loginShareprefeModal = new LoginShareprefe_Modal(Home_User_Activity.this);




        ///end

        frameLayoutCourses = findViewById(R.id.Fram_UCourses);
        frameLayoutProfile=findViewById(R.id.Fram_UProfile);
        adt = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(adt);
        adt.syncState();
        getSupportActionBar().setTitle("Madhvi Vision");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("WrongConstant")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();

                if (id == R.id.MenuMycoursedashboard) {
                    frameLayoutCourses.setVisibility(View.VISIBLE);
                    frameLayoutProfile.setVisibility(View.GONE);
                    Course_Fragment courseListFragment = new Course_Fragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.Fram_UCourses, courseListFragment);
                    drawerLayout.closeDrawer(Gravity.START);
                    getSupportActionBar().setTitle("Madhvi Vision");

                    fragmentTransaction.commit();
                }
                if (id == R.id.MenuTutorial) {
                    frameLayoutCourses.setVisibility(View.VISIBLE);
                    frameLayoutProfile.setVisibility(View.GONE);
                    My_Purchase_course_Fragment fragment=new My_Purchase_course_Fragment();
                    FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.Fram_UCourses,fragment);
                    fragmentTransaction.commit();
                    drawerLayout.closeDrawer(Gravity.START);
                }


                if (id == R.id.MenuShare) {
                    drawerLayout.closeDrawer(Gravity.START);
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    String sharebody = "https://play.google.com/store/apps/details?id=vision.madhvi.tutorial&hl=en_IN";
                    String sharesub = "Madhvi Vision";
                    intent.putExtra(Intent.EXTRA_SUBJECT, sharesub);
                    intent.putExtra(Intent.EXTRA_TEXT, sharebody);
                    startActivity(Intent.createChooser(intent, "share using"));
                }

                if (id == R.id.MenuFeedback) {
                    drawerLayout.closeDrawer(Gravity.START);


                }
                return true;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(myreciver,filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myreciver);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return adt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_myhome, menu);
        MenuItem notification = menu.findItem(R.id.Menu_my_notificatin);
        MenuItem flage = menu.findItem(R.id.Menu_my_flage);
        MenuItem profile = menu.findItem(R.id.Menu_my_profile);


        notification.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                return true;
            }
        });

        profile.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                dialog.show();
                imagepro = dialog.findViewById(R.id.Img_PDImage);

                Glide.with(Home_User_Activity.this).load(loginShareprefeModal.sharedPreLogin.getString("image", null)).into(imagepro);
                n = loginShareprefeModal.sharedPreLogin.getString("fullname", null);
                pn = loginShareprefeModal.sharedPreLogin.getString("profileName", null);

                un = loginShareprefeModal.sharedPreLogin.getString("UserName", null);
                ld = loginShareprefeModal.sharedPreLogin.getString("lastLoginDate", null);
               tvname=dialog.findViewById(R.id.Tv_PDName);
               tvprofilename=dialog.findViewById(R.id.Tv_PDProfileName);
               tvusername=dialog.findViewById(R.id.Tv_PDUName);
                name=welcom.concat(n);
                username=un+" , "+concat(lastlogindate.concat(ld));
                tvname.setText(name);
                tvprofilename.setText(pn);
                tvusername.setText(username);
               btnpdlogout=dialog.findViewById(R.id.Btn_PDLogout);
               btnpdlogout.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                      showlogoutdailog();
                   }
               });
                btnpdprofile=dialog.findViewById(R.id.Btn_PDProfile);
               btnpdprofile.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       MyProfile_Fragment profile_fragment=new MyProfile_Fragment();
                       FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                       transaction.replace(R.id.Fram_UProfile,profile_fragment);
                       transaction.commit();
                       frameLayoutCourses.setVisibility(View.GONE);
                       frameLayoutProfile.setVisibility(View.VISIBLE);

                       dialog.dismiss();
                   }
               });

                return true;
            }
        });

        flage.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem Item) {
                return true;
            }
        });


        return true;
    }
    public void showlogoutdailog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Exit");
        builder.setMessage("you want to logout");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loginShareprefeModal = new LoginShareprefe_Modal(Home_User_Activity.this);
                loginShareprefeModal.removeuser();
                toast = Toast.makeText(Home_User_Activity.this, "Logout successful", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                Intent intent = new Intent(getApplicationContext(), Home_Activity.class);
                startActivity(intent);
                finish();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        Dialog dialog = builder.create();
        dialog.show();
        // dialog.getWindow().setBackgroundDrawableResource(R.color.blue);
        Button no = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
        no.setTextColor(Color.BLACK);
        Button yes = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
        yes.setTextColor(Color.BLACK);

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START);
        }
        else {
            //ShowalertDialog();
           super.onBackPressed();
        }
    }
    public class Myreciver extends BroadcastReceiver {
        public  boolean noconnectivity;
        @Override
        public void onReceive(Context context, Intent intent) {

            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
                noconnectivity=intent.getBooleanExtra(
                        ConnectivityManager.EXTRA_NO_CONNECTIVITY,false
                );
                if (noconnectivity){
                    toast=  Toast.makeText(Home_User_Activity.this, "Please connect internet !", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();


                }else {
                    My_Purchase_course_Fragment fragment=new My_Purchase_course_Fragment();
                    FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.Fram_UCourses,fragment);
                    fragmentTransaction.commit();


                }
            }
        }
    }
}