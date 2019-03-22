package mine.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;

import static android.view.Gravity.LEFT;
import static android.view.Gravity.RIGHT;
import static android.view.Gravity.TOP;

/**
 * Created by Administrator on 2019/3/20.
 */
public class WindowTwoTransitionActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        Window window = getWindow();

//        window.setEnterTransition(new Fade().setDuration(5000L)); //进入动画
//        getWindow().setExitTransition(new Slide(RIGHT).setDuration(5000L)); //返回动画





//        window.requestFeature(Window.FEATURE_NO_TITLE);
//        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);


//        window.setAllowEnterTransitionOverlap(false);
//        window.setAllowReturnTransitionOverlap(false);




        System.out.println(getClass().getSimpleName() + " is " + window);
        System.out.println("getEnterTransition is "  + window.getEnterTransition());
        System.out.println("getExitTransition is "  + window.getExitTransition());
        System.out.println("getReturnTransition is "  + window.getReturnTransition());
        System.out.println("getReenterTransition is "  + window.getReenterTransition());


        setContentView(R.layout.activity_window_two);


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

//        getWindow().setReturnTransition(new Slide(RIGHT).setDuration(5000L)); //返回动画
        finishAfterTransition();


//        finish();


    }

    public void start(View view) {
        System.out.println("********start******");

    }

}