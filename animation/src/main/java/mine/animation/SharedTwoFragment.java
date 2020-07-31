package mine.animation;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.SharedElementCallback;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/3/11.
 */
public class SharedTwoFragment extends Fragment {

    public SharedTwoFragment() {
        System.out.println("+++  " + getClass().getSimpleName() + ".Constructor  +++");

        long duration = 5000L;

        Transition transition = new TransitionSet()
                .setOrdering(TransitionSet.ORDERING_TOGETHER)
                .addTransition(new ChangeClipBounds().setDuration(duration))
                .addTransition(new ChangeTransform().setDuration(duration))
                .addTransition(new ChangeBounds().setDuration(duration));

        setSharedElementEnterTransition(transition);


        setEnterSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                System.out.println("--EnterSharedElementCallback.onSharedElementStart--");
                super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots);
            }

            @Override
            public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                System.out.println("--EnterSharedElementCallback.onSharedElementEnd--");
                super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots);
            }

            @Override
            public void onRejectSharedElements(List<View> rejectedSharedElements) {
                System.out.println("--EnterSharedElementCallback.onRejectSharedElements--");
                super.onRejectSharedElements(rejectedSharedElements);
            }

            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                System.out.println("--EnterSharedElementCallback.onMapSharedElements--");
                super.onMapSharedElements(names, sharedElements);
            }

            @Override
            public Parcelable onCaptureSharedElementSnapshot(View sharedElement, Matrix viewToGlobalMatrix, RectF screenBounds) {
                System.out.println("--EnterSharedElementCallback.onCaptureSharedElementSnapshot--");
                return super.onCaptureSharedElementSnapshot(sharedElement, viewToGlobalMatrix, screenBounds);
            }

            @Override
            public View onCreateSnapshotView(Context context, Parcelable snapshot) {
                System.out.println("--EnterSharedElementCallback.onCreateSnapshotView--");
                return super.onCreateSnapshotView(context, snapshot);
            }

            @Override
            public void onSharedElementsArrived(List<String> sharedElementNames, List<View> sharedElements, OnSharedElementsReadyListener listener) {
                System.out.println("--EnterSharedElementCallback.onSharedElementsArrived--");
                super.onSharedElementsArrived(sharedElementNames, sharedElements, listener);
            }
        });



//        Transition transition = TransitionInflater.from(getContext())
//                .inflateTransition(R.transition.shared_tansition_set);
//        setSharedElementEnterTransition(transition);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateView  *********");

        View view = inflater.inflate(R.layout.fragment_shared_transistion_two, container, false);


        return view;
    }
}