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

public class TextFragment extends Fragment {

    public TextFragment() {
        System.out.println("+++  " + getClass().getSimpleName() + ".Constructor  +++");
    }

    @Override
    public void onAttach(Context context) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onAttach  *********");
        super.onAttach(context);

//        System.out.println(context);
//        System.out.println(getActivity());

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateView  *********");
        System.out.println("getId is " + getId());
        System.out.println("getTag is " + getTag());

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_text, container, false);
        TextView textView = viewGroup.findViewById(R.id.tv);
        textView.setText(getArguments().getString("text", "null"));

        return viewGroup;
    }

    @Override
    public void onStart() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        super.onStart();

    }

    @Override
    public void onResume() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onResume  *********");
        super.onResume();

    }

    @Override
    public void onPause() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");
        super.onPause();

    }

    @Override
    public void onStop() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");
        super.onStop();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroyView  *********");

    }

    @Override
    public void onDestroy() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");
        super.onDestroy();

    }

    @Override
    public void onDetach() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onDetach  *********");
        super.onDetach();

    }


}
