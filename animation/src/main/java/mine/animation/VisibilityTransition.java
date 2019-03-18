package mine.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2019/3/16.
 */
public class VisibilityTransition extends Visibility {
    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        System.out.println("~~captureStartValues~~");
        super.captureStartValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        System.out.println("~~captureEndValues~~");
//        System.out.println(transitionValues);
//        transitionValues.values.put(X, 250f);

        super.captureEndValues(transitionValues);
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, final TransitionValues startValues, final TransitionValues endValues) {
        System.out.println("~~createAnimator~~");



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
                    startValues.view.setAlpha((Float) animation.getAnimatedValue());

                }

                if (endValues != null) {
                    System.out.println("end is " + endValues.view);
                    endValues.view.setAlpha((Float) animation.getAnimatedValue());
                }

                if (startValues != null && endValues != null) {
                    System.out.println("start is " + startValues.view);
                    System.out.println("end is " + endValues.view);
                    startValues.view.setAlpha((Float) animation.getAnimatedValue());
                }

            }
        });
//        return valueAnimator;


        Animator animator = super.createAnimator(sceneRoot, startValues, endValues);
        System.out.println(animator);
        return animator;


    }
}
