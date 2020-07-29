package mine.material;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.transition.MaterialFadeThrough;
import com.google.android.material.transition.platform.MaterialSharedAxis;

/**
 * Created by Administrator on 2020/7/28.
 */
public class FadeOneFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        long duration = 5000L;
        setEnterTransition(new MaterialFadeThrough().setDuration(duration));
        setExitTransition(new MaterialFadeThrough().setDuration(duration));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateView  *********");
        return inflater.inflate(R.layout.frgment_fade_one, container, false);
    }
}