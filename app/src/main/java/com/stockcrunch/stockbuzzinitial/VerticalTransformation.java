package com.stockcrunch.stockbuzzinitial;

import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

public class VerticalTransformation implements ViewPager2.PageTransformer {
    private static final float MIN_SCALE = 0.9f;
    private final int version = Build.VERSION.SDK_INT;

    @Override
    public void transformPage(@NonNull View view, float position) {

        if (position < -1) { // [-Infinity,-1)
            view.setAlpha(0);


        } else if (position <= 0) { // [-1,0]

            view.setAlpha(1);
            view.setTranslationY(0);
            if (version >= 21) {
                view.setTranslationZ(0f);
            }
            view.setScaleX(1);
            view.setScaleY(1);


        } else if (position <= 1) { // (0,1]
            view.setAlpha(1 - position);
            view.setTranslationY(view.getHeight() * -position);
            if (version >= 21) {
                view.setTranslationZ(-1f);
            }

            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);


        } else { // (1,+Infinity]
            view.setAlpha(0);
        }

    }

}
