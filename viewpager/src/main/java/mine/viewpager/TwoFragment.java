package mine.viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TwoFragment extends Fragment {

    int position;
    boolean isLoading;

    public TwoFragment(int position) {
        System.out.println(position + "|" + getLifecycle().getCurrentState() + "|" + hashCode() + "|~~" + getClass().getSimpleName() + ".OneFragment~~");
        this.position = position;

        isLoading = true;
        //loading();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println(position + "|" + getLifecycle().getCurrentState() + "|" + hashCode() + "|~~" + getClass().getSimpleName() + ".onCreate~~");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println(position + "|" + getLifecycle().getCurrentState() + "|" + hashCode() + "|~~" + getClass().getSimpleName() + ".onCreateView~~");
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        TextView textView = view.findViewById(R.id.tv);
        textView.setText("position - " + position);

        return view;
    }

    @Override
    public void onStart() {
        System.out.println(position + "|" + getLifecycle().getCurrentState() + "|" + hashCode() + "|~~" + getClass().getSimpleName() + ".onStart~~");
        super.onStart();
    }

    @Override
    public void onResume() {
        System.out.println(position + "|" + getLifecycle().getCurrentState() + "|" + hashCode() + "|~~" + getClass().getSimpleName() + ".onResume~~");
        super.onResume();

        isLoading = true;
        //loading();
    }

    @Override
    public void onPause() {
        System.out.println(position + "|" + getLifecycle().getCurrentState() + "|" + hashCode() + "|~~" + getClass().getSimpleName() + ".onPause~~");
        super.onPause();
    }

    @Override
    public void onStop() {
        System.out.println(position + "|" + getLifecycle().getCurrentState() + "|" + hashCode() + "|~~" + getClass().getSimpleName() + ".onStop~~");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        System.out.println(position + "|" + getLifecycle().getCurrentState() + "|" + hashCode() + "|~~" + getClass().getSimpleName() + ".onDestroyView~~");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        System.out.println(position + "|" + getLifecycle().getCurrentState() + "|" + hashCode() + "|~~" + getClass().getSimpleName() + ".onDestroy~~");
        super.onDestroy();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        System.out.println(position + "|" + getLifecycle().getCurrentState() + "|" + hashCode() + "|~~" + getClass().getSimpleName() + ".setUserVisibleHint~~" + "|isVisibleToUser = " + isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);

        if(isLoading || !isVisibleToUser)return;
        //loading();
    }
}



