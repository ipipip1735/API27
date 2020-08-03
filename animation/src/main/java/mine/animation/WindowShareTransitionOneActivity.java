package mine.animation;

import android.app.ActivityOptions;
import android.app.SharedElementCallback;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionListenerAdapter;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;
import java.util.Map;

import static android.view.Gravity.TOP;

/**
 * Created by Administrator on 2019/3/20.
 */
public class WindowShareTransitionOneActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle bundle) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");


        long duration = 5000L;
        Window window = getWindow();


        //非共享元素钩子函数
        TransitionListenerAdapter adapter = new TransitionListenerAdapter() {
            @Override
            public void onTransitionStart(Transition transition) {
                System.out.println("~~Fade.onTransitionStart~~");
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                System.out.println("~~Fade.onTransitionEnd~~");
            }

        };


        //共享元素转换钩子函数
        SharedElementCallback sharedElementCallback = new SharedElementCallback() {
            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                System.out.println("...EnterSharedElementCallback.onMapSharedElements...");
                System.out.println("names is " + names);
                System.out.println("sharedElements is " + sharedElements);

//                names.remove(0);
//                sharedElements.remove(names.get(0));
            }

            @Override
            public void onSharedElementsArrived(List<String> sharedElementNames, List<View> sharedElements, OnSharedElementsReadyListener listener) {
//                System.out.println("...EnterSharedElementCallback.onSharedElementsArrived...");
//                System.out.println("sharedElementNames is " + sharedElementNames);
//                System.out.println("sharedElements is " + sharedElements);
//                System.out.println("listener is " + listener);


                super.onSharedElementsArrived(sharedElementNames, sharedElements, listener);
            }

            @Override
            public void onRejectSharedElements(List<View> rejectedSharedElements) {
                System.out.println("...EnterSharedElementCallback.onRejectSharedElements...");
//                System.out.println("rejectedSharedElements is " + rejectedSharedElements);
//                rejectedSharedElements.remove(0);
            }

            @Override
            public View onCreateSnapshotView(Context context, Parcelable snapshot) {
                System.out.println("...EnterSharedElementCallback.onCreateSnapshotView...");
//                System.out.println("context is " + context);
//                System.out.println("snapshot is " + snapshot);

//                Bitmap bitmap = (Bitmap) snapshot;
//                System.out.println(bitmap.getByteCount());

                return super.onCreateSnapshotView(context, snapshot);
//                return null;
            }

            @Override
            public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                System.out.println("...EnterSharedElementCallback.onSharedElementStart...");
//                System.out.println("sharedElementNames is " + sharedElementNames);
//                System.out.println("sharedElements is " + sharedElements);
//                System.out.println("sharedElementSnapshots is " + sharedElementSnapshots);

                View v = sharedElementSnapshots.get(0);
                System.out.println(v);
                System.out.println(v.getX());
                System.out.println(v.getY());
                System.out.println(v.getWidth() + ", " + v.getHeight() + "|" + v.getLeft() + ", " + v.getTop() + ", " + v.getRight() + ", " + v.getBottom());

                v = sharedElementSnapshots.get(1);
                System.out.println(v);
                System.out.println(v.getX());
                System.out.println(v.getY());
                System.out.println(v.getWidth() + ", " + v.getHeight() + "|" + v.getLeft() + ", " + v.getTop() + ", " + v.getRight() + ", " + v.getBottom());


                v = sharedElements.get(0);
                System.out.println(v);
//                v.setTop(63);
                System.out.println(v.getX());
                System.out.println(v.getY());
                System.out.println(v.getWidth() + ", " + v.getHeight() + "|" + v.getLeft() + ", " + v.getTop() + ", " + v.getRight() + ", " + v.getBottom());

                v = sharedElements.get(1);
                System.out.println(v);
