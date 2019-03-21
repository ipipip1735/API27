package mine.animation;

import android.transition.Transition;
import android.transition.TransitionPropagation;
import android.transition.TransitionValues;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2019/3/21.
 */
public class BaseTransitionPropagation extends TransitionPropagation {

    String X = "appname:transitionname:rotation";

    @Override
    public long getStartDelay(ViewGroup sceneRoot, Transition transition, TransitionValues startValues, TransitionValues endValues) {
        System.out.println("~~getStartDelay~~");
        System.out.println("transition is " + transition);
        System.out.println("startValues is " + startValues);
        System.out.println("endValues is " + endValues);
        return 9500;
    }

    @Override
    public void captureValues(TransitionValues transitionValues) {
        System.out.println("~~captureValues~~");
        System.out.println("transitionValues is " + transitionValues);

    }

    @Override
    public String[] getPropagationProperties() {
        System.out.println("~~getPropagationProperties~~");

        return new String[]{X};
    }
}
