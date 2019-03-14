package mine.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2019/3/13.
 */
public class LayoutTransitionActivity extends AppCompatActivity {

    Scene oneScene;
    Scene anotherScene;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.scene_main);

        ViewGroup sceneRoot = (ViewGroup) findViewById(R.id.scene_root);
        oneScene = Scene.getSceneForLayout(sceneRoot, R.layout.one_scene, this);


        View view = getLayoutInflater().inflate(R.layout.another_scene, null);
        anotherScene = new Scene(sceneRoot, view);




        anotherScene.setExitAction(new Runnable() {
            @Override
            public void run() {
                System.out.println("anotherScene Exit");
                System.out.println(Thread.currentThread());
            }
        });

        anotherScene.setEnterAction(new Runnable() {
            @Override
            public void run() {
                System.out.println("anotherScene Enter");
                System.out.println(Thread.currentThread());
            }
        });



        oneScene.setExitAction(new Runnable() {
            @Override
            public void run() {
                System.out.println("oneScene Exit");
                System.out.println(Thread.currentThread());
            }
        });
        oneScene.setEnterAction(new Runnable() {
            @Override
            public void run() {
                System.out.println("oneScene Enter");
                System.out.println(Thread.currentThread());
            }
        });


        System.out.println("rootView is " + oneScene.getSceneRoot());

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


    public void stop(View view) {
        System.out.println("********stop******");

//        Transition fadeTransition = new Fade();
//        TransitionManager.go(oneScene, fadeTransition);
//        oneScene.enter();
        oneScene.exit();

    }

    public void swap(View view) {
        System.out.println("********swap******");

        //方式一：使用快捷方法go()
//        Transition fadeTransition = new Fade();
//        TransitionManager.go(anotherScene, fadeTransition);


        //方法二：
        Transition fadeTransition = new Fade();
        TransitionManager transitionManager =  new TransitionManager();
        transitionManager.setTransition(oneScene, fadeTransition);
        transitionManager.setTransition(anotherScene, fadeTransition);
        transitionManager.transitionTo(anotherScene);


    }


}