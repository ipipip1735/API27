package mine.navigation;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

/**
 * Created by Administrator on 2020/7/2.
 */
public class TwoFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onAttach  *********");
        super.onCreate(savedInstanceState);

        //拦截back按钮事件
//        requireActivity().getOnBackPressedDispatcher()
//                .addCallback(this, new OnBackPressedCallback(true) {
//                    @Override
//                    public void handleOnBackPressed() {
//                        System.out.println("~~OneFragment.handleOnBackPressed~~");
//                    }
//                });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateView  *********");


//        System.out.println("one is " + getArguments().getInt("one"));//直接获取Bundle中的参数

//        Bundle b = getArguments();
//        TwoFragmentArgs args = TwoFragmentArgs.fromBundle(b);
//        System.out.println("one is " + args.getOne());
//        System.out.println("two is " + args.getTwo());


//        setSharedElementEnterTransition(new Slide().setDuration(5000L));
        setSharedElementEnterTransition(new ChangeBounds().setDuration(5000L));



        View view = inflater.inflate(R.layout.fragment_two, container, false);

        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("~~TwoFragment.onClick~~");

//                Navigation.findNavController(getView()).navigate(R.id.action_twoFragment_to_oneFragment);


                FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                        .addSharedElement(v, "sss")
                        .build();
                Navigation.findNavController(v).navigate(R.id.action_twoFragment_to_oneFragment, null, null, extras);
            }
        });

        return view;
    }
}