package mine.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class LeftFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        System.out.println("~~~ LeftFragment.onAttach ~~~");

        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("~~~ LeftFragment.onCreate ~~~");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("~~~ LeftFragment.onCreateView ~~~");

        View view = inflater.inflate(R.layout.fragment_left, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("~~~ LeftFragment.onViewCreated ~~~");

        Button button = view.findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RightFragment rightFragment = (RightFragment) getFragmentManager().findFragmentByTag("right");
                System.out.println(rightFragment);
                TextView textView = rightFragment.getView().findViewById(R.id.textView);
                textView.setText("XX");


            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("~~~ LeftFragment.onStart ~~~");

    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("~~~ LeftFragment.onResume ~~~");

    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("~~~ LeftFragment.onPause ~~~");

    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("~~~ LeftFragment.onStop ~~~");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("~~~ LeftFragment.onDestroyView ~~~");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("~~~ LeftFragment.onDestroy ~~~");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("~~~ LeftFragment.onDetach ~~~");

    }


}
