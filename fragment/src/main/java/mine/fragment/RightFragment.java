package mine.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;

public class RightFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        System.out.println("~~~ RightFragment.onAttach ~~~");

        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("~~~ RightFragment.onCreate ~~~");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("~~~ RightFragment.onCreateView ~~~");

        View view = inflater.inflate(R.layout.fragment_right, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("~~~ RightFragment.onViewCreated ~~~");

        char[] chars = new char[1024];
        Arrays.fill(chars, 's');
        String strings = new String(chars);
        TextView textView = view.findViewById(R.id.textView);
        System.out.println(textView);
        textView.setText(strings);
//        System.out.println(textView);



    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("~~~ RightFragment.onStart ~~~");

    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("~~~ RightFragment.onResume ~~~");

    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("~~~ RightFragment.onPause ~~~");

    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("~~~ RightFragment.onStop ~~~");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("~~~ RightFragment.onDestroyView ~~~");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("~~~ RightFragment.onDestroy ~~~");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("~~~ RightFragment.onDetach ~~~");

    }

}
