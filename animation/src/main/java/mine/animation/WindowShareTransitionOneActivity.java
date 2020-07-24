package mine.animation;

import android.app.ActivityOptions;
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
import android.widget.Button;
import android.widget.ImageView;

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
        Transition changesBounds = new ChangeBounds()
//                .addTarget(android.R.id.content)
                .setDuration(duration)
                .addListener(new TransitionListenerAdapter() {
                    @Override
                    public void onTransitionEnd(Transition transition) {
                        System.out.println("~~ChangeBounds.onTransitionEnd~~");
                    }

                    @Override
                    public void onTransitionStart(Transition transition) {
                        System.out.println("~~ChangeBounds.onTransitionStart~~");
                    }
                });




        //设置共享组件转换对象
        window.setSharedElementEnterTransition(changesBounds); //共享组件进入变换
        window.setSharedElementReturnTransition(changesBounds);  //共享组件返回变换，优先级高于SharedElementEnterTransition
        window.setTransitionBackgroundFadeDuration(1000L);



        super.onCreate(bundle);
        setContentView(R.layout.activity_window_share_one);
//        findViewById(R.id.siv).setTransitionName("shared");
        findViewById(android.R.id.content).setTransitionName("content");

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