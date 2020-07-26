package mine.material;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.slider.Slider;
import com.google.android.material.transition.platform.MaterialContainerTransform;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

import java.util.Random;

/**
 * Created by Administrator on 2020/7/15.
 */
public class ContainerTransformOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");


//        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);

        findViewById(android.R.id.content).setTransitionName("shared");


        long duration = 3000L;


        setEnterSharedElementCallback(new MaterialContainerTransformSharedElementCallback());



        Transition materialContainerTransform = new MaterialContainerTransform()
                .addTarget(android.R.id.content)
                .setDuration(duration);

        Transition materialContainerTransform1 = new MaterialContainerTransform()
                .addTarget(android.R.id.content)
                .setDuration(3000L);

        Transition changesBounds = new ChangeBounds()
                .setDuration(duration)
                .addListener(new Transition.TransitionListener() {
                    @Override
                    public void onTransitionStart(Transition transition) {
                        System.out.println("~~ChangeBounds.onTransitionStart~~");
                    }

                    @Override
                    public void onTransitionEnd(Transition transition) {
                        System.out.println("~~ChangeBounds.onTransitionEnd~~");
                    }

                    @Override
                    public void onTransitionCancel(Transition transition) {
                        System.out.println("~~ChangeBounds.onTransitionCancel~~");
                    }

                    @Override
                    public void onTransitionPause(Transition transition) {
                        System.out.println("~~ChangeBounds.onTransitionPause~~");
                    }

                    @Override
                    public void onTransitionResume(Transition transition) {
                        System.out.println("~~ChangeBounds.onTransitionResume~~");
                    }
                });



        getWindow().setSharedElementEnterTransition(materialContainerTransform);
        getWindow().setSharedElementReturnTransition(materialContainerTransform1);
//        getWindow().setSharedElementReturnTransition(changesBounds);
//        getWindow().setTransitionBackgroundFadeDuration(20000L);




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_transform_one);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestoreInstanceState  *********");

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestart  *********");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("*********  " + getClass().getSimpleName() + ".onResume  *********");
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
        System.out.println("~~button.start~~");

    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");
    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");

    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void del(View view) {
        System.out.println("~~button.del~~");

    }


    public void query(View view) {
        System.out.println("~~button.query~~");

    }
}
