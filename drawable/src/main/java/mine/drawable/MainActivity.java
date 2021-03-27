package mine.drawable;

import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * Created by Administrator on 2021/3/26.
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestoreInstanceState  *********");
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
        super.onPause();
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("*********  " + getClass().getSimpleName() + ".onBackPressed  *********");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onSaveInstanceState  *********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");
    }


    public void start(View view) {
        System.out.println("~~button.start~~");


        //设置<bitmap>的重叠模式
//        Drawable bg = findViewById(R.id.imageView1).getBackground();
//        BitmapDrawable bmp = (BitmapDrawable) bg;
//        bmp.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);


        //获取LayerDrawable中的<item>
//        ImageView imageView = findViewById(R.id.imageView1);
//        System.out.println("imageView = " + imageView);
//        LayerDrawable layerDrawable = (LayerDrawable) imageView.getDrawable();
//        System.out.println("layerDrawable = " + layerDrawable);
//        System.out.println(layerDrawable.findDrawableByLayerId(R.id.item1));//查找<item>
//        System.out.println(layerDrawable.findIndexByLayerId(R.id.item1));//查找<item>的索引
//        System.out.println(layerDrawable.findIndexByLayerId(R.id.item2));

        //方式二
//        LayerDrawable layerDrawable = (LayerDrawable) getResources().getDrawable(R.drawable.layer);
//        System.out.println("layerDrawable = " + layerDrawable);
//        System.out.println(layerDrawable.findIndexByLayerId(R.id.item1));


        //使用LevelDrawable
//        ImageView imageView = findViewById(R.id.imageView);
//        imageView.setImageLevel(3);


        //使用TransitionDrawable
//        ImageView imageView = findViewById(R.id.imageView);
//        TransitionDrawable transitionDrawable = (TransitionDrawable) imageView.getDrawable();
//        transitionDrawable.setCrossFadeEnabled(true);
//        transitionDrawable.startTransition(10000);//变换
////        transitionDrawable.reverseTransition(5000);//反变换

        //使用ClipDrawable
//        ImageView imageview = findViewById(R.id.imageView);
//        ClipDrawable clipDrawable = (ClipDrawable) imageview.getBackground();
//        clipDrawable.setLevel(10000);
////        new Thread(new Runnable() {
////            @Override
////            public void run() {
////                for (int i = 0; i < 100; i++) {
////                    clipDrawable.setLevel(clipDrawable.getLevel() + 100);
////                    System.out.println("level is " + clipDrawable.getLevel());
////                    try {
////                        Thread.sleep(100L);
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    }
////
////                }
////            }
////        }).start();


        //使用ScaleDrawable
        //方式一：
//        ImageView imageview = findViewById(R.id.imageView);
//        ScaleDrawable scaleDrawable = (ScaleDrawable) imageview.getBackground();
//        System.out.println("level is " + scaleDrawable.getLevel());
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 999; i++) {
//                    scaleDrawable.setLevel(Math.min(10000, scaleDrawable.getLevel() + 100));//放大
//                    System.out.println(scaleDrawable.getLevel());
//                    try {
//                        Thread.sleep(100L);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//        }).start();

        //方式二：
//        ImageView imageview = findViewById(R.id.imageView);
//        imageview.getDrawable().setLevel(5000);

        //方式三：
        ImageView imageview = findViewById(R.id.imageView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    imageview.setImageLevel(i * 100);
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();

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