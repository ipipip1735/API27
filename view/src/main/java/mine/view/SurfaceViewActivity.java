package mine.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.Random;

/**
 * Created by Administrator on 2017/8/27.
 */

public class SurfaceViewActivity extends AppCompatActivity {

    private SurfaceHolder surfaceHolder;
    private Thread thread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        super.onCreate(savedInstanceState);


        //方式一：窗口模式
        setContentView(R.layout.activity_surface);
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.msfv);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                System.out.println("~~~~~~~  " + getClass().getSimpleName() + ".surfaceCreated  ~~~~~~~");

                final Random random = new Random();


//                thread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        while (!thread.isInterrupted()) {
//                            Canvas canvas = surfaceHolder.lockCanvas();
//                            Paint p = new Paint();
//                            p.setColor(getResources().getColor(R.color.AliceBlue, null));
//                            canvas.drawARGB(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
//                            canvas.drawCircle(100f, 100f, 100f, p);
//                            surfaceHolder.unlockCanvasAndPost(canvas);
//                            try {
//                                System.out.println("--");
//                                Thread.sleep(1000L);
//                            } catch (InterruptedException e) {
//                                thread.interrupt();
//                            }
//                        }
//
//                    }
//                });


                Canvas canvas = surfaceHolder.lockCanvas();
                Paint p = new Paint();
                p.setColor(getResources().getColor(R.color.AliceBlue, null));
                canvas.drawARGB(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
                canvas.drawCircle(100f, 100f, 100f, p);
                surfaceHolder.unlockCanvasAndPost(canvas);


            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int frmt, int w, int h) {
                System.out.println("~~~~~~~  " + getClass().getSimpleName() + ".surfaceChanged  ~~~~~~~");

                System.out.println("frmt is " + frmt);
                System.out.println("w is " + w);
                System.out.println("h is " + h);


            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                System.out.println("~~~~~~~  " + getClass().getSimpleName() + ".surfaceDestroyed  ~~~~~~~");

            }
        });


        //方式二：全屏模式
//        MySurfaceView view = new MySurfaceView(this);
//        setContentView(view);
//        surfaceView.setZOrderOnTop(true);
//        view.getHolder().addCallback(this);

    }


    public void start(View view) {
        System.out.println("*********  button.start  *********");

//        thread.start();

    }

    public void stop(View view) {
        System.out.println("*********  button.stop  *********");

//        thread.interrupt();

//        Random random = new Random();
//        Paint p = new Paint();

//        p.setColor(getResources().getColor(R.color.MediumSeaGreen, null));
//        Canvas canvas = surfaceHolder.lockCanvas();
//        canvas.drawColor(Color.BLUE);
//        surfaceHolder.unlockCanvasAndPost(canvas);

        surfaceHolder.setFixedSize(1000, 1200);


    }

    public void rect(View view) {
        System.out.println("*********  button.rect  *********");


//        Random random = new Random();
//        Paint p = new Paint();
//        p.setColor(getResources().getColor(R.color.MediumSeaGreen, null));


        System.out.println(surfaceHolder.getSurfaceFrame());
//        surfaceHolder.setFixedSize(10, 25);
//        System.out.println(surfaceHolder.getSurfaceFrame());
//        surfaceHolder.setSizeFromLayout();
//        surfaceHolder.setKeepScreenOn(false);
//        System.out.println(surfaceHolder.getSurfaceFrame());

        Canvas canvas;

//        canvas = surfaceHolder.lockCanvas();
//        canvas.drawColor(Color.BLUE);
//        surfaceHolder.unlockCanvasAndPost(canvas);
//
//        try {
//            Thread.sleep(1000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        canvas = surfaceHolder.lockCanvas(null);
        canvas = surfaceHolder.lockCanvas(new Rect(100,100, 800, 600));
        canvas.drawColor(Color.YELLOW);
        surfaceHolder.unlockCanvasAndPost(canvas);
//
//
//
//        try {
//            Thread.sleep(1000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//
//        canvas = surfaceHolder.lockCanvas(null);
////        canvas = surfaceHolder.lockCanvas(new Rect(50,50, 100, 150));
//        canvas.drawColor(Color.RED);
//        surfaceHolder.unlockCanvasAndPost(canvas);
//
//        try {
//            Thread.sleep(1000L);
//            System.out.println("sleep 1");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//        canvas = surfaceHolder.lockCanvas(null);
////        canvas = surfaceHolder.lockCanvas(new Rect(50,60, 100, 160));
//        canvas.drawColor(Color.GREEN);
//        surfaceHolder.unlockCanvasAndPost(canvas);




    }
}




