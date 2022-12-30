package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.R;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ads.AdsUtils;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.frag.AnalogFragment;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.frag.AnimatedFragment;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.frag.DigitalFragments;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.frag.PreFragment;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.frag.SmartFragment;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    TabLayout tabLayout;
    DrawerLayout drawer;
    private NavigationView navigationView;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adView = AdsUtils.showBanner(this, findViewById(R.id.llAds));

        initView();
        initViewpager();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (adView != null)
            adView.pause();
    }

    @Override
    protected void onDestroy() {
        if (adView != null)
            adView.destroy();
        super.onDestroy();
    }

    private void initViewpager() {

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(4);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initView() {
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(MainActivity.this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_privacy:

                startActivity(new Intent(MainActivity.this, PrivacyActivity.class));
                return true;

            case R.id.action_rate:

                String url1 = "https://play.google.com/store/apps/details?id=" + getPackageName();
                Uri uri1 = Uri.parse(url1);
                Intent intent1 = new Intent(Intent.ACTION_VIEW, uri1);
                if (intent1.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent1);
                }
                return true;
            case R.id.action_share:

                try {
                    String text = "Download Wallpapers to set as home and lock screen using\nAnime Wallpaper 4K : HD\n https://play.google.com/store/apps/details?id=" + getPackageName();
                    Intent txtIntent = new Intent(android.content.Intent.ACTION_SEND);
                    txtIntent.setType("text/plain");
                    txtIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Wallpaper");
                    txtIntent.putExtra(android.content.Intent.EXTRA_TEXT, text);
                    startActivity(Intent.createChooser(txtIntent, "Share Wallpaper"));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "can not share text", Toast.LENGTH_SHORT).show();
                }

                return true;
        }
        return false;
    }


    @Override
    public void onBackPressed() {

        exitDialog();
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
                    showRatingDialog();
                }
            });

        } catch (Exception ignored) {
        }
    }

    private void showRatingDialog() {
        // If using Java
        ReviewManager manager = ReviewManagerFactory.create(this);
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // We can get the ReviewInfo object
                ReviewInfo reviewInfo = task.getResult();
                Task<Void> flow = manager.launchReviewFlow(this, reviewInfo);
                flow.addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
            } else {
                // There was some problem, continue regardless of the result.
                // you can show your own rate dialog alert and redirect user to your app page
                // on play store.
            }
        });
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0) {
                fragment = new AnalogFragment();
            } else if (position == 1) {
                fragment = new AnimatedFragment();
            } else if (position == 2) {
                fragment = new DigitalFragments();
            } else if (position == 3) {
                fragment = new SmartFragment();
            } else if (position == 4) {
                fragment = new PreFragment();
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            if (position == 0) {
                title = "Analog";
            } else if (position == 1) {
                title = "LED";
            } else if (position == 2) {
                title = "Digital";
            } else if (position == 3) {
                title = "Smart";
            } else if (position == 4) {
                title = "Emoji";
            }
            return title;
        }
    }
}