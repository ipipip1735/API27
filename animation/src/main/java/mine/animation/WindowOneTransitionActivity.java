package mine.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
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
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
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


        long duration = 2000L;

        Window window = getWindow();
//        window.requestFeature(Window.FEATURE_NO_TITLE);
//        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS); //启用变换


        //创建转换对象
        Transition fade = new Fade().setDuration(duration);
        fade.addListener(new TransitionListenerAdapter() {
            @Override
            public void onTransitionStart(Transition transition) {
                System.out.println("~~Fade.onTransitionStart~~");
                System.out.println(transition.getTransitionValues(findViewById(R.id.imageViewShared), true));
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                System.out.println("~~Fade.onTransitionEnd~~");
                System.out.println(transition.getTransitionValues(findViewById(R.id.imageViewShared), true));
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
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                System.out.println("~~Slide.onTransitionEnd~~");
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
        slide.excludeTarget(R.id.action_bar_container, true);
        slide.excludeTarget(android.R.id.statusBarBackground, true);
        slide.excludeTarget(android.R.id.navigationBarBackground, true);


        //设置转换对象
        window.setExitTransition(slide); //退出变换
        window.setReenterTransition(slide); //重进入变换
//        window.setAllowReturnTransitionOverlap(false); //返回播放模式，false为顺序播放，默认值true为同时播放
        window.setTransitionBackgroundFadeDuration(duration);


        //设置共享组件转换对象（一般不会使用SharedElementExitTransition/SharedElementReenterTransition）
//        window.setSharedElementExitTransition(fade);  //共享组件退出变换
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


    }

    public void sharedStart(View view) {
        System.out.println("********sharedStart******");

        ImageView imageView = findViewById(R.id.imageViewShared);

        Intent intent = new Intent(this, WindowTwoTransitionActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, imageView, "shared");
        startActivity(intent, options.toBundle());


        /**
         * 如果使用了SharedElementExitTransition动画，需要在启动Activity后修改组件属性
         */
//        imageView.setX(imageView.getX() - 150f);
//        imageView.setY(imageView.getY() - 450f);

//        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
//        layoutParams.width += 150f;
//        layoutParams.height += 150f;
//        imageView.setLayoutParams(layoutParams);
//


        //如果SharedElementExitTransition使用ChangeBounds就需要父View重新布局
//        View parent = (View) imageView.getParent();
//        int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY);
//        int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(), View.MeasureSpec.EXACTLY);
//        parent.measure(widthSpec, heightSpec);
//        parent.layout(parent.getLeft(), parent.getTop(), parent.getRight(), parent.getBottom());


        //如果SharedElementExitTransition使用Fade就需要修改父View的可见性
//        View parent = (View) imageView.getParent();
//        parent.setVisibility(View.INVISIBLE);

    }

    public void sharedStop(View view) {
        System.out.println("********sharedStop******");

    }


}