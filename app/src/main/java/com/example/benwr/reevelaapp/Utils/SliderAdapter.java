package com.example.benwr.reevelaapp.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.benwr.reevelaapp.R;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

/**
 * __________________________________________________________________________
 * <p>
 * Slider adapter used when users first sign up to the application.
 * __________________________________________________________________________
 */


public class SliderAdapter  extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;



    }

    //Arrays

    public int[] slide_images = {

            R.drawable.eye,
            R.drawable.games2,
            R.drawable.users
    };

    public String [] slide_headings = {

            "REVEAL",
            "PLAY",
            "MATCH"


    };

    public String [] slide_descriptions = {

            "Step by step Reveal the different parts of possible matches profile.",
            "Challenge possible matches with a quick game to double reveal them.",
            "Talk, challenge and get to know the users you match with. "

    };


    @Override
    public int getCount() {

        return slide_headings.length;

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.imageView);
        TextView slideHeading = (TextView) view.findViewById(R.id.textView);
        TextView slidePara = (TextView) view.findViewById(R.id.textView2);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slidePara.setText(slide_descriptions[position]);

        container.addView(view);

        return view;
    };

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
