package mine.animation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2019/3/11.
 */
public class BackFragment extends Fragment {

    public BackFragment() {
        System.out.println("+++  " + getClass().getSimpleName() + ".Constructor  +++");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateView  *********");


        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_fore, container, false);
        viewGroup.setBackgroundColor(getResources().getColor(R.color.TURQUOISE, null));
        ImageView imageView = viewGroup.findViewById(R.id.imageView3);
        imageView.setImageResource(R.drawable.b);

        return viewGroup;

    }
}