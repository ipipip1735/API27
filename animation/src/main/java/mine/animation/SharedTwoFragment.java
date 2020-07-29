package mine.animation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2019/3/11.
 */
public class SharedTwoFragment extends Fragment {

    public SharedTwoFragment() {
        System.out.println("+++  " + getClass().getSimpleName() + ".Constructor  +++");

//        Transition transition = new Fade().setDuration(1000L);
//        setEnterTransition(transition);
//        setExitTransition(transition);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateView  *********");

//        Transition transition = TransitionInflater.from(getContext())
//                .inflateTransition(R.transition.shared_tansition_set);
//        setSharedElementEnterTransition(transition);

        View view = inflater.inflate(R.layout.fragment_shared_transistion_two, container, false);
        setSharedElementEnterTransition(new ChangeBounds().setDuration(3000L));

        return view;
    }
}