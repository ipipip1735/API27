package mine.viewpager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Random;

public class OneFragment extends Fragment {

    int position;

    public OneFragment(int position) {
        this.position = position;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TabsActivity activity = (TabsActivity) getActivity();
        new TabLayoutMediator(activity.mTabLayout, activity.mPager, true,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        System.out.println("~~onConfigureTab~~");
                        System.out.println("tab is " + tab);
                        System.out.println("position is " + position);

                        tab.setText("AAA" + position);
                        tab.setIcon(R.drawable.ic_launcher_background);
                    }
                }).attach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        TextView textView = view.findViewById(R.id.tv);
        textView.setText("position - " + position);

        return view;
    }


}
