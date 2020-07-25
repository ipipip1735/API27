package mine.material;

import android.os.Bundle;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.transition.platform.MaterialContainerTransform;

/**
 * Created by Administrator on 2020/7/25.
 */
public class TransitionTwoFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        long duration = 5000L;

        Transition materialContainerTransform = new MaterialContainerTransform()
                .addTarget(android.R.id.content)
                .setDuration(duration);
        setSharedElementEnterTransition(materialContainerTransform);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frgment_transition_two, container, false);
        view.findViewById(R.id.textView).setTransitionName("shared");
        return view;
    }
}