package com.fabiose.photovideo.utils;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * Created by fabioestrela on 25/02/15.
 */
public class AnimationUtil {

    public static AnimationSet animationFadeIn(long duration, long offSet){
        Animation fadeIn = new AlphaAnimation(1, 0);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(duration);
        fadeIn.setStartOffset(offSet);

        AnimationSet animationFadeIn = new AnimationSet(false);
        animationFadeIn.addAnimation(fadeIn);

        return  animationFadeIn;
    }

    public static AnimationSet animationFadeOut(long duration, long offSet){
        Animation fadeOut = new AlphaAnimation(0, 1);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setStartOffset(duration);
        fadeOut.setDuration(offSet);

        AnimationSet animationFadeOut = new AnimationSet(false);
        animationFadeOut.addAnimation(fadeOut);

        return  animationFadeOut;
    }
}
