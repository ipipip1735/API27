package mine.material;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.transition.platform.MaterialFadeThrough;

/**
 * Created by Administrator on 2020/7/28.
 */
public class FadeThroughOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fade_two);




        //设置入场动画
        getWindow().setEnterTransition(new MaterialFadeThrough()
                .addTarget(R.id.cl)//使用白名单，仅允许布局配置中的View参与动画
//                .excludeTarget(android.R.id.statusBarBackground, true)//使用黑名单，排除顶部状态条
//                .excludeTarget(android.R.id.navigationBarBackground, true)//使用黑名单，排除底部导航条
                .setDuration(1000L));


        //设置返回动画
//        getWindow().setReturnTransition(new MaterialSharedAxis(direction, false)
////                .addTarget(R.id.cl)//使用白名单，仅允许布局配置中的View参与动画
////                .excludeTarget(android.R.id.statusBarBackground, true)//使用黑名单，排除顶部状态条
////                .excludeTarget(android.R.id.navigationBarBackground, true)//使用黑名单，排除底部导航条
//                .setDuration(1000L));
//        getWindow().setAllowEnterTransitionOverlap(false);//入场动画使用顺序播放模式



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
