package com.myjob2u.myjob2u;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //home fragment variables fragmentN => J equals 'J'ob details
    final Fragment fragmentJ = new JobDetailsFragment();
    final Fragment fragmentP = new PostJobFragment();
    final Fragment fragmentH = new JobHistoryFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragmentJ;

    private DrawerLayout mDrawerLayout;
    TextView tv;

    // these two variables will be used by SharedPreferences
    private static final String FILE_NAME = "file_lang"; // preference file name
    private static final String KEY_LANG = "key_lang"; // preference key
    private Context context;


    public SharedPreferences loginSession;
    public SharedPreferences.Editor loginEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //initialisation
        super.onCreate(savedInstanceState);

//make status bar translucent
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);



        loadLanguage();

        setContentView(R.layout.activity_main);

        //hide fragment
        BottomNavigationView Bnavigation = findViewById(R.id.bottom_navigation);
        Bnavigation.setOnNavigationItemSelectedListener(mBottomNavigation);

        fm.beginTransaction().add(R.id.homeFragmentPlaceholder, fragmentJ, "3").hide(fragmentJ).commit();
        fm.beginTransaction().add(R.id.homeFragmentPlaceholder, fragmentP, "2").hide(fragmentP).commit();
        fm.beginTransaction().add(R.id.homeFragmentPlaceholder, fragmentH, "1").commit();

        //add toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //convert to actionbar
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);


        //Navigation Drawer
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);



        //link to other activity from the navigation drawer
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        switch (menuItem.getItemId()) {

                            //case R.id.login:
                            //    launchLogin();
                            //    break;

                            //case R.id.logout:
                              //  SharedPrefManager.getInstance(MainActivity.this).logout();
                              //  finishAndRemoveTask();
                              //  Intent intent = new Intent(MainActivity.this, MainActivity.class);
                              //  startActivity(intent);
                              //  break;

                            case R.id.login:
                                launchLogin();
                                break;

                            case R.id.profile:
                                launchProfile();
                                break;

                            case R.id.Bookmarks:
                                launchBookmarks();
                                break;

                            case R.id.guideline:
                                launchguideline();
                                break;

                            case R.id.logout:
                                SharedPreferences loginSession = getSharedPreferences("loginSession", MODE_PRIVATE);
                                SharedPreferences.Editor loginEdit = loginSession.edit();

                                loginEdit.remove("username");
                                loginEdit.commit();
                                 NavigationView navigationView = findViewById(R.id.nav_view);
                                View headerView = navigationView.getHeaderView(0);
                                TextView navUsername = headerView.findViewById(R.id.navUsername);
                                String username = loginSession.getString("username","Anonymous");
                                 navUsername.setText(username);

                                Toast.makeText(getApplicationContext(),"you have logged out",Toast.LENGTH_LONG).show();

                                break;
                        }
                        return true;
                    }
                });




        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.navUsername);

//show if user logged in

            loginSession = getApplicationContext().getSharedPreferences("loginSession", MODE_PRIVATE);


            String username = loginSession.getString("username","Anonymous");
            navUsername.setText(username);



    }

    @Override
    protected void onResume() {
        super.onResume();

        /////to resume after login
        SharedPreferences loginSession = getSharedPreferences("loginSession", MODE_PRIVATE);
        SharedPreferences.Editor loginEdit = loginSession.edit();

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.navUsername);
        String username = loginSession.getString("username","Anonymous");
        navUsername.setText(username);

    }


    private void launchLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    private void launchProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }


    private void launchBookmarks() {
        Intent intent = new Intent(this, BookmarksActivity.class);
        startActivity(intent);
    }


    private void launchguideline() {
        Intent intent = new Intent(this, GuidelinesActivity.class);
        startActivity(intent);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mBottomNavigation
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.JobDetails:
                    fm.beginTransaction().hide(active).detach(active).attach(fragmentJ).show(fragmentJ).commit();
                    fragmentJ.onResume();
                    active = fragmentJ;
                    return true;

                case R.id.PostJob:
                    fm.beginTransaction().hide(active).detach(active).attach(fragmentP).show(fragmentP).commit();
                    fragmentP.onResume();
                    active = fragmentP;
                    return true;

                case R.id.JobHistory:
                    fm.beginTransaction().hide(active).detach(active).attach(fragmentH).show(fragmentH).commit();
                    fragmentH.onResume();
                    active = fragmentH;
                    return true;
            }
            return false;
        }
    };




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }





    public void chgDialogList(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choose your preferred language.");

        String[] lang = {"English", "中文", "Bahasa Malaysia", "Tamil"};
        builder.setItems(lang, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        changeLang("en");
                        break;
                    case 1:
                        changeLang("zh");
                        break;
                    case 2:
                        changeLang("ms");
                        break;
                    case 3:
                        changeLang("ta");
                        break;
                }
            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //change language code
    private void changeLang(String lang) {
        SharedPreferences preferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_LANG, lang);
        editor.apply();
        // recreate activity after saving to load the new language, this is the same
        // as refreshing activity to load new language
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    private void loadLanguage() {
        // we can use this method to load language,
        // this method should be called before setContentView() method of the onCreate method
        Locale locale = new Locale(getLangCode());
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }

    private String getLangCode() {
        SharedPreferences preferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        String langCode = preferences.getString(KEY_LANG, "en");
        return langCode;
    }


}
