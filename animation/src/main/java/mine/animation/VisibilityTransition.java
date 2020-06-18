package mine.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2019/3/16.
 */
public class VisibilityTransition extends Visibility {

    private static final String X = "mine.animation:VisibilityTransition:X";
    private static final String Y = "mine.animation:VisibilityTransition:Y";
    private static final String ALPHA = "mine.animation:VisibilityTransition:ALPHA";

    private static final String[] sTransitionProperties = {X, Y, ALPHA};


    @Override
    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }


    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        System.out.println("~~captureStartValues~~");
        super.captureStartValues(transitionValues); //获取父类的动画值

        System.out.println(transitionValues);

        //出场动画值
        transitionValues.values.put(X, transitionValues.view.getX());
        transitionValues.values.put(ALPHA, 1f);

    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        System.out.println("~~captureEndValues~~");
        super.captureEndValues(transitionValues); //获取父类的动画值

        //进场动画值
        transitionValues.values.put(X, transitionValues.view.getX());
        transitionValues.values.put(ALPHA, 1f);

        System.out.println(transitionValues);
    }

    @Override
    public Animator onDisappear(ViewGroup sceneRoot, View view, final TransitionValues startValues, TransitionValues endValues) {
        System.out.println("~~onDisappear~~");
        System.out.println("view is " + view);
        System.out.println("startValues is " + startValues);
        System.out.println("endValues is " + endValues);



        float startX = (Float) startValues.values.get(X);
        float startAlpha = (Float) startValues.values.get(ALPHA);

        //方式一：使用对象动画
        PropertyValuesHolder x = PropertyValuesHolder.ofFloat("x", startX, startX + 100f);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", startAlpha, 0);


        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, x, alpha);
        return objectAnimator;



        //方式二：使用属性动画
        //创建2个动画
//        ValueAnimator x = ValueAnimator.ofFloat(startX, startX-100f);
//        x.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                System.out.println("~~onAnimationUpdate~~");
//                startValues.view.setX((Float) animation.getAnimatedValue());
//            }
//        });
//        ValueAnimator alphal = ValueAnimator.ofFloat(startX, startX-100f);
//        alphal.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                System.out.println("~~onAnimationUpdate~~");
//                startValues.view.setAlpha((Float) animation.getAnimatedValue());
//            }
//        });
//
//        AnimatorSet animatorSet = new AnimatorSet();//创建动画集
//        animatorSet.playTogether(x, alphal);
//        return animatorSet;

//        return super.onDisappear(sceneRoot, view, startValues, endValues);



    }

    @Override
    public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        System.out.println("~~onAppear~~");

        System.out.println("startValues is " + startValues);
        System.out.println("endValues is " + endValues);

        float endX = (float) endValues.values.get(X);
        float endAlpha = (float) endValues.values.get(ALPHA);

        PropertyValuesHolder x = PropertyValuesHolder.ofFloat("x", endX - 100f, endX);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0, endAlpha);


        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, x, alpha);
        return objectAnimator;

//        return super.onAppear(sceneRoot, view, startValues, endValues);
    }
}
