package mine.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Intent;
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
import android.widget.TextView;

import java.util.Objects;

import static android.transition.TransitionSet.ORDERING_SEQUENTIAL;
import static android.transition.TransitionSet.ORDERING_TOGETHER;

/**
 * Created by Administrator on 2019/3/13.
 */
public class SceneTransitionActivity extends AppCompatActivity {

    Scene oneScene;
    Scene twoScene;
    Scene threeScene;
    Scene fourScene;
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

        TransitionManager.go(oneScene);
//        TransitionManager.go(threeScene);


    }

    public void swap(View view) {
        System.out.println("********swap******");
//        transitionWithXML(); //使用XML
//        transitionWithJAVA(); //使用JAVA


//        visibility(); //使用Fade/Explode/Slide

//        changeBounds(); //使用边界变换

//        transitionSet();

        delay(); //使用延迟动画

//        customTransition(); //自定义变化


    }

    private void transitionSet() {

        TransitionSet transtionSet = (TransitionSet) TransitionInflater.from(this)
                .inflateTransition(R.transition.tansition_set);

        transtionSet.setDuration(5000L).setOrdering(ORDERING_SEQUENTIAL);

        TransitionManager.go(fourScene, transtionSet.setDuration(5000L));

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


        final ViewGroup root = findViewById(R.id.scene_root);
        for (int i = 0; i < 5; i++) {
            TextView textView = new TextView(this);
            textView.setText("aaaaaaaaaaaaaa" + i);
            root.addView(textView);
        }

        Fade transition = new Fade() {

            @Override
            public void captureStartValues(TransitionValues transitionValues) {
                System.out.println("~~captureStartValues~~");

                System.out.println(transitionValues);
                super.captureStartValues(transitionValues);


            }

            @Override
            public void captureEndValues(TransitionValues transitionValues) {
                System.out.println("~~captureEndValues~~");
                System.out.println(transitionValues);

                super.captureEndValues(transitionValues);
            }

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






        transition.setDuration(5000L).excludeChildren(root.getChildAt(2), true);
        TransitionManager.beginDelayedTransition(root, transition);

        //        transition.addTarget(root.getChildAt(2));


        for (int i = 1; i <= 5; i++) {
            View view = root.getChildAt(i);
            view.setX(i * 100 + 50);
            view.setY(i * 100 + 50);
        }


    }

    private void visibility() {

        Transition transition = new Fade() {
            //        Transition transition = new Slide(){
//        Transition transition = new Explode() {
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


        transitionManager = new TransitionManager();
        transitionManager.setTransition(twoScene, transition.setDuration(3000L));//任意源场景
        transitionManager.transitionTo(twoScene);


    }


    private void customTransition() {

        TransitionManager.go(twoScene, new VisibilityTransition().setDuration(5000L));

    }


    private void transitionWithJAVA() {
        Transition fade = new Fade();
        Transition slide = new Slide();


        transitionManager = new TransitionManager();

        transitionManager.setTransition(twoScene, fade.setDuration(6000l));//任意源场景
        transitionManager.setTransition(twoScene, threeScene, slide.setDuration(10000));//精确匹配源场景

        transitionManager.transitionTo(twoScene);
        transitionManager.transitionTo(threeScene);


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