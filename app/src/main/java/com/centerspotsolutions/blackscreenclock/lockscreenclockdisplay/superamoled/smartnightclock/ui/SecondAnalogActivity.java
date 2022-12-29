package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.R;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils.CustomAnalogClockView;


public class SecondAnalogActivity extends AppCompatActivity {


    public static int selectedHourNeedle;
    public static int selectedMinuteNeedle;
    public static int selectedSecondNeedle;
    public static int selectedWatchFace;
    int[] hourNeedles = {R.drawable.needle_hour1, R.drawable.needle_hour2, R.drawable.needle_hour3, R.drawable.needle_hour4, R.drawable.needle_hour5, R.drawable.needle_hour6, R.drawable.needle_hour7, R.drawable.needle_hour8, R.drawable.needle_hour9, R.drawable.needle_hour10, R.drawable.needle_hour11, R.drawable.needle_hour12, R.drawable.needle_hour13, R.drawable.needle_hour14, R.drawable.needle_hour15, R.drawable.needle_hour16, R.drawable.needle_hour17, R.drawable.needle_hour18, R.drawable.needle_minute19, R.drawable.needle_hour20, R.drawable.needle_hour21, R.drawable.needle_hour22};
    int[] minuteNeedles = {R.drawable.needle_minute1, R.drawable.needle_minute2, R.drawable.needle_minute3, R.drawable.needle_minute4, R.drawable.needle_minute5, R.drawable.needle_minute6, R.drawable.needle_minute7, R.drawable.needle_minute8, R.drawable.needle_minute9, R.drawable.needle_minute10, R.drawable.needle_minute11, R.drawable.needle_minute12, R.drawable.needle_minute13, R.drawable.needle_minute14, R.drawable.needle_minute15, R.drawable.needle_minute16, R.drawable.needle_minute17, R.drawable.needle_minute18, R.drawable.needle_hour19, R.drawable.needle_minute20, R.drawable.needle_minute21, R.drawable.needle_minute22};
    RecyclerView recyclerView;
    int[] secondNeedles = {R.drawable.needle_second1, R.drawable.needle_second2, R.drawable.needle_second3, R.drawable.needle_second4, R.drawable.needle_second5, R.drawable.needle_second6, R.drawable.needle_second7, R.drawable.needle_second8, R.drawable.needle_second9, R.drawable.needle_second10, R.drawable.needlesecond11, R.drawable.needle_second12, R.drawable.needle_second13, R.drawable.needle_second14, R.drawable.needle_second15, R.drawable.needle_second16, R.drawable.needle_second17, R.drawable.needle_second18, R.drawable.needle_second19, R.drawable.needle_second20, R.drawable.needle_second21, R.drawable.needle_second22};
    int[] watchFaces = {R.drawable.bg_dial1, R.drawable.bg_dial2, R.drawable.bg_dial3, R.drawable.bg_dial4, R.drawable.bg_dial5, R.drawable.bg_dial6, R.drawable.bg_dial7, R.drawable.bg_dial8, R.drawable.bg_dial9, R.drawable.bg_dial10, R.drawable.bg_dial11, R.drawable.bg_dial12, R.drawable.bg_dial13, R.drawable.bg_dial14, R.drawable.bg_dial15, R.drawable.bg_dial16, R.drawable.bg_dial17, R.drawable.bg_dial18, R.drawable.bg_dial19, R.drawable.bg_dial20, R.drawable.bg_dial21, R.drawable.bg_dial22};


    @Override

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_second_analog_clocks);
        this.recyclerView = findViewById(R.id.recycler_view_clocks_list);
        ClocksAdapter clocksAdapter = new ClocksAdapter(this.watchFaces, this.hourNeedles, this.minuteNeedles, this.secondNeedles, getLayoutInflater());
        this.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        this.recyclerView.setAdapter(clocksAdapter);
    }

    public class ClocksAdapter extends RecyclerView.Adapter<ClocksViewHolder> {
        int[] hour_needles;
        LayoutInflater layoutInflater;
        int[] minute_needles;
        int[] second_needles;
        int[] watch_faces;

        public ClocksAdapter(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, LayoutInflater layoutInflater2) {
            this.watch_faces = iArr;
            this.hour_needles = iArr2;
            this.minute_needles = iArr3;
            this.second_needles = iArr4;
            this.layoutInflater = layoutInflater2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ClocksViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ClocksViewHolder(this.layoutInflater.inflate(R.layout.clock_list_single_layout, viewGroup, false));
        }

        public void onBindViewHolder(ClocksViewHolder clocksViewHolder, int i) {
            clocksViewHolder.setClock(this.watch_faces[i], this.hour_needles[i], this.minute_needles[i], this.second_needles[i]);
        }

        @Override
        public int getItemCount() {
            return this.watch_faces.length;
        }
    }

    public class ClocksViewHolder extends RecyclerView.ViewHolder {
        View view;

        public ClocksViewHolder(View view2) {
            super(view2);
            this.view = view2;

            view2.setOnClickListener(v -> {
                SecondAnalogActivity.selectedWatchFace = SecondAnalogActivity.this.watchFaces[ClocksViewHolder.this.getAdapterPosition()];
                SecondAnalogActivity.selectedHourNeedle = SecondAnalogActivity.this.hourNeedles[ClocksViewHolder.this.getAdapterPosition()];
                SecondAnalogActivity.selectedMinuteNeedle = SecondAnalogActivity.this.minuteNeedles[ClocksViewHolder.this.getAdapterPosition()];
                SecondAnalogActivity.selectedSecondNeedle = SecondAnalogActivity.this.secondNeedles[ClocksViewHolder.this.getAdapterPosition()];
                SecondAnalogActivity.this.startActivity(new Intent(SecondAnalogActivity.this, AnalogActivity.class));
            });
        }

        public void setClock(int i, int i2, int i3, int i4) {
            CustomAnalogClockView customAnalogClockView = findViewById(R.id.clock_view);
        }
    }
}
