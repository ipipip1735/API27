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
        super.onAttach(context);
        System.out.println("*********  " + hashCode() +  "|" + getClass().getSimpleName() + ".onAttach  *********");

//        System.out.println(context);
//        System.out.println(getActivity());

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + hashCode() +  "|" + getClass().getSimpleName() + ".onCreate  *********");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("*********  " + hashCode() +  "|" + getClass().getSimpleName() + ".onCreateView  *********");
        System.out.println("inflater = " + inflater + ", container = " + container + ", savedInstanceState = " + savedInstanceState);
        System.out.println("hashCode() is " + hashCode());
        System.out.println("getTag is " + getTag());

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_text, container, false);
        TextView textView = viewGroup.findViewById(R.id.tv);
        textView.setText(getArguments().getString("text", "null"));

        return viewGroup;
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("*********  " + hashCode() +  "|" + getClass().getSimpleName() + ".onStart  *********");

    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("*********  " + hashCode() +  "|" + getClass().getSimpleName() + ".onResume  *********");

    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("*********  " + hashCode() +  "|" + getClass().getSimpleName() + ".onPause  *********");

    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("*********  " + hashCode() +  "|" + getClass().getSimpleName() + ".onStop  *********");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("*********  " + hashCode() +  "|" + getClass().getSimpleName() + ".onDestroyView  *********");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("*********  " + hashCode() +  "|" + getClass().getSimpleName() + ".onDestroy  *********");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("*********  " + hashCode() +  "|" + getClass().getSimpleName() + ".onDetach  *********");

    }


}
