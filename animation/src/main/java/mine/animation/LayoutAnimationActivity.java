package mine.animation;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2019/3/9.
 */
public class LayoutAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        setContentView(R.layout.activity_layout_animation);
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



    public void del(View view) {
        System.out.println("********del******");
        disappearing();
//        changeDisappearing();


    }

    private void disappearing() {
        //布局属性动画
        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofFloat("top", 0, 250f, 0);//首尾值将被系统替换
        PropertyValuesHolder pvhBottom = PropertyValuesHolder.ofFloat("bottom", 0, 100f, 0);//首尾值将被系统替换
        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofFloat("left", 0, 100f, 0);//首尾值将被系统替换

        //非布局属性动画
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x", 0, 100f);//首尾值将被系统替换
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", 0, 100f);//首尾值将被系统替换
        PropertyValuesHolder pvhAlpha = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);//首尾值将被系统替换

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(0,
//                pvhY); //起作用
//                pvhX); //起作用
                pvhAlpha); //起作用
//                pvhLeft); //不起作用
//                pvhTop); //不起作用
//                pvhBottom); //不起作用


        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                System.out.println("-------onAnimationStart*****");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                System.out.println("-------onAnimationEnd*****");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                System.out.println("-------onAnimationCancel*****");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

                System.out.println("-------onAnimationRepeat*****");
            }
        });

        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                System.out.println("change appearing");
                System.out.println("Fraction is " + animation.getAnimatedFraction());
                System.out.println("x is " + animation.getAnimatedValue("x"));
                System.out.println("left is " + animation.getAnimatedValue("left"));
                System.out.println("top is " + animation.getAnimatedValue("top"));
                System.out.println("right is " + animation.getAnimatedValue("right"));
                System.out.println("bottom is " + animation.getAnimatedValue("bottom"));
                System.out.println("scaleX is " + animation.getAnimatedValue("scaleX"));
                System.out.println("scaleY is " + animation.getAnimatedValue("scaleY"));
            }
        });

        //创建布局转换对象并绑定动画
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.setAnimator(LayoutTransition.DISAPPEARING, objectAnimator);
        layoutTransition.setDuration(5000l);


        LinearLayout linearLayout = findViewById(R.id.ll);
        linearLayout.setLayoutTransition(layoutTransition);

        linearLayout.removeViewAt(0);

    }


    private void changeDisappearing() {
        //主属性动画
        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top", 0,100,0);//首尾值将被系统替换
        PropertyValuesHolder pvhRight = PropertyValuesHolder.ofInt("right", 0, 0);
//        PropertyValuesHolder pvhBottom = PropertyValuesHolder.ofInt("bottom", 0, 0);
//        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f);
//        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 1f);


        //非布局属性动画
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x", 0, 100f, 0);//首尾值将被系统替换
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", 0, 100f, 0);//首尾值将被系统替换
        PropertyValuesHolder pvhAlpha = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);//首尾值将被系统替换

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(0,
//                pvhTop);//起作用
//                pvhRight);//不起作用
//                pvhY);//起作用
//                pvhX);//不起作用
                pvhAlpha);//不起作用


        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                System.out.println("-------onAnimationStart*****");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                System.out.println("-------onAnimationEnd*****");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                System.out.println("-------onAnimationCancel*****");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

                System.out.println("-------onAnimationRepeat*****");
            }
        });

        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                System.out.println("change appearing");
                System.out.println("Fraction is " + animation.getAnimatedFraction());
                System.out.println("x is " + animation.getAnimatedValue("x"));
                System.out.println("left is " + animation.getAnimatedValue("left"));
                System.out.println("top is " + animation.getAnimatedValue("top"));
                System.out.println("right is " + animation.getAnimatedValue("right"));
                System.out.println("bottom is " + animation.getAnimatedValue("bottom"));
                System.out.println("scaleX is " + animation.getAnimatedValue("scaleX"));
                System.out.println("scaleY is " + animation.getAnimatedValue("scaleY"));
            }
        });


        //获取默认动画（可以修改默认动画）
//        Animator defaultA = layoutTransition.getAnimator(LayoutTransition.APPEARING);
//        Animator defaultCA = layoutTransition.getAnimator(LayoutTransition.CHANGE_APPEARING);


        //创建布局转换对象并绑定动画
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, objectAnimator);
        layoutTransition.setDuration(5000l);


        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll);
        linearLayout.setLayoutTransition(layoutTransition);

//        linearLayout.removeViewAt(0);
        linearLayout.getChildAt(0).setVisibility(View.GONE);
    }

    public void add(View view) {
        System.out.println("********add******");
//        appearing();
        changeAppearing();
//        layoutTransition();

    }

    private void changeAppearing() {

        //主属性动画
        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top", 0,100,0);//首尾值将被系统替换
        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left", 0,100,0);//首尾值将被系统替换
        PropertyValuesHolder pvhRight = PropertyValuesHolder.ofInt("right", 0, 0);
        PropertyValuesHolder pvhBottom = PropertyValuesHolder.ofInt("bottom", 0, 0);
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f);
        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 1f);

        //非布局属性动画
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x", 0, 100f, 0);//首尾值将被系统替换
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", 0, 100f, 0);//首尾值将被系统替换
        PropertyValuesHolder pvhAlpha = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);//首尾值将被系统替换

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(0,
//                pvhX);//不起作用
//                pvhLeft);//不起作用
                pvhAlpha);//不起作用
