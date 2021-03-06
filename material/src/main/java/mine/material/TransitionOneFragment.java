package mine.material;

import android.graphics.Color;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Fade;
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        setExitTransition(new Hold().setDuration(3000L));//设置非共享元素出场动画（Hold转换用于保持非共享元素让其不要消失）


        MaterialContainerTransform materialContainerTransform = new MaterialContainerTransform();
        materialContainerTransform.setDuration(3000L);
        materialContainerTransform.setScrimColor(Color.TRANSPARENT);//禁用纱布（透明纱布）
        setSharedElementEnterTransition(materialContainerTransform);//设置共享元素入场动画
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateView  *********");
        return inflater.inflate(R.layout.frgment_transition_one, container, false);
    }
}