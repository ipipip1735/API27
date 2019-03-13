package mine.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
//import android.support.animation.DynamicAnimation;
//import android.support.animation.SpringAnimation;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout relativeLayout;
Button button0;
ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("**********  " + getClass().getSimpleName() + ".onCreate  **********");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fling_animation);
        relativeLayout = findViewById(R.id.rl);
        imageView = findViewById(R.id.imageView);

        for (int i = 0; i < 5; i++) {
            Button button = new Button(this);
            button.setText("" + Math.random());
            relativeLayout.addView(button);
        }
        button0 = new Button(this);
        relativeLayout.addView(button0, 3);

    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("**********  " + getClass().getSimpleName() + ".onResume  **********");

        LayoutTransition layoutTransition = new LayoutTransition();
        relativeLayout.setLayoutTransition(layoutTransition);


        PropertyValuesHolder pvhLeft =
                PropertyValuesHolder.ofInt("left", 0);
        PropertyValuesHolder pvhTop =
                PropertyValuesHolder.ofInt("top", 0);
        PropertyValuesHolder pvhRight =
                PropertyValuesHolder.ofInt("right", 0);
        PropertyValuesHolder pvhBottom =
                PropertyValuesHolder.ofFloat("bottom", 0);
        PropertyValuesHolder pvhScaleX =
                PropertyValuesHolder.ofFloat("scaleX", 0, 1.5f, 0);
        PropertyValuesHolder pvhScaleY =
                PropertyValuesHolder.ofFloat("scaleY", 1f, 1f);
        PropertyValuesHolder x =
                PropertyValuesHolder.ofFloat("X", 0, 150f, 0);

        ObjectAnimator changing = ObjectAnimator.ofPropertyValuesHolder(
                0, pvhTop,pvhBottom, x);
        ObjectAnimator changeAppearing = ObjectAnimator.ofPropertyValuesHolder(
                0, pvhTop,pvhBottom);
        ObjectAnimator appearing = ObjectAnimator.ofPropertyValuesHolder(
                0, x);

        //绑定监听器
//        changeIn.addListener(new Animator.AnimatorListener() {
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

//        layoutTransition.setAnimator(LayoutTransition.APPEARING, appearing);
        layoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, changeAppearing);

//        layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
//        layoutTransition.setAnimator(LayoutTransition.CHANGING, changeIn);
        layoutTransition.setDuration(5000l);

        //增加布局动画监听器
        layoutTransition.addTransitionListener(new LayoutTransition.TransitionListener() {
            @Override
            public void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                System.out.println("*-*-*  startTransition  *-*-*");
                System.out.println("transition  is " + transition);
                System.out.println("container  is " + container);
                System.out.println("tween  is " + view);
                System.out.println("transitionType  is " + transitionType);
            }

            @Override
            public void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                System.out.println("*-*-*  endTransition  *-*-*");
                System.out.println("transition  is " + transition);
                System.out.println("container  is " + container);
                System.out.println("tween  is " + view);
                System.out.println("transitionType  is " + transitionType);
            }
        });


    }


    public void start(View view) {
        System.out.println("********start******");
//        valueAnimator();
//        objectAnimator();
//        animatorSet();
//        values();
//        frame();
//        viewAnimator();
//        property();
//        keyFrameAnimator();
//        TypeEvalutors();
//        LayoutTransition();
        SpringAnimation();
    }

    private void SpringAnimation() {

        SpringAnimation springAnimation = new SpringAnimation(imageView, DynamicAnimation.TRANSLATION_Y);
        springAnimation.setMaxValue(1500f);
        springAnimation.setMinValue(0f);
        springAnimation.setStartValue(800f);
//        springAnimation.setStartVelocity(2f);
//        springAnimation.animateToFinalPosition(0f);

        SpringForce springForce = new SpringForce(0f);
        springAnimation.setSpring(springForce);

        springAnimation.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY)
                .setStiffness(SpringForce.STIFFNESS_VERY_LOW);

        springAnimation.start();

//        SpringAnimation anim = new SpringAnimation(imageView, DynamicAnimation.X, 0);
//        anim.setStartVelocity(5000);
//        anim.start();

    }

    public void stop(View view) {
        System.out.println("********stop******");
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout3);
//        linearLayout.removeViewAt(0);
//        animationDrawable.stop();
//        relativeLayout.setY(230f);
//        button0.setVisibility(View.GONE);
//        relativeLayout.requestLayout();

//        Drawable c = getResources().getDrawable(R.color.ALICEBLUE, null);
//        imageView.setBackgroundColor(R.color.ALICEBLUE);

        relativeLayout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
//        button0.setX(10f);
        button0.setLeft(120);
//        button0.setText("slsdlsdjl");
        relativeLayout.requestLayout();

    }


    private void LayoutTransition() {
        System.out.println("~~ LayoutTransition ~~");


        Button b = new Button(this);
        b.setText("*" + Math.random() + "*");
        relativeLayout.addView(b, 3);



    }


    private void viewAnimator() {
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.animate()
                .x(50f)
                .y(100f)
                .alpha(0.5f)
                .start();
    }


    private void valueAnimator() {
        System.out.println("***** values ******");

//        ValueAnimator va = ValueAnimator.ofInt(0,67);
        ValueAnimator va = ValueAnimator.ofInt(10, 67);
        va.setDuration(3000);
        va.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                System.out.println("input is " + input);
                return input;
            }
        });

        va.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                System.out.println("  >>> evaluate <<<");
                System.out.println(fraction);
                System.out.println(startValue);
                System.out.println(endValue);
                return fraction * 100;
            }
        });

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


    private void frame() {
        System.out.println("*****  frame  ******");
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setBackgroundResource(R.drawable.animation);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        if (animationDrawable.isRunning()) {
            System.out.println("is running!");
            animationDrawable.stop();
        }

        animationDrawable.start();
//        animationDrawable.start();


    }


    private void property() {
        System.out.println("***** values ******");


        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.tween);
        imageView.startAnimation(animation);

//        ImageView imageView = (ImageView) findViewById(R.id.imageView);
//
//        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.object);
//        set.setTarget(imageView);
//        set.start();


//        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.values);
//        ArrayList<Animator> list = set.getChildAnimations();
//
//        for (Animator animator : list) {
//            ValueAnimator valueAnimator = (ValueAnimator)animator;
//            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator animation) {
//                    System.out.println("value is " + animation.getAnimatedValue());
//                }
//            });
//        }
//        System.out.println(list.get(0));
//        set.start();

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

    private void animatorSet() {

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        ValueAnimator animator1 = ObjectAnimator.ofFloat(imageView, "alpha", 0.1f);
        ValueAnimator animator2 = ObjectAnimator.ofFloat(imageView, "x", 500f);
        ValueAnimator animator3 = ObjectAnimator.ofFloat(imageView, "y", 365f);
        ValueAnimator animator4 = ObjectAnimator.ofFloat(imageView, "x", 35f);

        animator1.setDuration(2000);
        animator2.setDuration(2000);
        animator3.setDuration(2000);
        animator4.setDuration(2000);

        AnimatorSet bouncer = new AnimatorSet();
        bouncer.playTogether(animator2, animator4);

//        bouncer.play(animator2).before(animator3);
//        bouncer.play(animator3).after(animator1);

        bouncer.start();

//        ValueAnimator fadeAnim = ObjectAnimator.ofFloat(newBall, "alpha", 1f, 0f);
//        fadeAnim.setDuration(250);
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.play(bouncer).before(fadeAnim);
//        animatorSet.start();
    }
}
