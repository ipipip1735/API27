package mine.viewpager;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FourFragment extends Fragment {

    int position;

    public FourFragment(int position) {
        System.out.println(position + "|" + getLifecycle().getCurrentState() + "|" + hashCode() + "|~~" + getClass().getSimpleName() + ".OneFragment~~");
        this.position = position;

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

        textView.setOnTouchListener(new View.OnTouchListener() {
            long timestemp = System.currentTimeMillis();
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("~~" + getClass().getSimpleName() + ".onTouch~~");
                System.out.println("v = " + v + ", event = " + event);

                //在DOWN中拦截
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    getView().getParent().requestDisallowInterceptTouchEvent(true);
//                }

                //根据时间判断，是否要把控制权交给父View
                boolean b = (System.currentTimeMillis() - timestemp) < 5 * 1000L;
                getView().getParent().requestDisallowInterceptTouchEvent(b);

//                return false;
                return true;
            }
        });

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

}



