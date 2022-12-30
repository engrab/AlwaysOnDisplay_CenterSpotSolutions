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
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.databinding.FragmentDigitalBinding;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ui.ClocksActivity;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ads.AdsUtils;

public class DigitalFragments extends Fragment {


    int clockNumber = 0;


    private FragmentDigitalBinding binding;

    public DigitalFragments() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDigitalBinding
                .inflate(inflater, container, false);
        setClickListeners();


        return binding.getRoot();
    }





    private void setClickListeners() {
        binding.preiewPreImg1.setOnClickListener(this::analogClockShow);
        binding.preiewPreImg2.setOnClickListener(this::analogClockShow);
        binding.preiewPreImg3.setOnClickListener(this::analogClockShow);
        binding.preiewPreImg4.setOnClickListener(this::analogClockShow);
        binding.preiewPreImg5.setOnClickListener(this::analogClockShow);
        binding.preiewPreImg6.setOnClickListener(this::analogClockShow);
        binding.preiewPreImg7.setOnClickListener(this::analogClockShow);
        binding.preiewPreImg8.setOnClickListener(this::analogClockShow);
        binding.preiewPreImg9.setOnClickListener(this::analogClockShow);
        binding.preiewPreImg10.setOnClickListener(this::analogClockShow);
        binding.preiewPreImg12.setOnClickListener(this::analogClockShow);
        binding.preImg13.setOnClickListener(this::analogClockShow);
        binding.preImg132.setOnClickListener(this::analogClockShow);
        binding.preImg14.setOnClickListener(this::analogClockShow);
        binding.preImg15.setOnClickListener(this::analogClockShow);
        binding.preImg16.setOnClickListener(this::analogClockShow);
    }

    public void analogClockShow(View view) {
        switch (view.getId()) {
            case R.id.preiew_pre_img1:
                clockNumber = 101;
                break;
            case R.id.preiew_pre_img10:
                this.clockNumber = 110;
                break;
            case R.id.preiew_pre_img12:
                this.clockNumber = 112;
                break;
            case R.id.pre_img13:
            case R.id.pre_img13_2:
                this.clockNumber = 113;
                break;
            case R.id.pre_img14:
                this.clockNumber = 114;
                break;
            case R.id.pre_img15:
                this.clockNumber = 115;
                break;
            case R.id.pre_img16:
                this.clockNumber = 116;
                break;
            case R.id.preiew_pre_img2:
                this.clockNumber = 102;
                break;
            case R.id.preiew_pre_img3:
                this.clockNumber = 103;
                break;
            case R.id.preiew_pre_img4:
                this.clockNumber = 104;
                break;
            case R.id.preiew_pre_img5:
                this.clockNumber = 105;
                break;
            case R.id.preiew_pre_img6:
                this.clockNumber = 106;
                break;
            case R.id.preiew_pre_img7:
                this.clockNumber = 107;
                break;
            case R.id.preiew_pre_img8:
                this.clockNumber = 108;
                break;
            case R.id.preiew_pre_img9:
                this.clockNumber = 109;
                break;
            default:
                break;
        }

        Intent intent2 = new Intent(getActivity(), ClocksActivity.class);
        intent2.putExtra("Digital", this.clockNumber);
        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent2);

        AdsUtils.showInterstitial(getContext());
    }


}