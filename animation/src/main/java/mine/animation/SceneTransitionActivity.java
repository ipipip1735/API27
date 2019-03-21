package mine.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionListenerAdapter;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.transition.Fade.IN;
import static android.transition.Fade.OUT;
import static android.transition.TransitionSet.ORDERING_SEQUENTIAL;
import static android.transition.TransitionSet.ORDERING_TOGETHER;
import static android.view.Gravity.LEFT;

/**
 * Created by Administrator on 2019/3/13.
 */
public class SceneTransitionActivity extends AppCompatActivity {

    Scene oneScene;
    Scene twoScene;
    Scene threeScene;
    Scene fourScene;
    Scene fiveScene;
    TransitionManager transitionManager;
    ViewTreeObserver observer;
    ViewTreeObserver.OnPreDrawListener onPreDrawListener;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.scene_main);

        transitionManager = new TransitionManager();

        ViewGroup sceneRoot = (ViewGroup) findViewById(R.id.scene_root);
        oneScene = Scene.getSceneForLayout(sceneRoot, R.layout.one_scene, this);
        twoScene = Scene.getSceneForLayout(sceneRoot, R.layout.two_scene, this);
        threeScene = Scene.getSceneForLayout(sceneRoot, R.layout.three_scene, this);
        fourScene = Scene.getSceneForLayout(sceneRoot, R.layout.four_scene, this);
        fiveScene = Scene.getSceneForLayout(sceneRoot, R.layout.five_scene, this);


        twoScene.setEnterAction(new Runnable() {
            @Override
            public void run() {
                System.out.println("anotherScene run!");
                System.out.println(Thread.currentThread());
            }
        });
        oneScene.setExitAction(new Runnable() {
            @Override
            public void run() {
                System.out.println("oneScene run!");
                System.out.println(Thread.currentThread());
            }
        });


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


    public void recovery(View view) {
        System.out.println("********recovery******");

//        TransitionManager.go(oneScene);
        TransitionManager.go(threeScene);


    }

    public void swap(View view) {
        System.out.println("********swap******");
//        transitionWithXML(); //使用XML
//        transitionWithJAVA(); //使用JAVA

//        propagation();


        visibility(); //使用Fade/Explode/Slide

//        changeBounds(); //使用边界变换

//        transitionSet(); //使用变换集

//        delay(); //使用延迟动画

//        customTransition(); //自定义变化


    }

    private void propagation() {
        final Transition transition = new Explode() {
            @Override
            public void captureEndValues(TransitionValues transitionValues) {
                System.out.println("~~captureEndValues~~");
                System.out.println(transitionValues);
                super.captureEndValues(transitionValues);
            }

            @Override
            public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
                System.out.println("~~onAppear~~");
                System.out.println(view);
                return super.onAppear(sceneRoot, view, startValues, endValues);
            }

            @Override
            public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
                System.out.println("~~onDisappear~~");
                System.out.println(view);
                return super.onDisappear(sceneRoot, view, startValues, endValues);
            }
        };
        transition.setDuration(3000L);
//                .setStartDelay(1000L);
//                .setPropagation(new BaseTransitionPropagation());


        findViewById(R.id.cl).setVisibility(View.INVISIBLE); //修改可见性，迫使子View也能创建动画
        TransitionManager.go(oneScene, transition);
        oneScene.setEnterAction(new Runnable() {
            @Override
            public void run() {
                System.out.println("~~enter~~");
                findViewById(R.id.cl1).setVisibility(View.INVISIBLE); //修改可见性，迫使子View也能创建动画
            }
        });

