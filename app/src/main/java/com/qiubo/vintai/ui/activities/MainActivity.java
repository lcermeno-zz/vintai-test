package com.qiubo.vintai.ui.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.qiubo.vintai.R;
import com.qiubo.vintai.ui.fragments.PostsFragment;
import com.qiubo.vintai.widgets.ImageFilePath;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private static final int PERMISSION_CAMERA_CODE = 144;
    private static final int PERMISSION_STORAGE_CODE = 255;
    private static final int REQUEST_CODE_PICKER = 366;
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

        final AppCompatImageButton generalMenuBtn = findViewById(R.id.btn_right_menu);
        final View menuContainer = findViewById(R.id.menu_container);
        generalMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context wrapper = new ContextThemeWrapper(MainActivity.this, R.style.PopupMenu);
                PopupMenu popup = new PopupMenu(wrapper, menuContainer);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_general, popup.getMenu());

                popup.setOnMenuItemClickListener(MainActivity.this);
                popup.show();
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

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.home:
                fragment = getFragmentByTag("home");
                if (fragment == null) fragment = PostsFragment.newInstance();
                break;
            case R.id.take_picture:
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // Permission is not granted
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, PERMISSION_CAMERA_CODE);
                } else {
                    startActivity(new Intent(MainActivity.this, CameraViewActivity.class));
                }
                break;
            case R.id.graphic:
            case R.id.api_data:
                Toast.makeText(this, "On another occasion it may be", Toast.LENGTH_SHORT).show();
                break;
            case R.id.edit_picture:
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // Permission is not granted
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_STORAGE_CODE);
                } else {
                    openPicker();
                }
                break;
            case R.id.edit_ai:
                fragment = getFragmentByTag("edit_ai");
                break;
        }

        if (fragment != null) setupFragment(fragment);

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CAMERA_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(new Intent(this, CameraViewActivity.class));
                }
                break;
            case PERMISSION_STORAGE_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openPicker();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_PICKER:
                if (resultCode == RESULT_OK && data != null) {
                    onMediaPicked(data);
                }
                break;
        }
    }



    private void onMediaPicked(Intent data) {
        List<Uri> uris = Matisse.obtainResult(data);
        if (uris != null && !uris.isEmpty()) {
            Intent intent = new Intent(this, EditorActivity.class);
            intent.putExtra("uri", ImageFilePath.getInstance().getPath(this, uris.get(0)));
            startActivity(intent);
        }
    }

    private void openPicker() {
        Matisse.from(this)
                .choose(MimeType.of(MimeType.JPEG, MimeType.PNG, MimeType.MP4, MimeType.MPEG), false)
                .countable(true)
                .maxSelectable(1)
                .spanCount(3)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.5f)
                .imageEngine(new GlideEngine())
                .forResult(REQUEST_CODE_PICKER);
    }
}
