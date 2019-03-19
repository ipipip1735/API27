package mine.animation;

import android.animation.Animator;
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

    private static final String[] sTransitionProperties = {X, Y};


    @Override
    public String[] getTransitionProperties() {
        int i = 0;
        String[] strings = new String[sTransitionProperties.length + super.getTransitionProperties().length];
        for (String s : super.getTransitionProperties()) {
            strings[i++] = s;
        }
        for (String s : sTransitionProperties) {
            strings[i++] = s;
        }
        return strings;
    }


    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        System.out.println("~~captureStartValues~~");

        System.out.println(transitionValues);
        super.captureStartValues(transitionValues);

        transitionValues.values.put(X, transitionValues.view.getX());
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        System.out.println("~~captureEndValues~~");

        super.captureEndValues(transitionValues);
        System.out.println(transitionValues);

        transitionValues.values.put(X, transitionValues.view.getX());

    }

    @Override
    public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        System.out.println("~~onAppear~~");

        System.out.println("startValues is " + startValues);
        System.out.println("endValues is " + endValues);

        return super.onAppear(sceneRoot, view, startValues, endValues);
    }

    @Override
    public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        System.out.println("~~onDisappear~~");

        System.out.println("startValues is " + startValues);
        System.out.println("endValues is " + endValues);

        return super.onDisappear(sceneRoot, view, startValues, endValues);
    }
}
