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
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import static android.view.Gravity.LEFT;
import static android.view.Gravity.TOP;

/**
 * Created by Administrator on 2019/3/20.
 */
public class WindowShareTransitionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle bundle) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        long duration = 5000L;


        Window window = getWindow();



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
        window.setSharedElementExitTransition(fade); //共享组件出场变换
//        window.setSharedElementReenterTransition(changesBounds);  //（按Back后返回时，本Activity入场动画，测试失败了）
//        window.setSharedElementsUseOverlay(false); //禁用遮罩层，让共享元素也能参与动画（Exit仅作用非遮罩层的View）


        super.onCreate(bundle);
        setContentView(R.layout.activity_window_share);

//        findViewById(R.id.siv).setVisibility(View.INVISIBLE);//设置共享元素可见性（fade需要可见性发生改变才能应用动画）
//        findViewById(R.id.button19).setVisibility(View.INVISIBLE);//设置非共享元素可见性

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

        ImageView imageView = findViewById(R.id.siv);


        Intent intent = new Intent(this, WindowShareTransitionOneActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, imageView, "shared");//单共享对象
        startActivity(intent, options.toBundle());


        //如果SharedElementExitTransition使用ChangeBounds就需要调整View的布局参数，否则不会有任何动画
//        imageView.setTop(450);
//        imageView.setLeft(150);



        //如果SharedElementExitTransition使用Fade就需要修改父View的可见性，否则不会有任何动画
        //方式一：修改可见性
//        imageView.setVisibility(View.VISIBLE);
//        findViewById(R.id.button19).setVisibility(View.VISIBLE);

        //方式二：fade动画也适用于增/删元素（因为增删元素也会改变可见性）
//        ViewGroup viewGroup = findViewById(R.id.linearLayout5);
//        viewGroup.removeAllViews();


    }

    public void sharedStop(View view) {
        System.out.println("********sharedStop******");
    }
}