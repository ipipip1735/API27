package mine.animation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.Fade;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2019/3/11.
 */
public class SharedOneFragment extends Fragment {

    public SharedOneFragment() {
        System.out.println("+++  " + getClass().getSimpleName() + ".Constructor  +++");

//        Transition transition = new Fade().setDuration(1000L);
//        setEnterTransition(transition);
//        setExitTransition(transition);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateView  *********");
        return inflater.inflate(R.layout.fragment_shared_transistion_one, container, false);
    }
}