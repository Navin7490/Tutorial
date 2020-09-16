package com.example.tutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class Home_User_Activity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle adt;

    Button btnviewcurses;
    TextView tvcourses, tvmarquee;
    FrameLayout frameLayoutCourses, frameLayoutVision;

    //Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user_);
        drawerLayout = findViewById(R.id.DrawerLyout);
        navigationView = findViewById(R.id.NaviGation);

        btnviewcurses = findViewById(R.id.Btn_Home_ViewCourse);
        tvcourses = findViewById(R.id.Tv_Home_COURSES);
        tvmarquee = findViewById(R.id.Tv_Home_LearnAt);
        frameLayoutCourses = findViewById(R.id.Fram_Courses);
        frameLayoutVision = findViewById(R.id.Fra_Vision);

        tvmarquee.setEllipsize(TextUtils.TruncateAt.MARQUEE);

        tvmarquee.setSelected(true);

        adt = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(adt);
        adt.syncState();
        getSupportActionBar().setTitle("Madhvi Vision");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Home_Vision_Fragment visionFragment = new Home_Vision_Fragment();
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.Fra_Vision, visionFragment);
        fragmentTransaction.commit();
        btnviewcurses.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                tvcourses.setText("All COURSES");
                frameLayoutCourses.setVisibility(View.VISIBLE);
                Course_Fragment courseFragment = new Course_Fragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.Fram_Courses, courseFragment);
                fragmentTransaction.commit();


            }
        });



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("WrongConstant")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                if (id == R.id.MenuHome) {
                    Course_Fragment courseListFragment = new Course_Fragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.Fram_Courses, courseListFragment);
                    drawerLayout.closeDrawer(Gravity.START);
                    getSupportActionBar().setTitle("Madhvi Vision");
                    tvcourses.setText("COURSES");

                    fragmentTransaction.commit();
                }
                if (id == R.id.MenuMycoursedashboard) {
                    MyCourse_Fragment myCourseFragment = new MyCourse_Fragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.Fram_Courses, myCourseFragment);
                    getSupportActionBar().setTitle("Madhvi Vision");
                    drawerLayout.closeDrawer(Gravity.START);
                    fragmentTransaction.commit();
                    Toast.makeText(Home_User_Activity.this, "Dashboard", Toast.LENGTH_SHORT).show();

                }
                if (id == R.id.MenuTutorial) {

                    My_Purchase_course_Fragment fragment=new My_Purchase_course_Fragment();
                    FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.Fram_Courses,fragment);
                    tvcourses.setText("PURCHASE COURSES");
                    fragmentTransaction.commit();

//                    Intent intent=new Intent(getApplicationContext(),Tutorial_Details_Activity.class);
//                    startActivity(intent);
                    drawerLayout.closeDrawer(Gravity.START);
                }

                if (id == R.id.MenuShare) {
                    drawerLayout.closeDrawer(Gravity.START);
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    String sharebody = "https://play.google.com/";
                    String sharesub = "Video Tutorial";
                    intent.putExtra(Intent.EXTRA_SUBJECT, sharesub);
                    intent.putExtra(Intent.EXTRA_TEXT, sharebody);
                    startActivity(Intent.createChooser(intent, "share using"));
                }

                if (id == R.id.MenuFeedback) {
                    drawerLayout.closeDrawer(Gravity.START);
                    Toast.makeText(Home_User_Activity.this, "Feed Back", Toast.LENGTH_SHORT).show();


                }
                return true;
            }
        });

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
                Toast.makeText(Home_User_Activity.this, "Notification", Toast.LENGTH_SHORT).show();

                return true;
            }
        });

        profile.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                Intent intent=new Intent(getApplicationContext(),Profile_Activity.class);
                startActivity(intent);
                // finish();
                return true;
            }
        });

        flage.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem Item) {
                Toast.makeText(Home_User_Activity.this, "flage", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

//        SearchView searchView= (SearchView) menuItem.getActionView();
//        searchView.setQueryHint("Type here search");
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return true;
//            }
//        });
        return true;
    }
    public void ShowalertDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(Home_User_Activity.this);
        builder.setCancelable(false);
        builder.setTitle("Exit ?");
        builder.setMessage("Are you Exit ?");

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        builder.create();
        builder.show();

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START);
        }
        else {
            ShowalertDialog();
           // super.onBackPressed();
        }
    }
}