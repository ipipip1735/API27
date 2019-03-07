package mine.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.IntEvaluator;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Property;
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


    public void start(View view) {
        System.out.println("********start******");
//        valueAnimator();
        objectAnimator();
//        animatorSet();
//        tween();
//        frame();
//        viewAnimator();
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
//        ImageView imageView = (ImageView) findViewById(R.id.imageView);
//        Animation hyperspaceJump = AnimationUtils.loadAnimation(this, R.anim.tween);
//        imageView.startAnimation(hyperspaceJump);
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


        //方式一：属性为公有
        Object o = new Object() {
            public int sk = 10;
        };
        Property property = Property.of(o.getClass(), int.class, "sk");

        System.out.println("getName is " + property.getName());
        System.out.println("getType is " + property.getType());
        System.out.println("isReadOnly is " + property.isReadOnly());

        System.out.println("get is " + property.get(o));
        property.set(o, 888);
        System.out.println("get is " + property.get(o));


        //方式二：属性为私有
//        Object o = new Object() {
//            private int sk = 10;
//
//            public int getSk() {
//                return sk;
//            }
//
//            public void setSk(int sk) {
//                this.sk = sk;
//            }
//        };
//
//        Property property = Property.of(o.getClass(), int.class, "sk");
//
//        System.out.println("getName is " + property.getName());
//        System.out.println("getType is " + property.getType());
//        System.out.println("isReadOnly is " + property.isReadOnly());
//
//        System.out.println("get is " + property.get(o));
//        property.set(o, 888);
//        System.out.println("get is " + property.get(o));

    }


    private void valueAnimator() {
        System.out.println("***** property ******");

//        ValueAnimator va = ValueAnimator.ofInt(20); //初值为0，终值为20
        ValueAnimator va = ValueAnimator.ofInt(200, 67); //初值为200，终值为67
//        ValueAnimator va = ValueAnimator.ofInt(10,11,25,8); //初值为10，终值为8，中值11,25
        va.setDuration(1000);

        //时间因子监听器
//        va.setInterpolator(new AccelerateInterpolator()); //加速时间因子
        va.setInterpolator(new TimeInterpolator() { //自定义时间因子
            @Override
            public float getInterpolation(float input) {
                System.out.println("  >>> interpolation <<<");

                System.out.println("input is " + input);
                return input;
            }
        });


        //计算中间值
//        va.setEvaluator(new IntEvaluator()); //int求值器
        va.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) { //自定义求值器
                System.out.println("  >>> evaluate <<<");
                System.out.println("fraction is " + fraction);
                System.out.println("startValue is " + startValue);
                System.out.println("endValue is " + endValue);
                return (int) startValue + fraction * ((int) endValue - (int) startValue);
            }
        });


        //更新监听器
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                System.out.println("  >>> update <<<");
                int i = (int) animation.getAnimatedValue();
                System.out.println("getAnimatedValue is " + i);

            }
        });

        va.start();


    }

    private void objectAnimator() {
        System.out.println("***animate****");


        ImageView imageView = (ImageView) findViewById(R.id.imageView);


        //例一：最简使用
//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView, "x", 250f);
//        animator1.setDuration(3000).start();
//        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView, "y", 250f);
//        animator2.setDuration(3000).start();//并行动画


        //例二：使用属性值持有器
//        PropertyValuesHolder top = PropertyValuesHolder.ofInt("top", 250);
//        PropertyValuesHolder scrollY = PropertyValuesHolder.ofInt("scrollY", 350);
//        PropertyValuesHolder scalex = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.6f, 2f);
//        PropertyValuesHolder scaley = PropertyValuesHolder.ofFloat("scaleY", 0f, 0.6f, 1f, 1.3f);
//
//        ObjectAnimator o = ObjectAnimator.ofPropertyValuesHolder(imageView, top); //单值动画
////        ObjectAnimator o = ObjectAnimator.ofPropertyValuesHolder(imageView, scalex); //单值动画
////        ObjectAnimator o = ObjectAnimator.ofPropertyValuesHolder(imageView, scrollY); //单值动画
////        ObjectAnimator o = ObjectAnimator.ofPropertyValuesHolder(imageView, scalex, scaley); //并行动画
//        o.setDuration(2000).start();
//
//
//        //绑定监听器，可以在start()后绑定
//        o.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                System.out.println("  >>> update <<<");
//                System.out.println(animation.getAnimatedFraction());
//                System.out.println(animation.getAnimatedValue());
//                System.out.println(animation.getDuration());
//            }
//        });




        //例三：路径动画
        Path path = new Path();
        path.addRect(0, 0, 50, 50, CCW); //创建矩形路径
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "x", "y", path);
        animator.setDuration(2000l).start(); //沿矩形路径动画




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

        //控制监听器
        animator2.addListener(new Animator.AnimatorListener() {
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
//        PropertyValuesHolder pvhLeft =
//                PropertyValuesHolder.ofInt("left", 0,0);
//        PropertyValuesHolder pvhTop =
//                PropertyValuesHolder.ofInt("top", 0, 0);
//        PropertyValuesHolder pvhRight =
//                PropertyValuesHolder.ofInt("right", 0, 0);
//        PropertyValuesHolder pvhBottom =
//                PropertyValuesHolder.ofInt("bottom", 0, 0);
//        PropertyValuesHolder pvhScaleX =
//                PropertyValuesHolder.ofFloat("scaleX", 1f,0f,1f);
//        PropertyValuesHolder pvhScaleY =
//                PropertyValuesHolder.ofFloat("scaleY", 1f, 1f);


//        ObjectAnimator x = ObjectAnimator.ofFloat(null, "x", 155f);
//        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
//                this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhScaleX);
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

//        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                System.out.println("change appearing");
//                System.out.println(animation.getAnimatedFraction());
//                System.out.println(animation.getAnimatedValue("top"));
//                System.out.println(animation.getAnimatedValue("left"));
//            }
//        });
//
//
//        Animator defaultA = layoutTransition.getAnimator(LayoutTransition.APPEARING);
//        Animator defaultCA = layoutTransition.getAnimator(LayoutTransition.CHANGE_APPEARING);
//
//        layoutTransition.setAnimator(LayoutTransition.APPEARING, x);
//        layoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, objectAnimator);
//        layoutTransition.setDuration(5000l);
//
//
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout3);
//        linearLayout.setLayoutTransition(layoutTransition);
//
//        Button b1 = new Button(this);
//
//        b1.setText("dddd" + Math.random());
//        linearLayout.addView(b1, 0);
//        b1.setVisibility(View.GONE);
    }


}