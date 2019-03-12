package mine.animation;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.animation.DynamicAnimation;
import android.support.animation.FlingAnimation;
import android.support.animation.FloatValueHolder;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;

import java.util.Objects;
import java.util.Set;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

/**
 * Created by Administrator on 2016/7/14.
 */
public class PhysicsAnimationActivity extends AppCompatActivity {

    VelocityTracker velocityTracker;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("**********  Example  onCreate  ***********");

        setContentView(R.layout.activity_spring_animation);


    }


    public void start(View view) {
        System.out.println("********start******");

//        fling();//惯性动画
//        velocityTracker(); //触碰点速度跟踪


//        spring();
        chainedSpringAnimation();


    }

    @SuppressLint("ClickableViewAccessibility")
    private void velocityTracker() {


        //方式一：速度跟踪器
//        ImageView imageView = findViewById(R.id.imageView1);
//        //绑定触碰事件
//        imageView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(final View v, MotionEvent event) {
//                System.out.println("event is " + event.actionToString(event.getAction()));
//
//
//                switch (event.getAction()) {
//                    case ACTION_MOVE:
//                        VelocityTracker velocityTracker = VelocityTracker.obtain();
//                        velocityTracker.addMovement(event);//增加事件
//                        velocityTracker.computeCurrentVelocity(1000);//计算速度
//                        float result = velocityTracker.getXVelocity(0);
//                        velocityTracker.recycle();//回收速度跟踪器
//                        break;
//
//                }
//                return true;
//            }
//        });



        //方式二：Fling动画配合速度跟踪器
        ImageView imageView = findViewById(R.id.imageView1);

        //绑定触碰事件
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                System.out.println("event is " + event.actionToString(event.getAction()));

                switch (event.getAction()) {
                    case ACTION_DOWN:
                        velocityTracker = VelocityTracker.obtain();//实例化速度跟踪器
                        break;
                    case ACTION_MOVE:
                        System.out.println(velocityTracker);
                        velocityTracker.addMovement(event);//增加事件
                        velocityTracker.computeCurrentVelocity(1000);//计算速度
                        break;
                    case ACTION_UP:
                        float result = velocityTracker.getXVelocity(0);
                        velocityTracker.recycle();//回收速度跟踪器
                        System.out.println("getXVelocity[0] is " + result);

                        new FlingAnimation(v, DynamicAnimation.TRANSLATION_X)
                        .setStartVelocity(result)//初速度
                        .setFriction(1f)
                        .setMinValue(0)
                        .setMaxValue(900)
                        .start();
                }
                return true;
            }
        });

    }

    public void stop(View view) {
        System.out.println("********stop******");


    }


    private void spring() {

        //方式一：基本使用
//        ImageView imageView = findViewById(R.id.imageView2);
//        SpringAnimation springAnimation = new SpringAnimation(imageView, DynamicAnimation.X, 50);
//        springAnimation.setStartVelocity(5000)
//        .addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() {
//            @Override
//            public void onAnimationUpdate(DynamicAnimation animation, float value, float velocity) {
//                System.out.println("~~onAnimationUpdate~~");
//                System.out.println("animation is " + animation);
//                System.out.println("value is " + value);
//                System.out.println("velocity is " + velocity);
//            }
//        }).start();




//       //方式二：自定义弹力
        ImageView imageView = findViewById(R.id.imageView2);
        SpringAnimation springAnimation = new SpringAnimation(imageView, DynamicAnimation.TRANSLATION_X);
        springAnimation.setStartValue(930f);
        springAnimation.setMinValue(0f);
        springAnimation.setMaxValue(1000f);

        //设置弹力
        SpringForce force = new SpringForce(450);
        force.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);//设置阻力系数，可理解为反弹等级
        force.setStiffness(SpringForce.STIFFNESS_VERY_LOW);//设置刚度,可理解为动画播放速度
        springAnimation.setSpring(force);

        //更新监听器
        springAnimation.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() {
            @Override
            public void onAnimationUpdate(DynamicAnimation animation, float value, float velocity) {
                System.out.println(" --->> onAnimationUpdate <<---");
                System.out.println("animation is " + animation);
                System.out.println("value is " + value);
                System.out.println("velocity is " + velocity);
            }
        });

        //增加结束监听器
        springAnimation.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                System.out.println(" --->> onAnimationEnd <<---");
                System.out.println("animation is " + animation);
                System.out.println("canceled is " + canceled);
                System.out.println("canceled is " + value);
                System.out.println("velocity is " + velocity);
            }
        });

        springAnimation.start();
