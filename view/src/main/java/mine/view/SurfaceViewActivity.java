package mine.view;

import android.graphics.Canvas;
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


                thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (!thread.isInterrupted()) {
                            Canvas canvas = surfaceHolder.lockCanvas();
                            Paint p = new Paint();
                            p.setColor(getResources().getColor(R.color.AliceBlue, null));
                            canvas.drawARGB(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
                            canvas.drawCircle(100f, 100f, 100f, p);
                            surfaceHolder.unlockCanvasAndPost(canvas);
                            try {
                                System.out.println("--");
                                Thread.sleep(1000L);
                            } catch (InterruptedException e) {
                                thread.interrupt();
                            }
                        }

                    }
                });


//                Canvas canvas = surfaceHolder.lockCanvas();
//                Paint p = new Paint();
//                p.setColor(getResources().getColor(R.color.AliceBlue, null));
//                canvas.drawARGB(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
//                canvas.drawCircle(100f, 100f, 100f, p);
//                surfaceHolder.unlockCanvasAndPost(canvas);


            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int frmt, int w, int h) {
                System.out.println("~~~~~~~  " + getClass().getSimpleName() + ".surfaceChanged  ~~~~~~~");


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

        thread.start();

    }

    public void stop(View view) {
        System.out.println("*********  button.stop  *********");

        thread.interrupt();
    }

    public void rect(View view) {
        System.out.println("*********  button.rect  *********");


        Random random = new Random();
        Canvas canvas = surfaceHolder.lockCanvas();
        Paint p = new Paint();
        p.setColor(getResources().getColor(R.color.AliceBlue, null));
        canvas.drawARGB(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
        canvas.drawCircle(100f, 100f, 100f, p);
        canvas.drawColor(getResources().getColor(R.color.MediumSeaGreen, null), PorterDuff.Mode.DST_OVER);
        surfaceHolder.unlockCanvasAndPost(canvas);

    }
}




