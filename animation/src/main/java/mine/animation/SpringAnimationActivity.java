package mine.animation;


import android.os.Bundle;
import android.support.animation.DynamicAnimation;
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
public class SpringAnimationActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        System.out.println("*******  Example  on  Create  Menu!  *********");
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        System.out.println("*******  Example  on  Prepare  Menu!!!  *********");
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("**********  Example  onCreate  ***********");

        if (bundle == null) {
            System.out.println("Example bundle is empty");
        } else {
            System.out.println("bundle's total is " + bundle.size());
            Set<String> keySet = bundle.keySet();
            for (String i : keySet) {
                System.out.println(i);
            }
        }

        setContentView(R.layout.activity_spring_animation);


    }


    public void start(View view) {
        System.out.println("********start******");

//            spring();
        chainedSpringAnimation();
//            fling();

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

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
//        FlingAnimation flingAnim = new FlingAnimation(imageView, DynamicAnimation.TRANSLATION_X);
//        flingAnim.setStartVelocity(-2000);
//        flingAnim.setMinValue(-200);
//        flingAnim.setMaxValue(2000);
//        flingAnim.start();


    }


}