package mine.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2019/3/16.
 */
public class BaseTransition extends Transition {
    final String X = "Transition:X";
    final String Y = "Transition:Y";
    final String ALPHA = "Transition:ALPHA";


    @Override
    public Animator createAnimator(ViewGroup sceneRoot, final TransitionValues startValues, final TransitionValues endValues) {
        System.out.println("***createAnimator***");
        System.out.println("startValues is " + startValues);
        System.out.println("endValues is " + endValues);





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
                    if(startValues.view.getId() == R.id.cl1)
                        startValues.view.setAlpha((Float) animation.getAnimatedValue());

                }

                if (endValues != null) {
                    System.out.println("end is " + endValues.view);
                    if(startValues.view.getId() == R.id.cl1)
                        endValues.view.setAlpha((Float) animation.getAnimatedValue());
                }

                if (startValues != null && endValues != null) {
                    System.out.println("start is " + startValues.view);
                    System.out.println("end is " + endValues.view);
                    if(startValues.view.getId() == R.id.cl1)
                        startValues.view.setAlpha((Float) animation.getAnimatedValue());
                }

            }
        });
        return valueAnimator;


//        return super.createAnimator(sceneRoot, startValues, endValues);
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        System.out.println("~~captureStartValues~~");
        System.out.println(transitionValues);
        transitionValues.values.put(X, 25f);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        System.out.println("~~captureStartValues~~");
        System.out.println(transitionValues);
        transitionValues.values.put(X, 250f);
    }
}
