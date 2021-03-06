package mine.material;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.transition.platform.MaterialSharedAxis;

/**
 * Created by Administrator on 2020/7/28.
 */
public class AxisTwoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateView  *********");

        long duration = 5000L;
        setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.Y, true).setDuration(duration));
        setReturnTransition(new MaterialSharedAxis(MaterialSharedAxis.Y, false).setDuration(duration));

        return inflater.inflate(R.layout.frgment_axis_two, container, false);
    }
}