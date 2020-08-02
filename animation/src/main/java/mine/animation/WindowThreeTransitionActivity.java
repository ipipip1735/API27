package mine.animation;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionListenerAdapter;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import static android.view.Gravity.LEFT;

/**
 * Created by Administrator on 2019/3/20.
 */
public class WindowThreeTransitionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        setContentView(R.layout.activity_window_three);
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


    }

    public void start(View view) {
        System.out.println("********start******");

        ImageView imageView = findViewById(R.id.ivTwo);

        Bitmap bitmap = ((BitmapDrawable) getDrawable(R.drawable.w2)).getBitmap();
        System.out.println(bitmap.getWidth());
        System.out.println(bitmap.getHeight());

        Intent intent = new Intent(this, WindowFourTransitionActivity.class);

        //使用放大动画
        ActivityOptions options = ActivityOptions.makeScaleUpAnimation(imageView, 0,0, imageView.getWidth(), imageView.getHeight());

        //使用缩略图放大动画
//        ActivityOptions options = ActivityOptions.makeThumbnailScaleUpAnimation(imageView, bitmap, 10, 100);

        //使用逐步显现动画
//        ActivityOptions options = ActivityOptions.makeClipRevealAnimation(imageView, 0, 0, imageView.getWidth(), imageView.getHeight());

        //使用自定义动画
//        ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.enter, R.anim.exit);//仅使用进场动画（出场动画的起始值和结束值一样，所以不播放）
//        ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.enter, 0);//仅使用进场动画


        startActivity(intent, options.toBundle());


    }

    public void sharedStart(View view) {
        System.out.println("********sharedStart******");
    }

    public void sharedStop(View view) {
        System.out.println("********sharedStop******");

    }


}