//                v.setTop(63);
                System.out.println(v.getX());
                System.out.println(v.getY());
                System.out.println(v.getWidth() + ", " + v.getHeight() + "|" + v.getLeft() + ", " + v.getTop() + ", " + v.getRight() + ", " + v.getBottom());

            }

            @Override
            public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                System.out.println("...EnterSharedElementCallback.onSharedElementEnd...");
//                System.out.println("sharedElementNames is " + sharedElementNames);
//                System.out.println("sharedElements is " + sharedElements);
//                System.out.println("sharedElementSnapshots is " + sharedElementSnapshots);


                View v = sharedElementSnapshots.get(0);
                System.out.println(v);
                System.out.println(v.getX());
                System.out.println(v.getY());
                System.out.println(v.getWidth() + ", " + v.getHeight() + "|" + v.getLeft() + ", " + v.getTop() + ", " + v.getRight() + ", " + v.getBottom());
                v = sharedElementSnapshots.get(1);
                System.out.println(v);
                System.out.println(v.getX());
                System.out.println(v.getY());
                System.out.println(v.getWidth() + ", " + v.getHeight() + "|" + v.getLeft() + ", " + v.getTop() + ", " + v.getRight() + ", " + v.getBottom());


                v = sharedElements.get(0);
                System.out.println(v);
                v.setBottom(1400);
                System.out.println(v.getX());
                System.out.println(v.getY());
                System.out.println(v.getWidth() + ", " + v.getHeight() + "|" + v.getLeft() + ", " + v.getTop() + ", " + v.getRight() + ", " + v.getBottom());
                System.out.println(v);
                System.out.println(v.getX());
                System.out.println(v.getY());
                System.out.println(v.getWidth() + ", " + v.getHeight() + "|" + v.getLeft() + ", " + v.getTop() + ", " + v.getRight() + ", " + v.getBottom());

            }

            @Override
            public Parcelable onCaptureSharedElementSnapshot(View sharedElement, Matrix viewToGlobalMatrix, RectF screenBounds) {
                System.out.println("...EnterSharedElementCallback.onCaptureSharedElementSnapshot...");
                return super.onCaptureSharedElementSnapshot(sharedElement, viewToGlobalMatrix, screenBounds);
            }
        };

        //创建转换对象
        final Transition fade = new Fade().setDuration(duration).addListener(adapter);
        Transition explode = new Explode().setDuration(duration).addListener(adapter);
        Transition slide = new Slide(TOP).setDuration(duration).addListener(adapter);
        Transition changesBounds = new ChangeBounds().setDuration(duration).addListener(adapter);


        //设置共享组件转换对象
        window.setSharedElementEnterTransition(changesBounds); //共享组件进入变换
        window.setEnterTransition(fade);
//        window.setReturnTransition(fade);
//        window.setSharedElementReturnTransition(changesBounds);  //共享组件返回变换，优先级高于SharedElementReenterTransition
//        window.setTransitionBackgroundFadeDuration(5000L);

        setEnterSharedElementCallback(sharedElementCallback);//绑定进入共享元素回调


        super.onCreate(bundle);
        setContentView(R.layout.activity_window_share_one);
        findViewById(R.id.siv).setTransitionName("sharedOne");
        findViewById(R.id.sivo).setTransitionName("sharedTwo");
//        findViewById(android.R.id.content).setTransitionName("content");

    }

    @Override
    protected void onNewIntent(Intent intent) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onNewIntent  *********");
        super.onNewIntent(intent);
    }


    @Override
    protected void onStart() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestoreInstanceState  *********");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestart  *********");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onResume  *********");
        super.onResume();


    }

    @Override
    protected void onPause() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onBackPressed  *********");
        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");
        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onSaveInstanceState  *********");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");
        super.onDestroy();
    }


    public void stop(View view) {
        System.out.println("********stop******");

        finishAfterTransition();

    }

    public void start(View view) {
        System.out.println("********start******");

    }


    public void sharedStart(View view) {
        System.out.println("********sharedStart******");
    }

    public void sharedStop(View view) {
        System.out.println("********sharedStop******");
    }
}