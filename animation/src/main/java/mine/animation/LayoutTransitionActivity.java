package mine.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

/**
 * Created by Administrator on 2019/3/13.
 */
public class LayoutTransitionActivity extends AppCompatActivity {

    Scene oneScene;
    Scene twoScene;
    Scene threeScene;
    TransitionManager transitionManager;


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


        twoScene.setExitAction(new Runnable() {
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

//        Transition fadeTransition = new Fade();
//        TransitionManager.go(oneScene, fadeTransition);
//        transitionManager.transitionTo(threeScene);
        transitionManager.transitionTo(oneScene);

    }

    public void swap(View view) {
        System.out.println("********swap******");
//        transitionWithXML(); //使用XML
//        transitionWithJAVA(); //使用JAVA

//        buildIn();

        customTransition();


    }


    private void buildIn() {

        Transition fade = new Fade();
        Transition slide = new Slide();
        Transition changeBounds = new ChangeBounds();
        System.out.println(((Slide) slide).getSlideEdge());


        transitionManager = new TransitionManager();

        transitionManager.setTransition(twoScene, changeBounds.setDuration(2000l));//任意源场景
        transitionManager.transitionTo(twoScene);

    }


    private void customTransition() {

        Transition transition = new Transition() {

            final String X = getPackageName() + ":Transition:" + "X";
            final String Y = getPackageName() + ":Transition:" + "Y";
            final String ALPHA = getPackageName() + ":Transition:" + "ALPHA";


            @Override
            public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
                System.out.println("~~createAnimator~~");
                System.out.println("sceneRoot is " + sceneRoot);
                System.out.println("startValues is " + startValues);
                System.out.println("endValues is " + endValues);

                //出场动画
//                if (Objects.nonNull(endValues) && !endValues.view.equals(sceneRoot)) {
//                    PropertyValuesHolder x = PropertyValuesHolder.ofFloat("x",
//                            (float)endValues.view.getX(),
//                            (float)endValues.values.get(X));
//                    PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha",
//                            1f,(float)endValues.values.get(ALPHA));
//                    ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(endValues.view, x, alpha);
//                    objectAnimator.addListener(new AnimatorListenerAdapter(){
//                        @Override
//                        public void onAnimationStart(Animator animation) {
//                            System.out.println("duration is " + animation.getDuration());
//                        }
//                    });
//                    return objectAnimator;
//
//                }


                //入场动画
//                if (Objects.nonNull(startValues) && !startValues.view.equals(sceneRoot)) {
//
//
//                    PropertyValuesHolder x = PropertyValuesHolder.ofFloat("x",
//                            (float) startValues.values.get(X), 350f);
//
////                    PropertyValuesHolder y = PropertyValuesHolder.ofFloat("y",
////                            (float) startValues.values.get(Y), 350f);
//
//                    PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha",
//                            (float)startValues.values.get(ALPHA),0.5f);
//                    ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(startValues.view, x, alpha);
//                    objectAnimator.addListener(new AnimatorListenerAdapter(){
//                        @Override
//                        public void onAnimationStart(Animator animation) {
//                            System.out.println("XXXXXXX duration is " + animation.getDuration());
//                        }
//                    });
//                    return objectAnimator;
//                }


//                return super.createAnimator(sceneRoot, startValues, endValues);

                ObjectAnimator objectAnimator = null;
                PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.3f, 0.8f);
                if (Objects.nonNull(startValues)) {
                    objectAnimator = ObjectAnimator.ofPropertyValuesHolder(startValues.view, alpha);
                    System.out.println("-------s----------");
                    return objectAnimator;
                }

                if (Objects.nonNull(endValues)) {
                    objectAnimator = ObjectAnimator.ofPropertyValuesHolder(endValues.view, alpha);
                    System.out.println("--------e---------");
                    return objectAnimator;
                }

                System.out.println("-----------default------");
                return objectAnimator;

            }

            @Override
            public void captureStartValues(TransitionValues transitionValues) {
                System.out.println("~~captureStartValues~~");

                transitionValues.values.put(X, transitionValues.view.getX());
                transitionValues.values.put(Y, transitionValues.view.getY());
                transitionValues.values.put(ALPHA, transitionValues.view.getAlpha() - 0.8f);
            }

            @Override
            public void captureEndValues(TransitionValues transitionValues) {
                System.out.println("~~captureEndValues~~");

                transitionValues.values.put(X, transitionValues.view.getX() + 150f);
                transitionValues.values.put(Y, transitionValues.view.getY() + 150f);
                transitionValues.values.put(ALPHA, transitionValues.view.getAlpha() - 0.8f);
            }
        };
        transition.setDuration(3000l);


        TransitionManager transitionManager = new TransitionManager();
//        transitionManager.setTransition(oneScene, transition);
        transitionManager.setTransition(twoScene, transition);
//        transitionManager.setTransition(oneScene, twoScene, transition);
        transitionManager.go(twoScene, transition);


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