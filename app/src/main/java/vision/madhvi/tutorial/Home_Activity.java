package vision.madhvi.tutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import vision.madhvi.tutorial.R;

import com.google.android.material.navigation.NavigationView;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class Home_Activity extends AppCompatActivity {
    Button btnviewcurses;
    TextView tvcourses, tvmarquee, tvsomething, tvvision, tvvisionidea;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle adt;
    FrameLayout frameLayoutCourses, frameLayoutVision;
    private MenuItem item;
    Myreciver myreciver;
    RelativeLayout imageView;
    Toast toast;

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
        tvsomething = findViewById(R.id.Tv_Home_Something);
        tvvision = findViewById(R.id.Tv_Home_Vision);
        tvvisionidea = findViewById(R.id.Tv_Home_VisionIdea);
        imageView = findViewById(R.id.Layout1);

        tvmarquee.setEllipsize(TextUtils.TruncateAt.MARQUEE);

        tvmarquee.setSelected(true);
        adt = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(adt);
        adt.syncState();
        //setSupportActionBar(toolbar);
        myreciver = new Myreciver();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Madhvi Vision");
        getSupportActionBar();
        imageView.setVisibility(View.GONE);
        tvmarquee.setVisibility(View.GONE);
        btnviewcurses.setVisibility(View.GONE);
        tvcourses.setVisibility(View.GONE);
        tvsomething.setVisibility(View.GONE);
        tvvision.setVisibility(View.GONE);
        tvvisionidea.setVisibility(View.GONE);
        frameLayoutCourses.setVisibility(View.GONE);
        frameLayoutVision.setVisibility(View.GONE);

        // Runtime permission call method
         PermissionAllow();
        //Runtime permission end


        btnviewcurses.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                tvcourses.setText("All COURSES");
                tvcourses.setVisibility(View.VISIBLE);
                tvsomething.setVisibility(View.VISIBLE);
                frameLayoutCourses.setVisibility(View.VISIBLE);
                frameLayoutVision.setVisibility(View.GONE);
                tvvision.setVisibility(View.GONE);
                tvvisionidea.setVisibility(View.GONE);
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

                    if (myreciver.noconnectivity) {
                        toast = Toast.makeText(Home_Activity.this, "Please connect internet !", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        drawerLayout.closeDrawer(Gravity.START);

                    } else {
                        tvcourses.setText("All COURSES");
                        frameLayoutCourses.setVisibility(View.VISIBLE);
                        frameLayoutVision.setVisibility(View.GONE);
                        tvcourses.setVisibility(View.VISIBLE);
                        tvsomething.setVisibility(View.VISIBLE);
                        tvvision.setVisibility(View.GONE);
                        tvvisionidea.setVisibility(View.GONE);
                        Course_Fragment courseFragment = new Course_Fragment();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.Fram_Courses, courseFragment);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(Gravity.START);
                    }


                }
                if (id == R.id.MenuHome_vision) {
                    if (myreciver.noconnectivity) {
                        toast = Toast.makeText(Home_Activity.this, "Please connect internet !", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        drawerLayout.closeDrawer(Gravity.START);

                    } else {

                        frameLayoutCourses.setVisibility(View.GONE);
                        frameLayoutVision.setVisibility(View.VISIBLE);
                        tvvision.setVisibility(View.VISIBLE);
                        tvvisionidea.setVisibility(View.VISIBLE);
                        tvcourses.setVisibility(View.GONE);
                        tvsomething.setVisibility(View.GONE);
                        Home_Vision_Fragment visionFragment = new Home_Vision_Fragment();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.Fra_Vision, visionFragment);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(Gravity.START);
                    }
                }
                if (id == R.id.MenuHome_Login) {
                    if (myreciver.noconnectivity) {
                        toast = Toast.makeText(Home_Activity.this, "Please connect internet !", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        drawerLayout.closeDrawer(Gravity.START);

                    } else {

                        frameLayoutVision.setVisibility(View.GONE);
                        frameLayoutCourses.setVisibility(View.VISIBLE);
                        tvcourses.setVisibility(View.GONE);
                        tvsomething.setVisibility(View.GONE);
                        tvvision.setVisibility(View.GONE);
                        tvvisionidea.setVisibility(View.GONE);
                        Login_Fragment loginFragment = new Login_Fragment();
                        FragmentTransaction fragmentTralogin = getSupportFragmentManager().beginTransaction();
                        fragmentTralogin.replace(R.id.Fram_Courses, loginFragment);
                        fragmentTralogin.commit();
                        drawerLayout.closeDrawer(Gravity.START);
                    }

                }
                if (id == R.id.MenuHome_signup) {
                    if (myreciver.noconnectivity) {
                        toast = Toast.makeText(Home_Activity.this, "Please connect internet !", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        drawerLayout.closeDrawer(Gravity.START);

                    } else {
                        frameLayoutVision.setVisibility(View.GONE);
                        frameLayoutCourses.setVisibility(View.VISIBLE);
                        tvcourses.setVisibility(View.GONE);
                        tvsomething.setVisibility(View.GONE);
                        tvvision.setVisibility(View.GONE);
                        tvvisionidea.setVisibility(View.GONE);
                        SignUp_Fragment signUp_fragment = new SignUp_Fragment();
                        FragmentTransaction signuptra = getSupportFragmentManager().beginTransaction();
                        signuptra.replace(R.id.Fram_Courses, signUp_fragment);
                        signuptra.commit();

                        drawerLayout.closeDrawer(Gravity.START);
                    }


                }

                return false;
            }
        });


    }

    // permission method start
    public void PermissionAllow() {
        PermissionListener permissionListener = new PermissionListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onPermissionGranted() {

            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                finish();
                Toast.makeText(Home_Activity.this, "Required All Permission", Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.with(Home_Activity.this)
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();

    }

    // permission method end


    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(myreciver, filter);
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

    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START);
        } else {
            //ShowalertDialog();
            super.onBackPressed();
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

    public class Myreciver extends BroadcastReceiver {
        public boolean noconnectivity;

        @Override
        public void onReceive(Context context, Intent intent) {

            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                noconnectivity = intent.getBooleanExtra(
                        ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
                );
                if (noconnectivity) {
                    toast = Toast.makeText(Home_Activity.this, "Please connect internet !", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();


                } else {

                    Home_Vision_Fragment visionFragment = new Home_Vision_Fragment();
                    final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.Fra_Vision, visionFragment);
                    fragmentTransaction.commit();
                    frameLayoutCourses.setVisibility(View.VISIBLE);
                    frameLayoutVision.setVisibility(View.VISIBLE);
                    tvvision.setVisibility(View.VISIBLE);
                    tvvisionidea.setVisibility(View.VISIBLE);
                    tvcourses.setVisibility(View.VISIBLE);
                    tvsomething.setVisibility(View.VISIBLE);
                    btnviewcurses.setVisibility(View.VISIBLE);
                    tvmarquee.setVisibility(View.VISIBLE);
                    imageView.setVisibility(View.VISIBLE);
                    btnviewcurses.setVisibility(View.VISIBLE);
                }
            }
        }
    }

}