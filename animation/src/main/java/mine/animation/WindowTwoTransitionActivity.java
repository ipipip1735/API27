package mine.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionListenerAdapter;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import static android.view.Gravity.LEFT;
import static android.view.Gravity.RIGHT;
import static android.view.Gravity.TOP;

/**
 * Created by Administrator on 2019/3/20.
 */
public class WindowTwoTransitionActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_window_two);

        long duration = 5000L;


        Window window = getWindow();
//        window.requestFeature(Window.FEATURE_NO_TITLE);
//        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS); //启用变换

        //创建转换对象
        Transition fade = new Fade().setDuration(duration);
        fade.addListener(new TransitionListenerAdapter() {
            @Override
            public void onTransitionStart(Transition transition) {
                System.out.println("~~Fade.onTransitionStart~~");
            }
            @Override
            public void onTransitionEnd(Transition transition) {
                System.out.println("~~Fade.onTransitionEnd~~");
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
        Transition slide = new Slide(TOP).setDuration(duration);
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



//        slide.excludeTarget(R.id.action_bar_container, true);
//        slide.excludeTarget(android.R.id.statusBarBackground, true);
//        slide.excludeTarget(android.R.id.navigationBarBackground, true);
//        explode.excludeTarget(R.id.action_bar_container, true);
//        explode.excludeTarget(android.R.id.statusBarBackground, true);
//        explode.excludeTarget(android.R.id.navigationBarBackground, true);



        //设置转换对象
//        window.setEnterTransition(slide); //进入变换
//        window.setReturnTransition(explode); //返回变换
//        window.setAllowEnterTransitionOverlap(false); //返回播放模式，false为顺序播放，默认值true为同时播放
        window.setTransitionBackgroundFadeDuration(duration);


        //设置共享组件转换对象
//        window.setSharedElementEnterTransition(changesBounds); //共享组件进入变换
//        window.setSharedElementReturnTransition(changesBounds);  //共享组件返回变换，优先级高于SharedElementEnterTransition
//        window.setSharedElementsUseOverlay(true); //共享组件转换禁用遮罩层





//        System.out.println(getClass().getSimpleName() + " is " + window);
//        System.out.println("getEnterTransition is "  + window.getEnterTransition());
//        System.out.println("getExitTransition is "  + window.getExitTransition());
//        System.out.println("getReturnTransition is "  + window.getReturnTransition());
//        System.out.println("getReenterTransition is "  + window.getReenterTransition());


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
        System.out.println("--------------");

        //打印变换信息
        Window window = getWindow();
        System.out.println(getClass().getSimpleName() + " is " + window);
        System.out.println("getEnterTransition is "  + window.getEnterTransition());
        System.out.println("getExitTransition is "  + window.getExitTransition());
        System.out.println("getReturnTransition is "  + window.getReturnTransition());
        System.out.println("getReenterTransition is "  + window.getReenterTransition());

    }

    public void start(View view) {
        System.out.println("********start******");

    }

}