package mine.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

/**
 * Created by Administrator on 2020/7/2.
 */
public class OneFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateView  *********");

        View view = inflater.inflate(R.layout.fragment_one, container, false);

        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("~~OneFragment.onClick~~");
                Navigation.findNavController(getView())
                        .navigate(R.id.action_oneFragment_to_twoFragment2);

            }
        });

        return view;
    }
}
