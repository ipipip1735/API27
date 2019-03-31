package mine.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.IntEvaluator;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.TypeConverter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Property;
import android.view.Menu;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewAnimator;

import java.util.Objects;
import java.util.Set;

import static android.graphics.Path.Direction.CCW;

/**
 * Created by Administrator on 2016/7/14.
 */
public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        setContentView(R.layout.activity_animation);
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

    public void show(View view) {
        System.out.println("********show******");
        ImageView imageView = findViewById(R.id.imageView1);
        System.out.println(imageView);

    }

    public void hide(View view) {
        System.out.println("********hide******");
        ImageView imageView = findViewById(R.id.imageView1);
        imageView.setVisibility(View.INVISIBLE);

    }


    public void start(View view) {
        System.out.println("********start******");


//        tween();
//        frame();
//        viewAnimator(); //视图属性动画

//        vectorAnimated();//矢量动画

//        ripple(); //涟漪动画

        postAnimotor();

    }

    private void postAnimotor() {

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        imageView.postOnAnimation(new Runnable() {
            @Override
            public void run() {
                System.out.println("run!!");
                System.out.println(Thread.currentThread());
                imageView.animate()
                        .x(50f)
                        .y(100f)
                        .alpha(0.5f)
                        .setDuration(3000)
                        .start();
            }
        });

    }


    private void ripple() {
        final View myView = findViewById(R.id.imageView1);

        if (myView.getVisibility() != View.VISIBLE) {

            //显示
            myView.setVisibility(View.VISIBLE);//设为可见

            //计算坐标
            int cx = myView.getWidth() / 2;
            int cy = myView.getHeight() / 2;

            float finalRadius = (float) Math.hypot(cx, cy);//计算半径

            //创建动画
            Animator animator = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0f, finalRadius);
            animator.setDuration(3000l).start();

        } else {

            //隐藏
            int cx = myView.getWidth() / 2;
            int cy = myView.getHeight() / 2;

            float initialRadius = (float) Math.hypot(cx, cy); //计算半径

            Animator animator = ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0f);
            animator.setDuration(3000l).start();

            //绑定监听器
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    myView.setVisibility(View.INVISIBLE);//设为不可见
                }
            });

        }


    }


    private void vectorAnimated() {

        ImageView imageView = findViewById(R.id.imageView1);
        AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) imageView.getDrawable();
        animatedVectorDrawable.start();

    }


    private void viewAnimator() {

        //简单使用
//        ImageView imageView = (ImageView) findViewById(R.id.imageView);
//        imageView.animate()
//                .x(50f)
//                .y(100f)
//                .alpha(0.5f)
//                .setDuration(3000)
//                .start();


        //绑定监听器
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.animate()
                .x(imageView.getX() - 50)
                .y(imageView.getX() - 40)
                .alpha(0)
                .setDuration(3000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        imageView.setVisibility(View.GONE);
                    }
                })
                .start();


    }


    private void tween() {

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.tween);
        animation.setDuration(3000l);
        imageView.startAnimation(animation);

    }


    private void frame() {
        System.out.println("*****  frame  ******");

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setBackgroundResource(R.drawable.frame);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();

        if (animationDrawable.isRunning()) {
            System.out.println("is running!");
            animationDrawable.stop();
        }
        animationDrawable.start();

    }

}