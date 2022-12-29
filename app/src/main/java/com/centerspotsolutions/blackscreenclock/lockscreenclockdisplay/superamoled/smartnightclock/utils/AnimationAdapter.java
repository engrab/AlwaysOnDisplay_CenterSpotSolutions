package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.R;

public class AnimationAdapter extends
        RecyclerView.Adapter<AnimationAdapter.MyViewHolder> {

    public static int rowIndex;
    private final Context context;
    private final int[] clockPreview;
    int clockNumber = 0;
    OnAnimationClickListener onAnimClick;
    SharedPref sharedPref;

    public AnimationAdapter(Context context2, int[] iArr, OnAnimationClickListener onAnimClick) {
        this.context = context2;
        this.clockPreview = iArr;
        this.sharedPref = new SharedPref(context2.getApplicationContext());
        this.onAnimClick = onAnimClick;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.layout_anim_item, viewGroup, false), this.onAnimClick);
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        if (rowIndex == i) {
            myViewHolder.imageView.setBackgroundColor(-16776961);
        } else {
            myViewHolder.imageView.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        }
        Glide.with(this.context).load(this.context.getDrawable(this.clockPreview[i])).into(myViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return this.clockPreview.length;
    }


    public interface OnAnimationClickListener {
        void OnClickListener(int i);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView imageView;
        OnAnimationClickListener onAnimationClickListener;

        MyViewHolder(View view, OnAnimationClickListener onAnimationClickListener) {
            super(view);
            ImageView imageView = view.findViewById(R.id.image_view);
            this.imageView = imageView;
            this.onAnimationClickListener = onAnimationClickListener;
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            this.onAnimationClickListener.OnClickListener(getAdapterPosition());
        }
    }


}
