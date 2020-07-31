package mine.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
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
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionListenerAdapter;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

import static android.transition.Fade.IN;

/**
 * Created by Administrator on 2019/3/11.
 */
public class SharedOneFragment extends Fragment {

    public SharedOneFragment() {
        System.out.println("+++  " + getClass().getSimpleName() + ".Constructor  +++");
        long duration = 5000L;


        Transition transition = new Fade()
                .addTarget(R.id.imageView)
//                .excludeTarget(R.id.imageView, true)
                .setDuration(duration)
                .addListener(new TransitionListenerAdapter() {
                    @Override
                    public void onTransitionStart(Transition transition) {
                        System.out.println("~~onTransitionStart~~");
                    }

                    @Override
                    public void onTransitionEnd(Transition transition) {
                        System.out.println("~~onTransitionEnd~~");
                    }
                });
        setExitTransition(transition);


        setExitSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                System.out.println("--ExitSharedElementCallback.onMapSharedElements--");
                System.out.println("names is " + names);
                System.out.println("sharedElements is " + sharedElements);
            }

            @Override
            public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                System.out.println("--ExitSharedElementCallback.onSharedElementStart--");
            }

            @Override
            public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                System.out.println("--ExitSharedElementCallback.onSharedElementEnd--");
            }

            @Override
            public void onRejectSharedElements(List<View> rejectedSharedElements) {
                System.out.println("--ExitSharedElementCallback.onRejectSharedElements--");
            }

            @Override
            public Parcelable onCaptureSharedElementSnapshot(View sharedElement, Matrix viewToGlobalMatrix, RectF screenBounds) {
                System.out.println("--ExitSharedElementCallback.onCaptureSharedElementSnapshot--");
                return super.onCaptureSharedElementSnapshot(sharedElement, viewToGlobalMatrix, screenBounds);
            }

            @Override
            public View onCreateSnapshotView(Context context, Parcelable snapshot) {
                System.out.println("--ExitSharedElementCallback.onCreateSnapshotView--");
                return super.onCreateSnapshotView(context, snapshot);
            }

            @Override
            public void onSharedElementsArrived(List<String> sharedElementNames, List<View> sharedElements, OnSharedElementsReadyListener listener) {
                System.out.println("--ExitSharedElementCallback.onSharedElementsArrived--");
                super.onSharedElementsArrived(sharedElementNames, sharedElements, listener);
            }
        });



//        setSharedElementEnterTransition(new ChangeBounds().setDuration(duration));

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateView  *********");
        return inflater.inflate(R.layout.fragment_shared_transistion_one, container, false);
    }
}