package mine.navigation;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

/**
 * Created by Administrator on 2020/7/12.
 */
public class ThreeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateView  *********");

        Bundle b = getArguments();
        ThreeFragmentArgs args = ThreeFragmentArgs.fromBundle(b);
        System.out.println("one is " + args.getOne());
        System.out.println("two is " + args.getTwo());



        View view = inflater.inflate(R.layout.fragment_three, container, false);

        return view;
    }
}