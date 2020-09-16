package com.example.tutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Home_Activity extends AppCompatActivity {
    Button btnviewcurses;
    TextView tvcourses, tvmarquee;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle adt;
    FrameLayout frameLayoutCourses, frameLayoutVision;
    private MenuItem item;

    //Toolbar toolbar;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        drawerLayout = findViewById(R.id.Drawer_Home);
        navigationView = findViewById(R.id.Navigation_Home);
        // toolbar=findViewById(R.id.MyToolbar);
        // recyclerViewCourses=findViewById(R.id.Rv_Home_Courses);
        // recyclerViewVision=findViewById(R.id.Rv_Home_Vision);
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
        //setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Madhvi Vision");


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
                if (id == R.id.MenuHome_courses) {
                    tvcourses.setText("All COURSES");
                    frameLayoutCourses.setVisibility(View.VISIBLE);
                    frameLayoutVision.setVisibility(View.VISIBLE);
                    Course_Fragment courseFragment = new Course_Fragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.Fram_Courses, courseFragment);
                    fragmentTransaction.commit();
                    drawerLayout.closeDrawer(Gravity.START);

                }
                if (id == R.id.MenuHome_vision) {
                    frameLayoutCourses.setVisibility(View.GONE);
                    frameLayoutVision.setVisibility(View.VISIBLE);
                    Home_Vision_Fragment visionFragment = new Home_Vision_Fragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.Fra_Vision, visionFragment);
                    fragmentTransaction.commit();
                    drawerLayout.closeDrawer(Gravity.START);

                }
                if (id == R.id.MenuHome_Login) {
                    frameLayoutVision.setVisibility(View.GONE);
                    frameLayoutCourses.setVisibility(View.VISIBLE);
                    Login_Fragment loginFragment = new Login_Fragment();
                    FragmentTransaction fragmentTralogin = getSupportFragmentManager().beginTransaction();
                    fragmentTralogin.replace(R.id.Fram_Courses, loginFragment);
                    fragmentTralogin.commit();
                    drawerLayout.closeDrawer(Gravity.START);

                }
                if (id == R.id.MenuHome_signup) {
                    frameLayoutVision.setVisibility(View.GONE);
                    frameLayoutCourses.setVisibility(View.VISIBLE);
                    SignUp_Fragment signUp_fragment = new SignUp_Fragment();
                    FragmentTransaction signuptra = getSupportFragmentManager().beginTransaction();
                    signuptra.replace(R.id.Fram_Courses, signUp_fragment);
                    signuptra.commit();

                    drawerLayout.closeDrawer(Gravity.START);


                }

                return false;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return adt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START);
        } else {
            ShowalertDialog();
            //super.onBackPressed();
        }
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//       getMenuInflater().inflate(R.menu.toolbarmenu,menu);
//
//         MenuItem menuItem=menu.findItem(R.id.Menu_tool_search);
//        MenuItem profile=menu.findItem(R.id.Menu_tool_profile);
//
//        profile.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem Item) {
//                Toast.makeText(Home_Activity.this, "profile", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
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
//        return true;
//    }
    public void ShowalertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Home_Activity.this);
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

}