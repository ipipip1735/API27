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
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

/**
 * Created by Administrator on 2020/7/2.
 */
public class TwoFragment extends Fragment {

    StringViewModel stringViewModel;

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
//        NavController navController = Navigation.findNavController(view);
//        NavBackStackEntry navBackStackEntry = navController.getCurrentBackStackEntry();//获取当前节点
//        System.out.println("navBackStackEntry = " + navBackStackEntry.getDestination());
//
//        navBackStackEntry = navController.getPreviousBackStackEntry();//获取前一节点
//        System.out.println("navBackStackEntry = " + navBackStackEntry.getDestination());



        //获取参数
//        Bundle bundle = navBackStackEntry.getArguments();//从BackStackEntry获取参数
//        Integer integer = bundle.getInt("one");
//        System.out.println("integer = " + integer);
//
//        bundle = getArguments();//从Fragment获取参数（参数与BackStackEntry中获取的是同一个）
//        integer = bundle.getInt("one");
//        System.out.println("integer = " + integer);



        //获取NavDestination
//        NavController navController = Navigation.findNavController(view);
//        NavBackStackEntry navBackStackEntry = navController.getCurrentBackStackEntry();
//        NavDestination navDestination = navBackStackEntry.getDestination();
//        System.out.println("navDestination = " + navDestination);
//
//        System.out.println("navDestination.getNavigatorName() is " + navDestination.getNavigatorName());
//        System.out.println("navDestination.getLabel() is " + navDestination.getLabel());
//        System.out.println("navDestination.getId() is " + navDestination.getId());



        //获取父节点ViewModel
        NavController navController = Navigation.findNavController(view);
        stringViewModel = new ViewModelProvider(navController.getPreviousBackStackEntry()).get(StringViewModel.class);
        System.out.println("stringViewModel is " + stringViewModel.getName() + "|" + stringViewModel.hashCode());


        //修改父节点数据
//        NavController navController = Navigation.findNavController(view);
//        String name = navController.getPreviousBackStackEntry()
////        String name = navController.getCurrentBackStackEntry()
//                .getSavedStateHandle()
//                .get("name");
//        System.out.println("name = " + name);
//
//        navController.getPreviousBackStackEntry().getSavedStateHandle().set("name", "Jack");//修改父节点数据



    }
}