package mine.material;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.transition.MaterialFadeThrough;
import com.google.android.material.transition.platform.MaterialSharedAxis;

/**
 * Created by Administrator on 2020/7/28.
 */
public class FadeOneFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateView  *********");

        long duration = 5000L;
        setExitTransition(new MaterialFadeThrough()
                .setDuration(duration));
//        setReenterTransition(new MaterialFadeThrough()
//                .setDuration(duration));

        return inflater.inflate(R.layout.frgment_fade_one, container, false);
    }
}