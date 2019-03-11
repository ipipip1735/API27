package mine.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.TypeConverter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Property;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import static android.graphics.Path.Direction.CCW;

/**
 * Created by Administrator on 2016/7/14.
 */
public class AnimatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        setContentView(R.layout.activity_animator);
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

//        valueAnimator(); //值动画
//        propertyAnimator(); //属性动画


//        objectAnimator(); //对象动画
//        animatorSet(); //对象动画集
//        keyFrameAnimator(); //属性关键帧

        pathObjectAnimator();//路径动画

    }

    private void pathObjectAnimator() {

        ImageView imageView = findViewById(R.id.imageView);

        Path path = new Path();
        path.addRect(100, 100, 500, 500, CCW); //创建矩形路径
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "x", "y", path);
        animator.setDuration(2000l).start(); //沿矩形路径动画

        //增加更新监听器
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                System.out.println("  >>> update <<<");
                float i = (float) animation.getAnimatedValue();
                System.out.println("getAnimatedValue is " + i);

            }
        });




    }

    public void stop(View view) {
        System.out.println("********stop******");
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout3);
//        linearLayout.removeViewAt(0);
//        animationDrawable.stop();


    }


    private void propertyAnimator() {
        System.out.println("***** property ******");


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
////        ObjectAnimator o = ObjectAnimator.ofPropertyValuesHolder(imageView, top); //单值动画
////        ObjectAnimator o = ObjectAnimator.ofPropertyValuesHolder(imageView, scalex); //单值动画
////        ObjectAnimator o = ObjectAnimator.ofPropertyValuesHolder(imageView, scrollY); //单值动画
//        ObjectAnimator o = ObjectAnimator.ofPropertyValuesHolder(imageView, scalex, scaley); //并行动画
//        o.setDuration(2000).start();
//
////        //绑定监听器，可以在start()后绑定
//        o.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                System.out.println("  >>> update <<<");
//                System.out.println(animation.getAnimatedFraction());
//                System.out.println(animation.getAnimatedValue());
//                System.out.println(animation.getDuration());
//            }
//        });


        //例三：使用求值器
        PropertyValuesHolder top = PropertyValuesHolder.ofInt("top", 250);

        ObjectAnimator o = ObjectAnimator.ofPropertyValuesHolder(imageView, top); //单值动画
        o.setEvaluator(new TypeEvaluator<Integer>() {
            @Override
            public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
                System.out.println("~~evaluate~~");
                System.out.println("fraction is " + fraction);
                System.out.println("startValue is " + startValue);
                System.out.println("endValue is " + endValue);
                return (int) (startValue + fraction * (endValue - startValue));
            }
        });
        o.setDuration(2000).start();

//        //绑定监听器，可以在start()后绑定
        o.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                System.out.println("  >>> update <<<");
                System.out.println(animation.getAnimatedFraction());
                System.out.println(animation.getAnimatedValue());
                System.out.println(animation.getDuration());
            }
        });





        //例四：属性为数组
//        Object o = new Object() {
//            float[] x;
//
//            public void setX(float[] x) {
//                this.x = x;
//            }
//        };
//        float[][] values = {{100f, 200f}, {300f, 400f}};
//        ObjectAnimator animator = ObjectAnimator.ofMultiFloat(o, "x", values);
//        animator.setDuration(1000l).start();
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                System.out.println("  >>> update <<<");
//                for (float f : (float[]) animation.getAnimatedValue()) {
//                    System.out.print(f + ",");
//                }
//                System.out.println("");
//
//            }
//        });


        //例五：属性为数组（使用转换器）
