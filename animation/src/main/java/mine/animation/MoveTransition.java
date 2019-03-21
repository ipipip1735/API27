package mine.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2019/3/16.
 */
public class MoveTransition extends Transition {

    private static final String X = "mine.animation:MoveTransition:X";
    private static final String Y = "mine.animation:MoveTransition:Y";




    @Override
    public String[] getTransitionProperties() {
        return new String[]{X, Y};
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        transitionValues.values.put(X, transitionValues.view.getX());
        transitionValues.values.put(Y, transitionValues.view.getY());
        System.out.println("captureStartValues " + transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        transitionValues.values.put(X, transitionValues.view.getX());
        transitionValues.values.put(Y, transitionValues.view.getY());
        System.out.println("captureEndValues " + transitionValues);
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        System.out.println("~~createAnimator~~");
        System.out.println(startValues.view); //只有ImageView将被动画，因为其他组件动画值为改变

        //X动画值
        float start = (float) startValues.values.get(X);
        float end = (float) endValues.values.get(X);
        PropertyValuesHolder x = PropertyValuesHolder.ofFloat("x", start, end);

        //Y动画值
        start = (float) startValues.values.get(Y);
        end = (float) endValues.values.get(Y);
        PropertyValuesHolder y = PropertyValuesHolder.ofFloat("y", start, end);

        //绑定更新监听器
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(startValues.view, x, y);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                System.out.println("~~onAnimationUpdate~~");
                System.out.println("value is " + animation.getAnimatedValue());
            }
        });

        //绑定监听器
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                System.out.println("~~onAnimationStart~~");

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                System.out.println("~~onAnimationEnd~~");
            }
        });

        return objectAnimator;
    }
}
