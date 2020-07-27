package mine.material;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;
import com.google.android.material.transition.platform.MaterialSharedAxis;

import static android.view.Gravity.LEFT;
import static android.view.Gravity.RIGHT;

/**
 * Created by Administrator on 2020/7/27.
 */
public class SharedAxisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");


        getWindow().setExitTransition(new MaterialSharedAxis(MaterialSharedAxis.X, true)
//                .excludeTarget(android.R.id.statusBarBackground, true)
//                .excludeTarget(android.R.id.navigationBarBackground, true)
                .setDuration(500L));
        getWindow().setReenterTransition(new MaterialSharedAxis(MaterialSharedAxis.X, false)
                .setDuration(5000L));
//        getWindow().setAllowReturnTransitionOverlap(false);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_axis_one);


//        getWindow().setExitTransition(new Slide(LEFT).setDuration(5000L));
//        getWindow().setReenterTransition(new Slide(RIGHT).setDuration(5000L));
//        getWindow().setAllowReturnTransitionOverlap(false);

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


        Intent intent = new Intent(this, SharedAxisOneActivity.class);


        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);

        startActivity(intent, options.toBundle());

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
