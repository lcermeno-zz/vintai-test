package com.qiubo.vintai.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.qiubo.vintai.R;
import com.qiubo.vintai.ui.fragments.PostsFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupDrawer();
    }

    private void setupFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @SuppressLint("RestrictedApi")
    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.vw_home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    private void setupDrawer() {
        mDrawerLayout = findViewById(R.id.vw_home_drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.best_post:
                        Toast.makeText(MainActivity.this, getString(R.string.lbl_best_posts), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.statistics:
                        Toast.makeText(MainActivity.this, getString(R.string.lbl_general_statics), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.my_styles:
                        Toast.makeText(MainActivity.this, getString(R.string.lbl_my_styles), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.best_profiles:
                        Toast.makeText(MainActivity.this, getString(R.string.lbl_best_profiles), Toast.LENGTH_LONG).show();
                        break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        final NavigationView generalNavigationView = findViewById(R.id.nav_general_menu);
        generalNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        fragment = getFragmentByTag("home");
                        if(fragment == null)
                            fragment = PostsFragment.newInstance();
                        break;
                    case R.id.take_picture:
                        fragment = getFragmentByTag("take_picture");
                        break;
                    case R.id.api_data:
                        fragment = getFragmentByTag("api_data");
                        break;
                    case R.id.graphic:
                        fragment = getFragmentByTag("graphic");
                        break;
                    case R.id.edit_picture:
                        fragment = getFragmentByTag("edit_picture");
                        break;
                    case R.id.edit_ai:
                        fragment = getFragmentByTag("edit_ai");
                        break;
                }

                if(fragment != null)
                    setupFragment(fragment);

                mDrawerLayout.closeDrawer(GravityCompat.END);
                return true;
            }
        });

        final AppCompatImageButton generalMenuBtn = findViewById(R.id.btn_right_menu);
        generalMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.END);
            }
        });

        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                drawerView.bringToFront();
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    private Fragment getFragmentByTag(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    @Override
    public boolean onSupportNavigateUp() {
        mDrawerLayout.openDrawer(GravityCompat.START);
        return true;
    }
}