//        springAnimation.animateToFinalPosition(300);//修改构造函数中指定的最终位置

    }


    private void chainedSpringAnimation() {

        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.linearLayout3);

        viewGroup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ImageView imageView1 = findViewById(R.id.imageView1);
                imageView1.setX(event.getX());
                imageView1.setY(event.getY());

                ImageView imageView2 = findViewById(R.id.imageView2);
                SpringAnimation springAnimationX = new SpringAnimation(imageView2, DynamicAnimation.TRANSLATION_X, 0);
                SpringAnimation springAnimationY = new SpringAnimation(imageView2, DynamicAnimation.TRANSLATION_Y, 0);
                springAnimationX.animateToFinalPosition(event.getX());
                springAnimationY.animateToFinalPosition(event.getY());

                return true;
            }
        });


    }


    private void fling() {

        //方式一：最简使用
//        final ImageView imageView = findViewById(R.id.imageView1);
//        FlingAnimation flingAnim = new FlingAnimation(imageView, DynamicAnimation.TRANSLATION_X);
//        flingAnim.setStartVelocity(2000);//初速度
//        flingAnim.setFriction(1f);//设置衰减因子，可选，默认值为1
//        flingAnim.setMinValue(0);//属性最小值
//        flingAnim.setMaxValue(4000);//属性最大值
//
//        //更新监听器
//        flingAnim.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() {
//            @Override
//            public void onAnimationUpdate(DynamicAnimation animation, float value, float velocity) {
//                System.out.println("~~onAnimationUpdate~~");
//                System.out.println("animation is " + animation);
//                System.out.println("value is " + value);
//                System.out.println("velocity is " + velocity);
//                System.out.println("getTranslationX is " + imageView.getTranslationX());
//            }
//        });
//
//        //结束监听器
//        flingAnim.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
//            @Override
//            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
//                System.out.println("~~onAnimationEnd~~");
//                System.out.println("animation is " + animation);
//                System.out.println("canceled is " + canceled);
//                System.out.println("value is " + value);
//                System.out.println("velocity is " + velocity);
//            }
//        });
//
//        flingAnim.start(); //开始动画


        //方法二：自定义对象
        final ImageView imageView = findViewById(R.id.imageView1);

        FloatValueHolder floatValueHolder = new FloatValueHolder(1000f);
        FlingAnimation flingAnim = new FlingAnimation(floatValueHolder);
        flingAnim.setStartVelocity(2000);//初速度
        flingAnim.setFriction(1f);//设置衰减因子，可选，默认值为1
        flingAnim.setMinValue(0);//属性最小值
        flingAnim.setMaxValue(4000);//属性最大值


        //更新监听器
        flingAnim.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() {
            @Override
            public void onAnimationUpdate(DynamicAnimation animation, float value, float velocity) {
                System.out.println("~~onAnimationUpdate~~");
                System.out.println("animation is " + animation);
                System.out.println("value is " + value);
                System.out.println("velocity is " + velocity);

                imageView.setTranslationX(value);
            }
        });

        flingAnim.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                System.out.println("~~onAnimationEnd~~");
                System.out.println("animation is " + animation);
                System.out.println("canceled is " + canceled);
                System.out.println("value is " + value);
                System.out.println("velocity is " + velocity);
            }
        });

        flingAnim.start(); //开始动画

    }


}