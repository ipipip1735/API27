package mine.animation;

import android.app.ActivityOptions;
import android.app.SharedElementCallback;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
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
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import java.util.List;
import java.util.Map;

import static android.view.Gravity.LEFT;
import static android.view.Gravity.TOP;

/**
 * Created by Administrator on 2019/3/20.
 */
public class WindowShareTransitionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle bundle) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        long duration = 2000L;
        Window window = getWindow();


        //共享元素转换钩子函数
        SharedElementCallback sharedElementCallback = new SharedElementCallback() {
            boolean first = true;

            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                System.out.println("--ExitSharedElementCallback.onMapSharedElements--");
//                System.out.println("names is " + names);
//                System.out.println("sharedElements is " + sharedElements);

//                if (first) {
//
//
//                    //删除共享元素
////                    sharedElements.remove(names.get(0));
////                    names.remove(0);
//
//
//                    //增加共享元素
////                    sharedElements.put("sharedTwo", findViewById(R.id.twoIV));
////                    names.add("sharedTwo");
//
//
//                    //替换共享元素
////                    sharedElements.put("sharedTwo", findViewById(R.id.twoIV));
//
//
//                    first = false;
//                } else {
//                    first = true;
//                }
            }

            @Override
            public Parcelable onCaptureSharedElementSnapshot(View sharedElement, Matrix viewToGlobalMatrix, RectF screenBounds) {
                System.out.println("--ExitSharedElementCallback.onCaptureSharedElementSnapshot--");
                System.out.println("sharedElement is " + sharedElement);
//                System.out.println("viewToGlobalMatrix is " + viewToGlobalMatrix);
//                System.out.println("screenBounds is " + screenBounds);

//                System.out.println(sharedElement.getWidth() + ", " + sharedElement.getHeight() + "|" + sharedElement.getLeft() + ", " + sharedElement.getTop() + ", " + sharedElement.getRight() + ", " + sharedElement.getBottom());

//                Matrix matrix = new Matrix();
//                matrix.setRotate(80.6f);
//                viewToGlobalMatrix = matrix;
//                viewToGlobalMatrix.postConcat(matrix);



                Parcelable bitmap = super.onCaptureSharedElementSnapshot(sharedElement, viewToGlobalMatrix, screenBounds);
                System.out.println(bitmap);
//                return bitmap;
                return null;
            }

            @Override
            public void onRejectSharedElements(List<View> rejectedSharedElements) {
                System.out.println("--ExitSharedElementCallback.onRejectSharedElements--");
//                System.out.println("rejectedSharedElements is " + rejectedSharedElements);
//                rejectedSharedElements.add(findViewById(R.id.oneIV));
            }

            @Override
            public View onCreateSnapshotView(Context context, Parcelable snapshot) {
                System.out.println("--ExitSharedElementCallback.onCreateSnapshotView--");
                System.out.println("snapshot is " + snapshot);
                View view = super.onCreateSnapshotView(context, snapshot);
                System.out.println(view);
                return view;
            }

            @Override
            public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                System.out.println("--ExitSharedElementCallback.onSharedElementStart--");
                System.out.println("sharedElements is " + sharedElements);
                System.out.println("sharedElementSnapshots is " + sharedElementSnapshots);
                System.out.println("sharedElementNames is " + sharedElementNames);
            }

            @Override
            public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                System.out.println("--ExitSharedElementCallback.onSharedElementEnd--");
                System.out.println("sharedElements is " + sharedElements);
                System.out.println("sharedElementSnapshots is " + sharedElementSnapshots);
                System.out.println("sharedElementNames is " + sharedElementNames);
            }

            @Override
            public void onSharedElementsArrived(List<String> sharedElementNames, List<View> sharedElements, OnSharedElementsReadyListener listener) {
                System.out.println("--ExitSharedElementCallback.onSharedElementsArrived--");
//                System.out.println("sharedElementNames is " + sharedElementNames);
//                System.out.println("sharedElements is " + sharedElements);
//                System.out.println("listener is " + listener);

                super.onSharedElementsArrived(sharedElementNames, sharedElements, listener);
            }
        };


        //创建转换对象
        final Transition fade = new Fade().setDuration(duration)
                .addListener(new TransitionListenerAdapter() { //非共享元素钩子函数
                    @Override
                    public void onTransitionStart(Transition transition) {
                        System.out.println("~~src|Fade.onTransitionStart~~");
                    }

                    @Override
                    public void onTransitionEnd(Transition transition) {
                        System.out.println("~~src|Fade.onTransitionEnd~~");
                    }

                });
        Transition changesBounds = new ChangeBounds().setDuration(duration)
                .addListener(new TransitionListenerAdapter() { //非共享元素钩子函数
                    @Override
                    public void onTransitionStart(Transition transition) {
                        System.out.println("~~src|ChangesBounds.onTransitionStart~~");
                    }

                    @Override
                    public void onTransitionEnd(Transition transition) {
                        System.out.println("~~src|ChangesBounds.onTransitionEnd~~");
                    }

                });

        //设置共享组件转换对象
        window.setExitTransition(fade); //共享组件出场变换
        window.setSharedElementExitTransition(changesBounds); //共享组件出场变换
        window.setSharedElementReenterTransition(changesBounds.clone().setDuration(duration));  //（按Back后返回时，本Activity入场动画，测试失败了）
        window.setSharedElementsUseOverlay(false); //禁用遮罩层，让共享元素也能参与动画（Exit仅作用非遮罩层的View）

        setExitSharedElementCallback(sharedElementCallback);//绑定退出共享元素回调


        super.onCreate(bundle);
        setContentView(R.layout.activity_window_share);


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
    }

    public void start(View view) {
        System.out.println("********start******");

    }


    public void sharedStart(View view) {
        System.out.println("********sharedStart******");

        ImageView oneIV = findViewById(R.id.oneIV);
        ImageView twoIV = findViewById(R.id.twoIV);


        Intent intent = new Intent(this, WindowShareTransitionOneActivity.class);
//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, oneIV, "sharedOne");//单共享对象
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,//多共享对象
                Pair.<View, String>create(oneIV, "sharedOne"),
                Pair.<View, String>create(twoIV, "sharedTwo"));

        startActivity(intent, options.toBundle());


        //如果SharedElementExitTransition使用ChangeBounds就需要调整View的布局参数，否则不会有任何动画
        oneIV.setBottom(750);
        twoIV.setRight(350);
//        findViewById(R.id.button19).setTop(50);


    }

    public void sharedStop(View view) {
        System.out.println("********sharedStop******");
    }
}