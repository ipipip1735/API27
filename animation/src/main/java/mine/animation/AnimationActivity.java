package mine.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
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

import java.util.Set;

/**
 * Created by Administrator on 2016/7/14.
 */
public class AnimationActivity extends AppCompatActivity {

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

        setContentView(R.layout.activity_animation);


    }

    @Override
    protected void onNewIntent(Intent intent) {
        System.out.println("****  Example  onNewIntent  *****");
        super.onNewIntent(intent);
    }


    @Override
    protected void onStart() {
        System.out.println("****Example**onStart*****");
        super.onStart();

    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("****  Example  onRestoreInstanceState  *****");
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    protected void onRestart() {
        System.out.println("****Example**onRestart*****");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        System.out.println("****Example**onResume*****");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("****Example**onPause*****");
        super.onPause();
    }


    @Override
    public void onBackPressed() {
        System.out.println("********Example***onBackPressed**********");
        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("****Example**onStop*****");
        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("****  Example   onSaveInstanceState  *****");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("****Example**onDestroy*****");
        super.onDestroy();
    }


    public void start(View view) {
        System.out.println("********start******");
//        valueAnimator();
//        objectAnimator();
//        animatorSet();
//        tween();
//        frame();
        viewAnimator();
//        property();
//        keyFrameAnimator();
//        TypeEvalutors();
//        LayoutTransition();
    }

    public void stop(View view) {
        System.out.println("********stop******");
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout3);
//        linearLayout.removeViewAt(0);
//        animationDrawable.stop();


    }


    private void tween() {
//        imageView.setImageResource(R.drawable.cd);
//        ImageView image = (ImageView) findViewById(R.id.image);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Animation hyperspaceJump = AnimationUtils.loadAnimation(this, R.anim.tween);
        imageView.startAnimation(hyperspaceJump);
    }


    private void frame() {
        System.out.println("*****  frame  ******");
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setBackgroundResource(R.drawable.frame);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        if (animationDrawable.isRunning()) {
            System.out.println("is running!");
            animationDrawable.stop();
        }

        animationDrawable.start();
        animationDrawable.start();


    }

    private void property() {
        System.out.println("***** property ******");

        ImageView imageView = (ImageView) findViewById(R.id.imageView);


//        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.property);
//        set.setTarget(imageView);
//        set.start();

    }


    private void valueAnimator() {
        System.out.println("***** property ******");

//        ValueAnimator va = ValueAnimator.ofInt(0,67);
        ValueAnimator va = ValueAnimator.ofInt(10, 67, 25);
        va.setDuration(3000);
        va.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                System.out.println("input is " + input);
                return input;
            }
        });

//        va.setEvaluator(new TypeEvaluator() {
//            @Override
//            public Object evaluate(float fraction, Object startValue, Object endValue) {
//                System.out.println("  >>> evaluate <<<");
//                System.out.println(fraction);
//                System.out.println(startValue);
//                System.out.println(endValue);
//                return fraction*100;
//            }
//        });

        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                System.out.println("  >>> update <<<");
                int i = (int) animation.getAnimatedValue();
                System.out.println(i);

            }
        });

        va.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                System.out.println("******onAnimationStart*****");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                System.out.println("******onAnimationEnd*****");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                System.out.println("******onAnimationCancel*****");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

                System.out.println("******onAnimationRepeat*****");
            }
        });
        va.start();


    }

    private void objectAnimator() {
        System.out.println("***animate****");

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

//        PropertyValuesHolder top = PropertyValuesHolder.ofInt("top", 250);
//        PropertyValuesHolder s = PropertyValuesHolder.ofInt("scrollY", 350);

        PropertyValuesHolder scalex = PropertyValuesHolder.ofFloat("scaleX", 1f,1.6f,2f);
        PropertyValuesHolder scaley = PropertyValuesHolder.ofFloat("scaleY", 0f,0.6f,1f,1.3f);



        ObjectAnimator o = ObjectAnimator.ofPropertyValuesHolder(imageView, scalex).setDuration(2000);
//        ObjectAnimator o = ObjectAnimator.ofPropertyValuesHolder(imageView, scaley).setDuration(2000);
//        ObjectAnimator o = ObjectAnimator.ofPropertyValuesHolder(imageView, scalex, scaley).setDuration(2000);
        o.start();

        o.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                System.out.println("  >>> update <<<");
                System.out.println(animation.getAnimatedFraction());
                System.out.println(animation.getAnimatedValue());
            }
        });
