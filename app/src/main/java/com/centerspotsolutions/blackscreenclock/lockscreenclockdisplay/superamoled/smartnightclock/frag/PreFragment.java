package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.frag;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.R;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.databinding.FragmentPreBinding;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ui.ClocksActivity;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ads.AdsUtils;

public class PreFragment extends Fragment {

    private FragmentPreBinding binding;


    public PreFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPreBinding
                .inflate(inflater, container, false);
        setClickListeners();
        loadBanner();

        return binding.getRoot();
    }

    private void setClickListeners() {
        binding.linearLayoutEmoji1.setOnClickListener(this::funClick);
        binding.linearLayoutEmoji2.setOnClickListener(this::funClick);
        binding.linearLayoutEmoji12.setOnClickListener(this::funClick);
        binding.linearLayoutEmoji4.setOnClickListener(this::funClick);
        binding.linearLayoutEmoji8.setOnClickListener(this::funClick);
        binding.linearLayoutEmoji9.setOnClickListener(this::funClick);
        binding.linearLayoutEmoji11.setOnClickListener(this::funClick);
    }



    public void funClick(View view) {
        int i;
        switch (view.getId()) {
            case R.id.linear_layout_emoji1:
                i = 31;
                break;
            case R.id.linear_layout_emoji11:
                i = 41;
                break;
            case R.id.linear_layout_emoji12:
                i = 42;
                break;
            case R.id.linear_layout_emoji2:
                i = 32;
                break;
            case R.id.linear_layout_emoji4:
                i = 34;
                break;
            case R.id.linear_layout_emoji8:
                i = 38;
                break;
            case R.id.linear_layout_emoji9:
                i = 39;
                break;
            default:
                i = 0;
                break;
        }
        Intent intent = new Intent(getActivity(), ClocksActivity.class);
        intent.putExtra("Digital", i);
        intent.putExtra("skipTheme", true);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        AdsUtils.showInterstitial(getContext());

    }

    public void displayClock(final View view) {
        funClick(view);
    }

    private void loadBanner() {
        AdRequest adRequest2 = new AdRequest.Builder().build();
        binding.adViewRectangle.loadAd(adRequest2);
        binding.adViewRectangle.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                binding.adViewRectangle.setVisibility(View.VISIBLE);
            }
        });
    }


}