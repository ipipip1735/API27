package mine.animation;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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

/**
 * Created by Administrator on 2019/3/20.
 */
public class WindowThreeTransitionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        setContentView(R.layout.activity_window_three);

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
//        window.setExitTransition(slide); //退出变换
//        window.setReenterTransition(fade); //重进入变换
//        window.setAllowReturnTransitionOverlap(false); //返回播放模式，false为顺序播放，默认值true为同时播放
//        window.setTransitionBackgroundFadeDuration(duration);


        //设置共享组件转换对象（一般不会使用SharedElementExitTransition/SharedElementReenterTransition）
//        window.setSharedElementExitTransition(changesBounds); //共享组件退出变换
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

        ImageView imageView = findViewById(R.id.ivTwo);

        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

        Intent intent = new Intent(this, WindowFourTransitionActivity.class);

//        Bundle options = ActivityOptions.makeScaleUpAnimation(imageView, 0,0, imageView.getWidth(), imageView.getHeight()).toBundle();
//        Bundle options = ActivityOptions.makeThumbnailScaleUpAnimation(imageView, bitmap, 0, 0).toBundle();
//        ActivityOptions options = ActivityOptions.makeClipRevealAnimation(imageView, 0, 0, imageView.getWidth(), imageView.getHeight());
        ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.animator.enter, R.animator.exit);


        startActivity(intent, options.toBundle());


    }

    public void sharedStart(View view) {
        System.out.println("********sharedStart******");
    }

    public void sharedStop(View view) {
        System.out.println("********sharedStop******");

    }


}