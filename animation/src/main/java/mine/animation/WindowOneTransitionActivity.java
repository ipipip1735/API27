package mine.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionListenerAdapter;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.transition.TransitionValues;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import static android.transition.TransitionSet.ORDERING_SEQUENTIAL;
import static android.view.Gravity.LEFT;
import static android.view.Gravity.RIGHT;
import static android.view.Gravity.TOP;

/**
 * Created by Administrator on 2019/3/20.
 */
public class WindowOneTransitionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        setContentView(R.layout.activity_window_one);

        long duration = 5000L;

        Window window = getWindow();
//        window.requestFeature(Window.FEATURE_NO_TITLE);
//        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS); //启用变换


        //创建转换对象(4个，任选其一)
        Transition fade = new Fade().setDuration(duration);
        fade.addListener(new TransitionListenerAdapter() {
            @Override
            public void onTransitionStart(Transition transition) {
                System.out.println("~~Fade.onTransitionStart~~");
                System.out.println(transition.getTransitionValues(findViewById(R.id.imageViewShared), true));
                System.out.println("getDuration is " + transition.getDuration());
            }

            @Override
            public void onTransitionCancel(Transition transition) {
                System.out.println("~~Fade.onTransitionCancel~~");
                super.onTransitionCancel(transition);
            }

            @Override
            public void onTransitionPause(Transition transition) {
                System.out.println("~~Fade.onTransitionPause~~");
                super.onTransitionPause(transition);
            }

            @Override
            public void onTransitionResume(Transition transition) {
                System.out.println("~~Fade.onTransitionResume~~");
                super.onTransitionResume(transition);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                System.out.println("~~Fade.onTransitionEnd~~");
                System.out.println(transition.getTransitionValues(findViewById(R.id.imageViewShared), true));
                System.out.println("getDuration is " + transition.getDuration());
            }
        });
        Transition explode = new Explode().setDuration(duration);
        explode.addListener(new TransitionListenerAdapter() {
            @Override
            public void onTransitionStart(Transition transition) {
                System.out.println("~~Explode.onTransitionStart~~");
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                System.out.println("~~Explode.onTransitionEnd~~");
            }
        });
        Transition slide = new Slide(LEFT).setDuration(duration);
        slide.addListener(new TransitionListenerAdapter() {
            @Override
            public void onTransitionStart(Transition transition) {
                System.out.println("~~Slide.onTransitionStart~~");
                System.out.println("getDuration is " + transition.getDuration());
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                System.out.println("~~Slide.onTransitionEnd~~");
                System.out.println("getDuration is " + transition.getDuration());
            }
        });
        Transition changesBounds = new ChangeBounds().setDuration(duration);
        changesBounds.addListener(new TransitionListenerAdapter() {
            @Override
            public void onTransitionEnd(Transition transition) {
                System.out.println("~~ChangeBounds.onTransitionEnd~~");
            }

            @Override
            public void onTransitionStart(Transition transition) {
                System.out.println("~~ChangeBounds.onTransitionStart~~");
            }
        });


        //排除状态条和导航条
//        slide.excludeTarget(R.id.action_bar_container, true);
//        slide.excludeTarget(android.R.id.statusBarBackground, true);
//        slide.excludeTarget(android.R.id.navigationBarBackground, true);


        //设置转换对象
        window.setExitTransition(slide); //退出变换
//        window.setReenterTransition(fade); //重进入变换
//        window.setAllowReturnTransitionOverlap(false); //返回播放模式，false为顺序播放，默认值true为同时播放
//        window.setTransitionBackgroundFadeDuration(duration);//不按back键或finishAfterTransition()，而是在其他Activity中启动此Activity，才会应用本方法


        //设置共享组件转换对象（一般不会使用SharedElementExitTransition/SharedElementReenterTransition）
        window.setSharedElementExitTransition(fade); //共享组件退出变换
//        window.setSharedElementReenterTransition(changesBounds); //共享组件重进入变换
//        window.setSharedElementsUseOverlay(false); //共享转换禁用遮罩层




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

        Intent intent = new Intent(this, WindowTwoTransitionActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        startActivity(intent, options.toBundle());


        //打印变换信息
        Window window = getWindow();
        System.out.println(getClass().getSimpleName() + " is " + window);
        System.out.println("getEnterTransition is " + window.getEnterTransition());
        System.out.println("getExitTransition is " + window.getExitTransition());
        System.out.println("getReturnTransition is " + window.getReturnTransition());
        System.out.println("getReenterTransition is " + window.getReenterTransition());
        System.out.println("getSharedElementEnterTransition is " + window.getSharedElementEnterTransition());
        System.out.println("getSharedElementExitTransition is " + window.getSharedElementExitTransition());
        System.out.println("getSharedElementReturnTransition is " + window.getSharedElementReturnTransition());
        System.out.println("getSharedElementReenterTransition is " + window.getSharedElementReenterTransition());


    }

    public void sharedStart(View view) {
        System.out.println("********sharedStart******");

        ImageView imageView = findViewById(R.id.ivOne);
        ImageView imageViewST = findViewById(R.id.st);



        Intent intent = new Intent(this, WindowTwoTransitionActivity.class);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, imageView, "shared");//单共享对象
//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,//多共享对象
//                Pair.<View, String>create(imageView, "shared"),
//                Pair.<View, String>create(imageViewST, "sharedOne"));
        startActivity(intent, options.toBundle());


        //如果SharedElementExitTransition使用ChangeBounds就需要调整View的布局参数，否则不会有任何动画
//        imageView.setTop(450);
//        imageView.setLeft(150);
//        imageViewST.setTop(250);
//        imageViewST.setLeft(350);


        //如果SharedElementExitTransition使用Fade就需要修改父View的可见性，否则不会有任何动画
        imageView.setVisibility(View.INVISIBLE);
        imageViewST.setVisibility(View.INVISIBLE);





        //打印变换信息
        Window window = getWindow();
        System.out.println(getClass().getSimpleName() + " is " + window);
        System.out.println("getEnterTransition is " + window.getEnterTransition());
        System.out.println("getExitTransition is " + window.getExitTransition());
        System.out.println("getReturnTransition is " + window.getReturnTransition());
        System.out.println("getReenterTransition is " + window.getReenterTransition());
        System.out.println("getSharedElementEnterTransition is " + window.getSharedElementEnterTransition());
        System.out.println("getSharedElementExitTransition is " + window.getSharedElementExitTransition());
        System.out.println("getSharedElementReturnTransition is " + window.getSharedElementReturnTransition());
        System.out.println("getSharedElementReenterTransition is " + window.getSharedElementReenterTransition());

    }

    public void sharedStop(View view) {
        System.out.println("********sharedStop******");

        Intent intent = new Intent(this, WindowTwoTransitionActivity.class);

        ImageView imageView = findViewById(R.id.ivOne);
        imageView.setTransitionName("content");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, imageView, "shared");//单共享对象
        startActivity(intent, options.toBundle());
    }

}