//                pvhY);//起作用
//                pvhTop);//起作用
//                pvhBottom);//起作用


        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                System.out.println("-------onAnimationStart*****");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                System.out.println("-------onAnimationEnd*****");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                System.out.println("-------onAnimationCancel*****");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

                System.out.println("-------onAnimationRepeat*****");
            }
        });

        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                System.out.println("change appearing");
                System.out.println("Fraction is " + animation.getAnimatedFraction());
                System.out.println("x is " + animation.getAnimatedValue("x"));
                System.out.println("left is " + animation.getAnimatedValue("left"));
                System.out.println("top is " + animation.getAnimatedValue("top"));
                System.out.println("right is " + animation.getAnimatedValue("right"));
                System.out.println("bottom is " + animation.getAnimatedValue("bottom"));
                System.out.println("scaleX is " + animation.getAnimatedValue("scaleX"));
                System.out.println("scaleY is " + animation.getAnimatedValue("scaleY"));
            }
        });


        //获取默认动画（可以修改默认动画）
//        Animator defaultA = layoutTransition.getAnimator(LayoutTransition.APPEARING);
//        Animator defaultCA = layoutTransition.getAnimator(LayoutTransition.CHANGE_APPEARING);


        //创建布局转换对象并绑定动画
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, objectAnimator);
        layoutTransition.setDuration(5000l);


        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll);
        linearLayout.setLayoutTransition(layoutTransition);

        Button b1 = new Button(this);
        b1.setText("dddd" + Math.random());
        linearLayout.addView(b1, 0);

//        b1.setVisibility(View.GONE);
    }






    private void appearing() {


        //定义动画
        //方式一
//        ObjectAnimator xOA = ObjectAnimator.ofFloat(null, "x", 155f, 0f);


        //方式二：使用属性持有器，与方式一等价
        PropertyValuesHolder top = PropertyValuesHolder.ofFloat("top", -145f, 0);
        PropertyValuesHolder left = PropertyValuesHolder.ofFloat("left", -140f, 0f);
        PropertyValuesHolder x = PropertyValuesHolder.ofFloat("x", -145f, 0);
        PropertyValuesHolder y = PropertyValuesHolder.ofFloat("y", -145f, 0);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha",  0, 1f);
        ObjectAnimator xOA = ObjectAnimator.ofPropertyValuesHolder(0, //target随便指定，后面系统架构自动设置为ViewGroup的child
//                x);//起作用
//                y);//起作用
                alpha);//起作用
//                top);//不起作用
//                left);//不起作用


        //绑定监听器
        xOA.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                System.out.println("-------onAnimationStart*****");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                System.out.println("-------onAnimationEnd*****");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                System.out.println("-------onAnimationCancel*****");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

                System.out.println("-------onAnimationRepeat*****");
            }
        });

        //绑定更新监听器
        xOA.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                System.out.println("change appearing");
                System.out.println(animation.getAnimatedFraction());
                System.out.println(animation.getAnimatedValue("x"));
            }
        });




        //创建布局转换对象
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.setAnimator(LayoutTransition.APPEARING, xOA);
        layoutTransition.setDuration(5000l);


        LinearLayout linearLayout = findViewById(R.id.ll);
        linearLayout.setLayoutTransition(layoutTransition); //绑定布局转换对象
        Button b1 = new Button(this);//增加子View
        b1.setText("dddd" + Math.random());
        linearLayout.addView(b1, 0);


    }


    private void layoutTransition() {


//        APPEARING + CHANGEAPPEARING动画

        //主属性动画
        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left", 0, 630, 0);
        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top", 0, 163, 0);
        PropertyValuesHolder pvhRight = PropertyValuesHolder.ofInt("right", 0, 0);
        PropertyValuesHolder pvhBottom = PropertyValuesHolder.ofInt("bottom", 0, 0);
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f);
        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 1f);

        //非布局属性动画
        ObjectAnimator x = ObjectAnimator.ofFloat(null, "x", 155f);
        ObjectAnimator top = ObjectAnimator.ofInt(null, "top", 0, 155, 0);


        //创建动画对象
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(0,
                pvhLeft, pvhTop, pvhRight, pvhBottom, pvhScaleX, pvhScaleY);


        //绑定监听器
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

        //绑定更新监听器
//        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                System.out.println("change appearing");
//                System.out.println(animation.getAnimatedFraction());
//                System.out.println(animation.getAnimatedValue("top"));
//                System.out.println(animation.getAnimatedValue("left"));
//            }
//        });



        LayoutTransition layoutTransition = new LayoutTransition();
//        layoutTransition.setAnimator(LayoutTransition.APPEARING, x);
//        layoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, objectAnimator);
        layoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, top);
        layoutTransition.setDuration(5000l);


        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll);
        linearLayout.setLayoutTransition(layoutTransition);

        Button b1 = new Button(this);
        b1.setText("dddd" + Math.random());
        linearLayout.addView(b1, 0);
    }


}