//        Object o = new Object() {
//            float[] x;
//            public void setX(float[] x) {
//                this.x = x;
//            }
//        };
//        int[][] values = {{100, 200}, {300, 400}};
//
//        TypeConverter<int[], float[]> typeConverter = new TypeConverter<int[], float[]>(int[].class, float[].class) {
//            @Override
//            public float[] convert(int[] value) {
//                float[] floats = new float[value.length];
//                for (int i = 0; i < value.length; i++) {
//                    floats[i] = (float) value[i];
//                }
//                return floats;
//            }
//        };
//
//        TypeEvaluator<int[]> typeEvalutor = new TypeEvaluator<int[]>() {
//            @Override
//            public int[] evaluate(float fraction, int[] startValue, int[] endValue) {
//                int[] result = new int[startValue.length];
//                for (int i = 0; i < startValue.length; i++) {
//                    int start = startValue[i];
//                    result[i] = (int) (start + fraction * (endValue[i] - startValue[i]));
//                }
//                return result;
//            }
//        };
//
//        ObjectAnimator animator = ObjectAnimator.ofMultiFloat(o, "x",
//                typeConverter, typeEvalutor, values);
//        animator.setDuration(1000l).start();
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                System.out.println("  >>> update <<<");
//                for (float f : (float[]) animation.getAnimatedValue()) {
//                    System.out.print(f + ",");
//                }
//                System.out.println("");
//
//            }
//        });


    }


    private void animatorSet() {

        ImageView imageView = (ImageView) findViewById(R.id.imageView);


        //方式一：XML方式
//        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.rotation); //读取XML创建对象
//        set.setTarget(imageView);
//        set.start();


        //方式二：代码方式
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

//        animator2.addListener(new AnimatorListenerAdapter() { //绑定监听器使用适配器对象更简洁
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//            }
//        });


        AnimatorSet bouncer = new AnimatorSet();
        bouncer.play(animator2).before(animator3);
        bouncer.play(animator3).after(animator1);
        bouncer.start();


        //方式三：动画集嵌套播放
//        ValueAnimator fadeAnim = ObjectAnimator.ofFloat(imageView, "alpha", 0.1f, 1f);
//        fadeAnim.setDuration(2500);
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.play(bouncer).before(fadeAnim); //嵌套播放
//        animatorSet.start();

    }


    private void keyFrameAnimator() {

        System.out.println("*****keyFrame*****");

        ImageView imageView = (ImageView) findViewById(R.id.imageView);


        //版本一：基本使用
//        //定义关键帧
//        Keyframe keyframe0 = Keyframe.ofFloat(0f);
//        Keyframe keyframe1 = Keyframe.ofFloat(0.1f, 600f);
//        Keyframe keyframe2 = Keyframe.ofFloat(1f, 100f);
//
//        //组合关键帧
//        PropertyValuesHolder pvhX = PropertyValuesHolder.ofKeyframe("x",
//                keyframe0, keyframe1, keyframe2);
//
//        //创建动画
//        ObjectAnimator anim = ObjectAnimator.ofPropertyValuesHolder(imageView, pvhX);
//
//        //绑定更新监听器
//        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                System.out.println("  >>> update <<<");
//                System.out.println(animation.getAnimatedFraction());
//                System.out.println(animation.getAnimatedValue());
//            }
//        });
//
//        //设置求值器
////        anim.setEvaluator(new TypeEvaluator() {
////            @Override
////            public Object evaluate(float fraction, Object startValue, Object endValue) {
////                System.out.println("  >>> evaluate <<<");
////                System.out.println(fraction);
////                System.out.println(startValue);
////                System.out.println(endValue);
////                return fraction*100;
////            }
////        });
//        anim.setDuration(5000).start();


        //版本二：使用时间因子修改器
        AccelerateInterpolator aInterpolator = new AccelerateInterpolator();
        DecelerateInterpolator dInterpolator = new DecelerateInterpolator();

        //关键帧1
        Keyframe keyframe0 = Keyframe.ofFloat(0f, 10f);
        keyframe0.setInterpolator(aInterpolator);//设置时间因子

        //关键帧2
        Keyframe keyframe1 = Keyframe.ofFloat(0.5f, 450f);
        keyframe1.setInterpolator(dInterpolator);

        //关键帧3
        Keyframe keyframe2 = Keyframe.ofFloat(1f, 50f);


        //组合关键帧
        PropertyValuesHolder x = PropertyValuesHolder.ofKeyframe("x", keyframe0, keyframe1, keyframe2);
        PropertyValuesHolder y = PropertyValuesHolder.ofKeyframe("y", keyframe0, keyframe1, keyframe2);

        //创建对象动画
        ObjectAnimator.ofPropertyValuesHolder(imageView, x, y)
                .setDuration(5000) //设置播放时长
                .start(); //开始动画


    }

}