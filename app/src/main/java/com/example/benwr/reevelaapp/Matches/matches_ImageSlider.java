package com.example.benwr.reevelaapp.Matches;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.benwr.reevelaapp.R;

public class matches_ImageSlider extends PagerAdapter {
    private Context ImgContext;
    private int[] SlideImgID = new int[]{R.drawable.kelly, R.drawable.kelly2, R.drawable.kelly3, R.drawable.kelly4};

    matches_ImageSlider(Context context) {
        ImgContext = context;

    }

    @Override
    public int getCount() {
        return SlideImgID.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(ImgContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(SlideImgID[position]);
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
