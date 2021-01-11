package mine.navigation;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
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


////        setSharedElementEnterTransition(new Slide().setDuration(5000L));
//        setSharedElementEnterTransition(new ChangeBounds().setDuration(5000L));



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


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        System.out.println("~~TwoFragment.onViewCreated~~");
        System.out.println("view = " + view + ", savedInstanceState = " + savedInstanceState);
        super.onViewCreated(view, savedInstanceState);


        //导航回退栈
        NavController navController = Navigation.findNavController(view);
        NavBackStackEntry navBackStackEntry = navController.getCurrentBackStackEntry();
        System.out.println("navBackStackEntry = " + navBackStackEntry.getDestination());

        navBackStackEntry = navController.getPreviousBackStackEntry();
        System.out.println("navBackStackEntry = " + navBackStackEntry.getDestination());





//        NavBackStackEntry navBackStackEntry = Navigation.findNavController(view).getBackStackEntry(R.id.twoFragment);
//        System.out.println("navBackStackEntry = " + navBackStackEntry);
//
//        StringViewModel stringViewModel = new ViewModelProvider(navBackStackEntry).get(StringViewModel.class);
//        System.out.println(stringViewModel.getName());






    }
}