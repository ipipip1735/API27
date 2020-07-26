package mine.material;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.transition.platform.Hold;
import com.google.android.material.transition.platform.MaterialContainerTransform;

/**
 * Created by Administrator on 2020/7/25.
 */
public class TransitionOneFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateView  *********");
        setExitTransition(new Hold().setDuration(1000L));//设置非共享元素出场动画（Hold转换用于保持非共享元素让其不要消失）
        return inflater.inflate(R.layout.frgment_transition_one, container, false);
    }
}