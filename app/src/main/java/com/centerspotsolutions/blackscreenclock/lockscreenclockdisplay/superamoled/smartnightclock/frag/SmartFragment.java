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
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.databinding.FragmentSmartBinding;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ui.ClocksActivity;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ads.AdsUtils;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils.ClockNum;

import org.jetbrains.annotations.NotNull;

public class SmartFragment extends Fragment implements View.OnClickListener {

    int clockNumber = 0;
    private FragmentSmartBinding binding;


    public SmartFragment() {
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSmartBinding.inflate(inflater, container, false);
        setClickListeners();



        return binding.getRoot();
    }

    private void setClickListeners() {
        binding.preiewPreImg12.setOnClickListener(this::onClick);
        binding.preiewImg11.setOnClickListener(this::onClick);
        binding.preiewPreImg10.setOnClickListener(this::onClick);
        binding.preiewPreImg9.setOnClickListener(this::onClick);
        binding.preiewPreImg8.setOnClickListener(this::onClick);
        binding.preiewPreImg7.setOnClickListener(this::onClick);
        binding.preiewPreImg6.setOnClickListener(this::onClick);
        binding.preiewPreImg5.setOnClickListener(this::onClick);
        binding.preiewPreImg4.setOnClickListener(this::onClick);
        binding.preiewPreImg3.setOnClickListener(this::onClick);
        binding.preiewPreImg2.setOnClickListener(this::onClick);
        binding.preiewPreImg1.setOnClickListener(this::onClick);
    }



    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.preiew_pre_img1:
                this.clockNumber = 120;
                break;
            case R.id.preiew_pre_img10:
                this.clockNumber = ClockNum.smart8;
                break;
            case R.id.preiew_img11:
                this.clockNumber = ClockNum.smart11;
                break;
            case R.id.preiew_pre_img12:
                this.clockNumber = ClockNum.smart12;
                break;
            default:
                switch (id) {
                    case R.id.preiew_pre_img2:
                        this.clockNumber = 124;
                        break;
                    case R.id.preiew_pre_img3:
                        this.clockNumber = 122;
                        break;
                    case R.id.preiew_pre_img4:
                        this.clockNumber = 123;
                        break;
                    case R.id.preiew_pre_img5:
                        this.clockNumber = 121;
                        break;
                    case R.id.preiew_pre_img6:
                        this.clockNumber = 128;
                        break;
                    case R.id.preiew_pre_img7:
                        this.clockNumber = ClockNum.smart7;
                        break;
                    case R.id.preiew_pre_img8:
                        this.clockNumber = ClockNum.smart10;
                        break;
                    case R.id.preiew_pre_img9:
                        this.clockNumber = ClockNum.smart6;
                        break;
                }
        }

        Intent intent2 = new Intent(getActivity(), ClocksActivity.class);
        intent2.putExtra("Digital", this.clockNumber);
        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //536870912
        intent2.putExtra("skipTheme", true);
        startActivity(intent2);

        AdsUtils.showInterstitial(getContext());
    }


}