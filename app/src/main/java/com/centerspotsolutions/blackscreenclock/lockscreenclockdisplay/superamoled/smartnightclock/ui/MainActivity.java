package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.R;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.databinding.ActivityMainBinding;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.frag.AnalogFragment;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.frag.DigitalFragments;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.frag.PreFragment;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.frag.AnimatedFragment;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.frag.SmartFragment;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ads.AdsUtils;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    private ActionBar toolbar;
    private MainActivity activity;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        activity = this;

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NotNull InitializationStatus initializationStatus) {
                AdsUtils.loadInterstitial(getApplicationContext());

            }
        });
        initView();

    }
    private void initView() {
        toolbar = getSupportActionBar();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Smart");
        loadFragment(new SmartFragment());
    }
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_smart:
                    toolbar.setTitle("Smart");
                    fragment = new SmartFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_analog:
                    toolbar.setTitle("Analog");
                    fragment = new AnalogFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_digital:
                    toolbar.setTitle("Digital");
                    fragment = new DigitalFragments();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_led:
                    toolbar.setTitle("LED");
                    fragment = new AnimatedFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_emoji:
                    toolbar.setTitle("Emoji");
                    fragment = new PreFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {

        exitDialog();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_privacy) {
            startActivity(new Intent(MainActivity.this, PrivacyActivity.class));
        }  else if (item.getItemId() == R.id.nav_more_app) {
            String url = "https://play.google.com/store/apps/developer?id=ABC";
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        } else if (item.getItemId() == R.id.nav_rate_app) {
            String url = "https://play.google.com/store/apps/details?id=" + getPackageName();
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        } else if (item.getItemId() == R.id.nav_share_app) {

            try {
                String text = "Download Hd wallpaper to set as home and lock screen\n https://play.google.com/store/apps/details?id=" + getPackageName();
                Intent txtIntent = new Intent(android.content.Intent.ACTION_SEND);
                txtIntent.setType("text/plain");
                txtIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Wallpaper");
                txtIntent.putExtra(android.content.Intent.EXTRA_TEXT, text);
                startActivity(Intent.createChooser(txtIntent, "Share Wallpaper"));
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "can not share text", Toast.LENGTH_SHORT).show();
            }


        }
        return true;
    }
    public void exitDialog() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final AlertDialog dialog;
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.exit_dialog, null);
            builder.setView(view);
            builder.setCancelable(true);
            dialog = builder.create();
            dialog.show();
            dialog.findViewById(R.id.ll_no).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog.findViewById(R.id.ll_yes).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    finish();
                }
            });
            dialog.findViewById(R.id.ll_rate).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    String url = "https://play.google.com/store/apps/details?id=" + getPackageName();
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            });

        } catch (Exception ignored) {
        }
    }
}