package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.frag;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;

import com.arbelkilani.clock.Clock;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;



import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.R;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.databinding.FragmentAnalogBinding;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ui.AnalogActivity;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ads.AdsUtils;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils.ConfigActivity;

import org.jetbrains.annotations.NotNull;

public class AnalogFragment extends Fragment {

    private static final String TAG = "AnalogClocksFragment";
    Clock clock;
    int counter = 0;
    private FragmentAnalogBinding binding;


    public AnalogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAnalogBinding.inflate(inflater, container, false);
        setClickListeners();
        loadBanner();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        clock = binding.clock601;
        clock = new ConfigActivity().configureClock(clock, getContext(), 601);
        clock = binding.clock602;
        clock = new ConfigActivity().configureClock(clock, getContext(), 602);
        clock = binding.clock603;
        clock = new ConfigActivity().configureClock(clock, getContext(), 603);
        clock = binding.clock604;
        clock = new ConfigActivity().configureClock(clock, getContext(), 604);
        clock = binding.clock605;
        clock = new ConfigActivity().configureClock(clock, getContext(), 605);
        clock = binding.clock606;
        clock = new ConfigActivity().configureClock(clock, getContext(), 606);
        clock = binding.clock607;
        clock = new ConfigActivity().configureClock(clock, getContext(), 607);
        clock = binding.clock608;
        clock = new ConfigActivity().configureClock(clock, getContext(), 608);
        clock = binding.clock609;
        clock = new ConfigActivity().configureClock(clock, getContext(), 609);
        clock = binding.clock610;
        clock = new ConfigActivity().configureClock(clock, getContext(), 610);
        clock = binding.clock611;
        clock = new ConfigActivity().configureClock(clock, getContext(), 611);
        clock = binding.clock612;
        clock = new ConfigActivity().configureClock(clock, getContext(), 612);
        clock = binding.clock613;
        clock = new ConfigActivity().configureClock(clock, getContext(), 613);
        clock = binding.clock614;
        clock = new ConfigActivity().configureClock(clock, getContext(), 614);
        clock = binding.clock615;
        clock = new ConfigActivity().configureClock(clock, getContext(), 615);
        clock = binding.clock616;
        clock = new ConfigActivity().configureClock(clock, getContext(), 616);
        clock = binding.clock617;
        clock = new ConfigActivity().configureClock(clock, getContext(), 617);
        clock = binding.clock618;
        clock = new ConfigActivity().configureClock(clock, getContext(), 618);
        clock = binding.clock619;
        clock = new ConfigActivity().configureClock(clock, getContext(), 619);
        clock = binding.clock620;
        clock = new ConfigActivity().configureClock(clock, getContext(), 620);
        clock = binding.clock621;
        clock = new ConfigActivity().configureClock(clock, getContext(), 621);
        clock = binding.clock622;
        clock = new ConfigActivity().configureClock(clock, getContext(), 622);
        clock = binding.clock623;
        clock = new ConfigActivity().configureClock(clock, getContext(), 623);
        clock = binding.clock624;
        clock = new ConfigActivity().configureClock(clock, getContext(), 624);
        clock = binding.clock625;
        clock = new ConfigActivity().configureClock(clock, getContext(), 625);
        clock = binding.clock626;
        clock = new ConfigActivity().configureClock(clock, getContext(), 626);
        clock = binding.clock627;
        clock = new ConfigActivity().configureClock(clock, getContext(), 627);
        clock = binding.clock628;
        clock = new ConfigActivity().configureClock(clock, getContext(), 628);
        clock = binding.clock629;
        clock = new ConfigActivity().configureClock(clock, getContext(), 629);
        clock = binding.clock630;
        clock = new ConfigActivity().configureClock(clock, getContext(), 630);
        clock = binding.clock631;
        clock = new ConfigActivity().configureClock(clock, getContext(), 631);
        clock = binding.clock632;
        clock = new ConfigActivity().configureClock(clock, getContext(), 632);
        clock = binding.clock633;
        clock = new ConfigActivity().configureClock(clock, getContext(), 633);
        clock = binding.clock634;
        clock = new ConfigActivity().configureClock(clock, getContext(), 634);
        clock = binding.clock635;
        clock = new ConfigActivity().configureClock(clock, getContext(), 635);
        clock = binding.clock636;
        clock = new ConfigActivity().configureClock(clock, getContext(), 636);


    }

    private void setClickListeners() {
        binding.clock601.setOnClickListener(this::analogClockShow);
        binding.clock602.setOnClickListener(this::analogClockShow);
        binding.clock603.setOnClickListener(this::analogClockShow);
        binding.clock604.setOnClickListener(this::analogClockShow);
        binding.clock605.setOnClickListener(this::analogClockShow);
        binding.clock606.setOnClickListener(this::analogClockShow);
        binding.clock607.setOnClickListener(this::analogClockShow);
        binding.clock608.setOnClickListener(this::analogClockShow);
        binding.clock609.setOnClickListener(this::analogClockShow);
        binding.clock610.setOnClickListener(this::analogClockShow);
        binding.clock611.setOnClickListener(this::analogClockShow);
        binding.clock612.setOnClickListener(this::analogClockShow);
        binding.clock613.setOnClickListener(this::analogClockShow);
        binding.clock614.setOnClickListener(this::analogClockShow);
        binding.clock615.setOnClickListener(this::analogClockShow);
        binding.clock616.setOnClickListener(this::analogClockShow);
        binding.clock617.setOnClickListener(this::analogClockShow);
        binding.clock618.setOnClickListener(this::analogClockShow);
        binding.clock619.setOnClickListener(this::analogClockShow);
        binding.clock620.setOnClickListener(this::analogClockShow);
        binding.clock621.setOnClickListener(this::analogClockShow);
        binding.clock622.setOnClickListener(this::analogClockShow);
        binding.clock623.setOnClickListener(this::analogClockShow);
        binding.clock624.setOnClickListener(this::analogClockShow);
        binding.clock625.setOnClickListener(this::analogClockShow);
        binding.clock626.setOnClickListener(this::analogClockShow);
        binding.clock627.setOnClickListener(this::analogClockShow);
        binding.clock628.setOnClickListener(this::analogClockShow);
        binding.clock629.setOnClickListener(this::analogClockShow);
        binding.clock630.setOnClickListener(this::analogClockShow);
        binding.clock631.setOnClickListener(this::analogClockShow);
        binding.clock632.setOnClickListener(this::analogClockShow);
        binding.clock633.setOnClickListener(this::analogClockShow);
        binding.clock634.setOnClickListener(this::analogClockShow);
        binding.clock635.setOnClickListener(this::analogClockShow);
        binding.clock636.setOnClickListener(this::analogClockShow);
    }

    public void analogClockShow(View view) {
        int i;
        switch (view.getId()) {
            case R.id.clock601:
                i = 601;
                break;
            case R.id.clock602:
                i = 602;
                break;
            case R.id.clock603:
                i = 603;
                break;
            case R.id.clock604:
                i = 604;
                break;
            case R.id.clock605:
                i = 605;
                break;
            case R.id.clock606:
                i = 606;
                break;
            case R.id.clock607:
                i = 607;
                break;
            case R.id.clock608:
                i = 608;
                break;
            case R.id.clock609:
                i = 609;
                break;
            case R.id.clock610:
                i = 610;
                break;
            case R.id.clock611:
                i = 611;
                break;
            case R.id.clock612:
                i = 612;
                break;
            case R.id.clock613:
                i = 613;
                break;
            case R.id.clock614:
                i = 614;
                break;
            case R.id.clock615:
                i = 615;
                break;
            case R.id.clock616:
                i = 616;
                break;
            case R.id.clock617:
                i = 617;
                break;
            case R.id.clock618:
                i = 618;
                break;
            case R.id.clock619:
                i = 619;
                break;
            case R.id.clock620:
                i = 620;
                break;
            case R.id.clock621:
                i = 621;
                break;
            case R.id.clock622:
                i = 622;
                break;
            case R.id.clock623:
                i = 623;
                break;
            case R.id.clock624:
                i = 624;
                break;
            case R.id.clock625:
                i = 625;
                break;
            case R.id.clock626:
                i = 626;
                break;
            case R.id.clock627:
                i = 627;
                break;
            case R.id.clock628:
                i = 628;
                break;
            case R.id.clock629:
                i = 629;
                break;
            case R.id.clock630:
                i = 630;
                break;
            case R.id.clock631:
                i = 631;
                break;
            case R.id.clock632:
                i = 632;
                break;
            case R.id.clock633:
                i = 633;
                break;
            case R.id.clock634:
                i = 634;
                break;
            case R.id.clock635:
                i = 635;
                break;
            case R.id.clock636:
                i = 636;
                break;
            default:
                i = 0;
                break;
        }
        Intent intent = new Intent(getActivity(), AnalogActivity.class);
        intent.putExtra("clockNumber", i);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        AdsUtils.showInterstitial(getContext());
    }

    private void loadBanner() {
        AdRequest adRequest2 = new AdRequest.Builder().build();
        binding.adViewRectangle1.loadAd(adRequest2);
        binding.adViewRectangle1.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                binding.adViewRectangle1.setVisibility(View.VISIBLE);
            }
        });



        AdRequest adRequest3 = new AdRequest.Builder().build();
        binding.adViewRectangle2.loadAd(adRequest3);
        binding.adViewRectangle2.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                binding.adViewRectangle2.setVisibility(View.VISIBLE);
            }
        });


        AdRequest adRequest4 = new AdRequest.Builder().build();
        binding.adViewRectangle3.loadAd(adRequest4);
        binding.adViewRectangle3.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                binding.adViewRectangle3.setVisibility(View.VISIBLE);
            }
        });


        AdRequest adRequest5 = new AdRequest.Builder().build();
        binding.adViewRectangle4.loadAd(adRequest5);
        binding.adViewRectangle4.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                binding.adViewRectangle4.setVisibility(View.VISIBLE);
            }
        });
    }


}