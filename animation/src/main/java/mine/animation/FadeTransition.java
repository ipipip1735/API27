package mine.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.transition.Fade;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2019/3/16.
 */
public class FadeTransition extends Fade {
    @Override
    public Animator onAppear(ViewGroup sceneRoot, final TransitionValues startValues, int startVisibility, final TransitionValues endValues, int endVisibility) {
        System.out.println("~~onAppear~~");
//        return super.onAppear(sceneRoot, startValues, startVisibility, endValues, endVisibility);

        System.out.println("sceneRoot is " + sceneRoot);
        System.out.println("startValues is " + startValues);
        System.out.println("startVisibility is " + startVisibility);
        System.out.println("endValues is " + endValues);
        System.out.println("endVisibility is " + endVisibility);


        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
//        valueAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                System.out.println("~~onAnimationStart~~");
//                super.onAnimationStart(animation);
//                if(startValues != null)
//                    System.out.println("start is " + startValues.view);
//
//                if(endValues != null)
//                    System.out.println("end is " + endValues.view);
//            }
//        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {


                if (startValues != null) {
                    System.out.println("start is " + startValues.view);
//                    if(startValues.view.getId() == R.id.cl1)
                    startValues.view.setAlpha((Float) animation.getAnimatedValue());

                }

                if (endValues != null) {
                    System.out.println("end is " + endValues.view);
//                    if(endValues.view.getId() == R.id.cl1)
                    endValues.view.setAlpha((Float) animation.getAnimatedValue());
                }

                if (startValues != null && endValues != null) {
                    System.out.println("defualt is " + startValues.view);
                    System.out.println("end is " + endValues.view);
//                    if(startValues.view.getId() == R.id.cl1)
                    startValues.view.setAlpha((Float) animation.getAnimatedValue());
                }

            }
        });
//        return valueAnimator;

        return null;
    }


    @Override
    public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        System.out.println("~~Fade.onDisappear~~");
        System.out.println("sceneRoot is " + sceneRoot);
        System.out.println("startValues is " + startValues);
        System.out.println("endValues is " + endValues);


        return ValueAnimator.ofFloat(0f, 1f);
//        return super.onDisappear(sceneRoot, view, startValues, endValues);

    }

    @Override
    public Animator onDisappear(ViewGroup sceneRoot, final TransitionValues startValues, int startVisibility, final TransitionValues endValues, int endVisibility) {
        System.out.println("~~Visibility.onDisappear~~");
        System.out.println("sceneRoot is " + sceneRoot);
        System.out.println("startValues is " + startValues);
        System.out.println("startVisibility is " + startVisibility);
        System.out.println("endValues is " + endValues);
        System.out.println("endVisibility is " + endVisibility);

//        return super.onDisappear(sceneRoot, view, startValues, endValues);
        return super.onDisappear(sceneRoot, startValues, startVisibility, endValues, endVisibility);
    }
}