//        TransitionManager.beginDelayedTransition(fiveScene.getSceneRoot(), transition);

    }


    private void transitionSet() {

        //方式一：使用XML
//        TransitionSet transtionSet = (TransitionSet) TransitionInflater.from(this)
//                .inflateTransition(R.transition.tansition_set);
//
//        transtionSet.setDuration(5000L).setOrdering(ORDERING_SEQUENTIAL);//设置播放顺序和时长
//
//        TransitionManager.go(fourScene, transtionSet.setDuration(5000L));


        //方式二：使用JAVA

        //使用转换集
        TransitionSet set = new TransitionSet()
                .addTransition(new MoveTransition())  //增加2个转换
                .addTransition(new ChangeBounds().setStartDelay(2000L)) //延迟播放2秒
                .setOrdering(ORDERING_TOGETHER) //播放顺序
                .setDuration(5000L); //播放时长

        //使用无场景变换
        TransitionManager.beginDelayedTransition((ViewGroup) findViewById(R.id.scene_root), set);


        ImageView imageView = findViewById(R.id.imageView3);
        imageView.setX(imageView.getX() + 500f);
        imageView.setY(imageView.getY() + 350f);

        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.height += 450f;
        layoutParams.width += 150f;
        imageView.setLayoutParams(layoutParams);


    }

    private void changeBounds() {


        final TransitionManager transitionManager = new TransitionManager();
        transitionManager.setTransition(threeScene,
                new Fade().setDuration(5000L)
                        .addListener(new TransitionListenerAdapter() {
                            @Override
                            public void onTransitionEnd(Transition transition) {
                                System.out.println("~~onTransitionEnd~~");
                                transitionManager.setTransition(fourScene, new ChangeBounds().setDuration(5000L));
                                transitionManager.transitionTo(fourScene);
                            }
                        }));
        transitionManager.transitionTo(threeScene);


    }

    private void delay() {

        //方式一：使用白名单
        ViewGroup root = findViewById(R.id.scene_root);
        Transition transition = new Explode().setDuration(2000L);//创建转换对象

        TransitionManager.beginDelayedTransition(root, transition);//使用无场景转换

        for (int i = 0; i < 5; i++) {
            TextView textView = new TextView(this);
            textView.setText("aaaaaaaaaaaaaa" + i);
//            textView.setX(i * 100 + 50);
//            textView.setY(i * 100 + 50);
            root.addView(textView); //增加到根View
        }
//        transition.addTarget(root.getChildAt(2)); //加入白名单


        //方式二：使用黑名单
//        ViewGroup root = findViewById(R.id.scene_root);
//
//        List<View> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            TextView textView = new TextView(this);
//            textView.setText("aaaaaaaaaaaaaa" + i);
//            textView.setX(i * 100 + 50);
//            textView.setY(i * 100 + 50);
//            list.add(textView); //加入到容器
//        }
//
//
//        TransitionManager.beginDelayedTransition(root, //使用无场景转换
//                new Fade().setDuration(5000L)//设置动画
//                        .excludeTarget(list.get(2), true));//增加到黑名单
//
//        for (View v : list) {
//            root.addView(v);
//        }

    }

    private void visibility() {

        //使用Fade变换
//        Transition fade = new Fade(OUT) {
//            @Override
//            public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
//                System.out.println("~~onAppear~~");
//
//                System.out.println("startValues is " + startValues);
//                System.out.println("endValues is " + endValues);
//
//                return super.onAppear(sceneRoot, view, startValues, endValues);
//            }
//
//            @Override
//            public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
//                System.out.println("~~onDisappear~~");
//
//                System.out.println("startValues is " + startValues);
//                System.out.println("endValues is " + endValues);
//
//                return super.onDisappear(sceneRoot, view, startValues, endValues);
//            }
//        };
//
//        findViewById(R.id.cl).setVisibility(View.INVISIBLE); //修改可见性，迫使子View也能创建动画
//        transitionManager = new TransitionManager();
//        transitionManager.setTransition(twoScene, fade.setDuration(3000L));//任意源场景
//        transitionManager.transitionTo(twoScene);





        //使用Slide变换
//        Transition transition = new Slide(LEFT){
//            @Override
//            public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
//                System.out.println("~~onAppear~~");
//
//                System.out.println("startValues is " + startValues);
//                System.out.println("endValues is " + endValues);
//
//                return super.onAppear(sceneRoot, view, startValues, endValues);
//            }
//
//            @Override
//            public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
//                System.out.println("~~onDisappear~~");
//
//                System.out.println("startValues is " + startValues);
//                System.out.println("endValues is " + endValues);
//
//                return super.onDisappear(sceneRoot, view, startValues, endValues);
//            }
//        };
//
//        findViewById(R.id.cl).setVisibility(View.INVISIBLE); //修改可见性，迫使子View也能创建动画
//        transitionManager = new TransitionManager();
//        transitionManager.setTransition(twoScene, transition.setDuration(3000L));//任意源场景
//        transitionManager.transitionTo(twoScene);








        //使用Explode变换
        Transition explode = new Explode() {
            @Override
            public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
                System.out.println("~~onAppear~~");

                System.out.println("startValues is " + startValues);
                System.out.println("endValues is " + endValues);

                return super.onAppear(sceneRoot, view, startValues, endValues);
            }

            @Override
            public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
                System.out.println("~~onDisappear~~");

                System.out.println("startValues is " + startValues);
                System.out.println("endValues is " + endValues);

                return super.onDisappear(sceneRoot, view, startValues, endValues);
            }
        };

        final View view = findViewById(R.id.imageView9);
        explode.setEpicenterCallback(new Transition.EpicenterCallback() {
            @Override
            public Rect onGetEpicenter(Transition transition) {
                System.out.println("~~onGetEpicenter~~");

                Rect rect = new Rect();
                view.getGlobalVisibleRect(rect);
                return rect;
            }
        });


        findViewById(R.id.cl).setVisibility(View.INVISIBLE); //修改可见性，迫使子View也能创建动画
        transitionManager = new TransitionManager();
        transitionManager.setTransition(twoScene, explode.setDuration(3000L));//任意源场景
        transitionManager.transitionTo(twoScene);


    }


    private void customTransition() {
        TransitionManager.go(twoScene, new VisibilityTransition().setDuration(5000L));
    }


    private void transitionWithJAVA() {
        Transition fade = new Fade().setDuration(2000L);
        Transition slide = new Slide().setDuration(2000L);
        Transition explode = new Explode().setDuration(2000L);


        transitionManager = new TransitionManager();

        transitionManager.setTransition(twoScene, fade);//任意源场景
        transitionManager.setTransition(twoScene, slide);//精确匹配源场景

        transitionManager.transitionTo(twoScene);
//        transitionManager.transitionTo(threeScene);


    }

    private void transitionWithXML() {
        //方法一
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fade_transition);
        TransitionManager.go(twoScene, transition);


        //方法二
//        TransitionManager transitionManager = TransitionInflater.from(this).inflateTransitionManager(R.transition.tansition, oneScene.getSceneRoot());
//        TransitionManager.go(twoScene);
    }


}