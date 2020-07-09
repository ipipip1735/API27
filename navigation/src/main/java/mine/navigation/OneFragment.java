package mine.navigation;

import android.content.Context;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

/**
 * Created by Administrator on 2020/7/2.
 */
public class OneFragment extends Fragment {

    @Override
    public void onAttach(@NonNull Context context) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onAttach  *********");
        super.onAttach(context);

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

        View view = inflater.inflate(R.layout.fragment_one, container, false);

        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("~~OneFragment.onClick~~");

                //方式一：使用Bundle传递数据
//                Bundle args = new Bundle();
////                args.putInt("one", 111);
////                args.putInt("two", 222);
//                Navigation.findNavController(v).navigate(R.id.action_oneFragment_to_twoFragment, args);


                //方式二：使用安全参数传递数据
//                Navigation.findNavController(v)
//                        .navigate(OneFragmentDirections.actionOneFragmentToTwoFragment()
//                                .setOne(111).setTwo(222));


                FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                        .addSharedElement(v, "ftwo_sn")
                        .build();
                Navigation.findNavController(v).navigate(R.id.action_oneFragment_to_twoFragment,
                        null, null,
                        extras);


            }
        });

        return view;
    }
}
