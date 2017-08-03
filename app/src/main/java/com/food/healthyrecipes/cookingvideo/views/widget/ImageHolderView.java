package com.food.healthyrecipes.cookingvideo.views.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.food.healthyrecipes.cookingvideo.DataBanner;
import com.food.healthyrecipes.cookingvideo.R;
import com.freegeek.android.materialbanner.holder.Holder;

/**
 * Created by hoanghiep on 8/3/17.
 */

public class ImageHolderView implements Holder<DataBanner> {
    private static final String TAG = ImageHolderView.class.getSimpleName();
    private AppCompatImageView imageView;
    private ProgressBar progressBar;

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_banner, new FrameLayout(context), false);
        imageView = view.findViewById(R.id.imageView);
        progressBar = view.findViewById(R.id.progress);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return view;
    }

    @Override
    public void UpdateUI(Context context, int position, DataBanner data) {
        SimpleTarget<Bitmap> simpleTarget = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                progressBar.setVisibility(View.GONE);
                imageView.setImageBitmap(resource);
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                progressBar.setVisibility(View.GONE);
            }
        };
        Glide.with(context)
                .asBitmap()
                .load(data.getUrl())
                .into(simpleTarget);
    }
}