//        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "x", 250f);
//        animator.setDuration(3000).start();


    }


    private void animatorSet() {

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        ValueAnimator animator1 = ObjectAnimator.ofFloat(imageView, "alpha", 0.1f);
        ValueAnimator animator2 = ObjectAnimator.ofFloat(imageView, "x", 500f);
        ValueAnimator animator3 = ObjectAnimator.ofFloat(imageView, "y", 365f);
        ValueAnimator animator4 = ObjectAnimator.ofFloat(imageView, "x", 365f);

        animator1.setDuration(2000);
        animator2.setDuration(2000);
        animator3.setDuration(2000);
        animator4.setDuration(2000);

        AnimatorSet bouncer = new AnimatorSet();

        bouncer.play(animator2).before(animator3);
        bouncer.play(animator3).after(animator1);

        bouncer.start();

//        ValueAnimator fadeAnim = ObjectAnimator.ofFloat(newBall, "alpha", 1f, 0f);
//        fadeAnim.setDuration(250);
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.play(bouncer).before(fadeAnim);
//        animatorSet.start();
    }


    private void viewAnimator() {
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.animate()
                .x(50f)
                .y(100f)
                .alpha(0.5f)
                .start();
    }


    private void keyFrameAnimator() {

        System.out.println("*****keyFrame*****");

        ImageView imageView = (ImageView) findViewById(R.id.imageView);


        Keyframe keyframe0 = Keyframe.ofFloat(0f);
        Keyframe keyframe1 = Keyframe.ofFloat(0.1f, 600f);
        Keyframe keyframe2 = Keyframe.ofFloat(1f, 100f);

        PropertyValuesHolder pvhX = PropertyValuesHolder.ofKeyframe("x", keyframe0, keyframe1, keyframe2);


        ObjectAnimator anim = ObjectAnimator.ofPropertyValuesHolder(imageView, pvhX);

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                System.out.println("  >>> update <<<");
                System.out.println(animation.getAnimatedFraction());
                System.out.println(animation.getAnimatedValue());
            }
        });
//        anim.setEvaluator(new TypeEvaluator() {
//            @Override
//            public Object evaluate(float fraction, Object startValue, Object endValue) {
//                System.out.println("  >>> evaluate <<<");
//                System.out.println(fraction);
//                System.out.println(startValue);
//                System.out.println(endValue);
//                return fraction*100;
//            }
//        });
        anim.setDuration(5000);
        anim.start();


//        AccelerateInterpolator aInterpolator = new AccelerateInterpolator();
//        DecelerateInterpolator dInterpolator = new DecelerateInterpolator();
//        Keyframe keyframe0 = Keyframe.ofFloat(0f, 10f);
//        keyframe0.setInterpolator(aInterpolator);
//        Keyframe keyframe1 = Keyframe.ofFloat(0.5f, 450f);
//        keyframe1.setInterpolator(dInterpolator);
//        Keyframe keyframe4 = Keyframe.ofFloat(1f, 50f);
//
//        PropertyValuesHolder x = PropertyValuesHolder.ofKeyframe("x", keyframe0, keyframe1, keyframe4);
//        PropertyValuesHolder y = PropertyValuesHolder.ofKeyframe("y", keyframe0, keyframe1, keyframe4);
//        ObjectAnimator.ofPropertyValuesHolder(imageView, x, y).setDuration(5000).start();


    }


    private void TypeEvalutors() {

        System.out.println("*****TypeEvalutors*****");

        ImageView imageView = (ImageView) findViewById(R.id.imageView);


        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0.1f, 1f);
        objectAnimator.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                System.out.println("***********  fraction is " + fraction + "***********");
                System.out.println("***********  startValue is " + startValue + "***********");
                System.out.println("***********  endValue is " + endValue + "***********");
                return 0.8f;
            }
        });
        objectAnimator.start();


    }


    private void LayoutTransition() {


        LayoutTransition layoutTransition = new LayoutTransition();


//        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left", 0, 1);
        PropertyValuesHolder pvhLeft =
                PropertyValuesHolder.ofInt("left", 0,0);
        PropertyValuesHolder pvhTop =
                PropertyValuesHolder.ofInt("top", 0, 0);
        PropertyValuesHolder pvhRight =
                PropertyValuesHolder.ofInt("right", 0, 0);
        PropertyValuesHolder pvhBottom =
                PropertyValuesHolder.ofInt("bottom", 0, 0);
        PropertyValuesHolder pvhScaleX =
                PropertyValuesHolder.ofFloat("scaleX", 1f,0f,1f);
        PropertyValuesHolder pvhScaleY =
                PropertyValuesHolder.ofFloat("scaleY", 1f, 1f);


        ObjectAnimator x = ObjectAnimator.ofFloat(null, "x", 155f);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhScaleX);
//                this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhScaleX, pvhScaleY);


//        objectAnimator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                System.out.println("-------onAnimationStart*****");
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                System.out.println("-------onAnimationEnd*****");
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//                System.out.println("-------onAnimationCancel*****");
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//                System.out.println("-------onAnimationRepeat*****");
//            }
//        });

        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                System.out.println("change appearing");
                System.out.println(animation.getAnimatedFraction());
                System.out.println(animation.getAnimatedValue("top"));
                System.out.println(animation.getAnimatedValue("left"));
            }
        });


        Animator defaultA = layoutTransition.getAnimator(LayoutTransition.APPEARING);
        Animator defaultCA = layoutTransition.getAnimator(LayoutTransition.CHANGE_APPEARING);

        layoutTransition.setAnimator(LayoutTransition.APPEARING, x);
        layoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, objectAnimator);
        layoutTransition.setDuration(5000l);


        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout3);
        linearLayout.setLayoutTransition(layoutTransition);

        Button b1 = new Button(this);

        b1.setText("dddd" + Math.random());
        linearLayout.addView(b1, 0);
//        b1.setVisibility(View.GONE);
    }


}