package mine.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2019/3/13.
 */
public class LayoutTransitionActivity extends AppCompatActivity {

    Scene oneScene;
    Scene twoScene;
    Scene threeScene;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.scene_main);

        ViewGroup sceneRoot = (ViewGroup) findViewById(R.id.scene_root);
        oneScene = Scene.getSceneForLayout(sceneRoot, R.layout.one_scene, this);
        twoScene = Scene.getSceneForLayout(sceneRoot, R.layout.two_scene, this);
        threeScene = Scene.getSceneForLayout(sceneRoot, R.layout.three_scene, this);


        twoScene.setExitAction(new Runnable() {
            @Override
            public void run() {
                System.out.println("twoScene run!");
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

        //方法一
//        Transition fadeTransition = new Fade();
//        TransitionManager.go(oneScene, fadeTransition);

        //方法二
        TransitionManager.go(oneScene, null);


    }



    public void swap(View view) {
        System.out.println("********swap******");

//        fade();

        customView();
    }

    private void customView() {

        TransitionManager transitionManager = new TransitionManager();

        Fade fade = new Fade();
        fade.setDuration(5000l);

//        TransitionManager.beginDelayedTransition(twoScene.getSceneRoot());

//        transitionManager.setTransition(twoScene, fade);
        transitionManager.setTransition(twoScene, threeScene, fade);
        transitionManager.transitionTo(threeScene);


    }

    private void fade() {

        //方式一：XML方式
//        TransitionManager transitionManager = new TransitionManager();
//        Transition fade = TransitionInflater.from(this)
//                .inflateTransition(R.transition.fade_transition);



        //方式二：JAVA方式（一般需要自定义动画才使用本方法）
        Transition fadeTransition = new Fade() {
            @Override
            public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
                System.out.println("~~onAppear~~");
                System.out.println("sceneRoot is " + sceneRoot);
                System.out.println("view is " + view);
                System.out.println("startValues is " + startValues);
                System.out.println("endValues is " + endValues);

//                return super.onDisappear(sceneRoot, view, startValues, endValues);



                //自定义动画
                PropertyValuesHolder x = PropertyValuesHolder.ofFloat("x", -view.getHeight(), 0f);
                ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, x);
                objectAnimator.setDuration(1000);
                return objectAnimator;

            }

            @Override
            public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
                System.out.println("~~onDisappear~~");
                System.out.println("sceneRoot is " + sceneRoot);
                System.out.println("view is " + view);
                System.out.println("startValues is " + startValues);
                System.out.println("endValues is " + endValues);
//                return super.onDisappear(sceneRoot, view, startValues, endValues);

                //自定义动画
                PropertyValuesHolder x = PropertyValuesHolder.ofFloat("x", 0f, view.getHeight());
                ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(startValues.view, x);
                objectAnimator.setDuration(1000);
                return objectAnimator;
            }
        };
        TransitionManager.go(twoScene, fadeTransition);

    }


    Transition BaseTransition = new Transition() {
        @Override
        public void captureStartValues(TransitionValues transitionValues) {
            System.out.println("~~captureStartValues~~");
            System.out.println(transitionValues);

        }

        @Override
        public void captureEndValues(TransitionValues transitionValues) {
            System.out.println("~~captureEndValues~~");
            System.out.println(transitionValues);
        }

        @Override
        public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
            System.out.println("~~createAnimator~~");
            System.out.println("sceneRoot is " + sceneRoot);
            System.out.println("startValues is " + startValues);
            System.out.println("endValues is " + endValues);


            return super.createAnimator(sceneRoot, startValues, endValues);
        }
    };

}