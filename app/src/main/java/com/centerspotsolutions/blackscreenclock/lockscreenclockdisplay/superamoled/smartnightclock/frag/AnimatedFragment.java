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
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.databinding.FragmentAnimatedBinding;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ui.ClocksActivity;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ads.AdsUtils;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils.ClockNum;

import org.jetbrains.annotations.NotNull;

public class AnimatedFragment extends Fragment {

    private FragmentAnimatedBinding binding;

    public AnimatedFragment() { }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAnimatedBinding.inflate(inflater, container, false);
        setClickListeners();
        loadBanner();

        return binding.getRoot();
    }

    private void setClickListeners() {
        binding.relativeLayoutLed1.setOnClickListener(this::premiumClocksClick);
        binding.linearLayoutLed3.setOnClickListener(this::premiumClocksClick);
        binding.linearLayoutLed32.setOnClickListener(this::premiumClocksClick);

        binding.imageled10.setOnClickListener(this::clickFun);
        binding.imageled11.setOnClickListener(this::clickFun);
        binding.imageled12.setOnClickListener(this::clickFun);
        binding.relativeLayoutLed13.setOnClickListener(this::clickFun);
        binding.imageled4.setOnClickListener(this::clickFun);
        binding.imageled5.setOnClickListener(this::clickFun);
        binding.imageled6.setOnClickListener(this::clickFun);
        binding.imageled7.setOnClickListener(this::clickFun);
        binding.imageled8.setOnClickListener(this::clickFun);
        binding.imageled9.setOnClickListener(this::clickFun);
    }



    public void premiumClocksClick(View view) {
        int i = 0;
        switch (view.getId()) {
            case R.id.relative_layout_led1:
                i = ClockNum.led1;
                break;
            case R.id.linear_layout_led3:
            case R.id.linear_layout_led3_2:
                i = ClockNum.led3;
                break;
        }
        Intent intent = new Intent(getActivity(), ClocksActivity.class);
        intent.putExtra("Digital", i);
        intent.putExtra("skipTheme", true);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 536870912
        startActivity(intent);

        AdsUtils.showInterstitial(getContext());
    }

    public void clickFun(View view) {
        int i;
        switch (view.getId()) {
            case R.id.imageled10:
                i = ClockNum.led10;
                break;
            case R.id.imageled11:
                i = ClockNum.led11;
                break;
            case R.id.imageled12:
                i = ClockNum.led12;
                break;
            case R.id.relative_layout_led13:
                i = ClockNum.led13;
                break;
            case R.id.linear_layout_led3:
                i = ClockNum.led3;
                break;
            case R.id.imageled4:
                i = ClockNum.led4;
                break;
            case R.id.imageled5:
                i = ClockNum.led5;
                break;
            case R.id.imageled6:
                i = ClockNum.led6;
                break;
            case R.id.imageled7:
                i = ClockNum.led7;
                break;
            case R.id.imageled8:
                i = ClockNum.led8;
                break;
            case R.id.imageled9:
                i = ClockNum.led9;
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

    public void clickLEDClock(final View view) {
        clickFun(view);
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