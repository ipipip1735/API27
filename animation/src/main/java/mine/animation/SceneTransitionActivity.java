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
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionListenerAdapter;
import android.transition.TransitionManager;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

/**
 * Created by Administrator on 2019/3/13.
 */
public class SceneTransitionActivity extends AppCompatActivity {

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
        View view1 = findViewById(R.id.cl1);
        view1.setX(200);
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
//        transitionManager.transitionTo(oneScene);


//        ViewGroup root = findViewById(R.id.scene_root);
//        View view1 = findViewById(R.id.cl1);
//        root.getOverlay().add(view1);

//        root.removeView(view1);

        View cl = findViewById(R.id.cl1);
        cl.requestLayout();
//        TextView textView = cl.findViewById(R.id.oneTV1);
//        textView.




    }

    public void swap(View view) {
        System.out.println("********swap******");
//        transitionWithXML(); //使用XML
//        transitionWithJAVA(); //使用JAVA

//        buildIn();
//        customTransition();



        test();




    }

    private void test() {
        //        ViewGroup viewGroup = findViewById(R.id.scene_root);
//        viewGroup.removeAllViews();

        View view = findViewById(R.id.cl1);

//        view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
//            @Override
//            public void onViewAttachedToWindow(View v) {
//                System.out.println("~~onViewAttachedToWindow~~");
//            }
//
//            @Override
//            public void onViewDetachedFromWindow(View v) {
//                System.out.println("~~onViewDetachedFromWindow~~");
//
//            }
//        });



        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                System.out.println("~~onPreDraw~~");

                TextView textView = findViewById(R.id.oneTV1);
                textView.animate().x(100f).setDuration(3000L);

                return true;
            }
        });









//        int[] location = new int[2];
//        view.getLocationOnScreen(location);
//        System.out.println("X is " + location[0]);
//        System.out.println("Y is " + location[1]);
//
//        view1.getLocationInWindow(loc);
//        System.out.println("0 is " + loc[0]);
//        System.out.println("1 is " + loc[1]);
//
//
//
//        view1.offsetLeftAndRight(5);
//        view1.offsetTopAndBottom(5);

//
//        final ViewGroup root = findViewById(R.id.scene_root);
//        System.out.println("children are " + root.getChildCount());
//
//
//        View cl = root.findViewById(R.id.cl1);
////        Button button = findViewById(R.id.button18);
////        button.setX(button.getX() + 150);
//
//        ViewGroupOverlay overlay = root.getOverlay();
//        System.out.println("children are " + root.getChildCount());
//        overlay.add(root.findViewById(R.id.cl1));
//        System.out.println("children are " + root.getChildCount());
//
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//
//                    root.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            root.setY(root.getY() + 15);
//                        }
//                    });
//                    try {
//                        Thread.sleep(1000L);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();


//        root.removeAllViews();

//        System.out.println(cl);
//        cl.setY(cl.getX() + 15);
//
//        TextView textView = new TextView(this);
//        textView.setText("sldf");
//        root.addView(textView);
//
//
//        System.out.println("count is " + root.getChildCount());
//        for (int i = 0; i < root.getChildCount(); i++) {
//            System.out.println(root.getChildAt(i));
//
//        }
    }


    private void buildIn() {

        Transition slide = new Slide();
        Transition changeBounds = new ChangeBounds();

//        Transition fade = new Fade() {
//            @Override
//            public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
//
//                if (startValues != null) {
//                    System.out.println(startValues.view);
//                }
//                if (endValues != null) {
//                    System.out.println(endValues.view);
//                }
//                if (startValues == null || endValues == null)
//                    System.out.println("--------null--------");
//                Animator animator = super.createAnimator(sceneRoot, startValues, endValues);
//
//                if(animator != null)
//                animator.addListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//                        View view = findViewById(R.id.oneTV1);
//                        System.out.println("view is " + view);
//                    }
//                });
//                return animator;
//            }
//        };

        Transition fade = new Fade() {
            @Override
            public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
                System.out.println("onAppear view is " + view);
                return super.onAppear(sceneRoot, view, startValues, endValues);
            }

            @Override
            public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
                System.out.println("onDisappear view is " + view);
                return super.onDisappear(sceneRoot, view, startValues, endValues);
            }
        };


        transitionManager = new TransitionManager();

        transitionManager.setTransition(twoScene, fade.setDuration(3000L));//任意源场景
        transitionManager.transitionTo(twoScene);

    }


    private void customTransition() {


//        Fade fade = new Fade();
//        fade.addListener(new TransitionListenerAdapter() {
//            @Override
//            public void onTransitionStart(Transition transition) {
//                System.out.println("~~onTransitionStart~~");
//
//            }
//        });




        TransitionManager transitionManager = new TransitionManager();


//        transitionManager.setTransition(oneScene, transition);
//        transitionManager.setTransition(twoScene, transition);
//        transitionManager.setTransition(oneScene, twoScene, transition);


//        transitionManager.transitionTo(twoScene);
//        TransitionManager.go(twoScene, transition);
//        TransitionManager.go(twoScene, transition);


//        TransitionManager.go(twoScene, new FadeTransition().setDuration(5000L));
       TransitionManager.go(twoScene, new BaseTransition().setDuration(5000L));
//       TransitionManager.go(twoScene, new VisibilityTransition().setDuration(5000L));


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