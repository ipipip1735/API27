package mine.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * Created by Administrator on 2020/7/2.
 */
class OneFragment extends Fragment {


    public OneFragment() {
        System.out.println("------  " + getClass().getSimpleName() + ".Constructor1  ------");
    }

    public static OneFragment newInstance() {
        OneFragment fragment = new OneFragment();
        return fragment;
    }

    public OneFragment(int contentLayoutId) {
        super(contentLayoutId);
        System.out.println("------  " + getClass().getSimpleName() + ".Constructor2  ------");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateView  *********");

        View view = inflater.inflate(R.layout.fragment_one, container, false);

//        Button button = view.findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("~~onClick~~");
//            }
//        });

        return view;
    }
}
