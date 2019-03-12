package mine.animation;


import android.os.Bundle;
import android.support.animation.DynamicAnimation;
import android.support.animation.FlingAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Set;

/**
 * Created by Administrator on 2016/7/14.
 */
public class PhysicsAnimationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("**********  Example  onCreate  ***********");

        setContentView(R.layout.activity_spring_animation);


    }


    public void start(View view) {
        System.out.println("********start******");

            fling();//惯性动画
//            spring();
//        chainedSpringAnimation();

    }

    public void stop(View view) {
        System.out.println("********stop******");


    }


    private void spring() {

        ImageView imageView = (ImageView) findViewById(R.id.imageView1);
        SpringAnimation springAnimation = new SpringAnimation(imageView, DynamicAnimation.TRANSLATION_Y, 0);

        springAnimation.setMinValue(0f);
        springAnimation.setMaxValue(1000f);
        springAnimation.setStartValue(230f);

        SpringForce force = new SpringForce();
        force.setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);
        force.setStiffness(SpringForce.STIFFNESS_LOW);
        force.setFinalPosition(0f);
        springAnimation.setSpring(force);

        springAnimation.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() {
            @Override
            public void onAnimationUpdate(DynamicAnimation animation, float value, float velocity) {
                System.out.println(" --->> onAnimationUpdate <<---");
                System.out.println("animation is " + animation);
                System.out.println("value is " + value);
                System.out.println("velocity is " + velocity);
            }
        });

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
//        springAnimation.animateToFinalPosition(100);

    }


    private void chainedSpringAnimation() {

        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.linearLayout3);

        viewGroup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
                imageView1.setX(event.getX());
                imageView1.setY(event.getY());

                ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
                SpringAnimation springAnimationX = new SpringAnimation(imageView2, DynamicAnimation.TRANSLATION_X, 0);
                SpringAnimation springAnimationY = new SpringAnimation(imageView2, DynamicAnimation.TRANSLATION_Y, 0);
                springAnimationX.animateToFinalPosition(event.getX());
                springAnimationY.animateToFinalPosition(event.getY());

                return true;
            }
        });


    }


    private void fling() {

        ImageView imageView = findViewById(R.id.imageView1);
        FlingAnimation flingAnim = new FlingAnimation(imageView, DynamicAnimation.TRANSLATION_X);
        flingAnim.setStartVelocity(2000);//初速度
        flingAnim.setFriction(1f);//设置衰减因子，可选，默认值为1
        flingAnim.setMinValue(0);//属性最小值
        flingAnim.setMaxValue(4000);//属性最大值
        flingAnim.start();

    }


}