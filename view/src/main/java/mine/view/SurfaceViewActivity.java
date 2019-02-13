package mine.view;

import android.graphics.Canvas;
import android.graphics.Paint;
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

    private SurfaceHolder mSurfaceHolder;
    private Thread thread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //方式一：全屏模式
//        SurfaceView surfaceView = new SurfaceView(this);
//        surfaceView.setZOrderOnTop(true);
//        setContentView(surfaceView);




        //方式二：窗口模式
        setContentView(R.layout.activity_surface);
        MySurfaceView view = findViewById(R.id.msfv);




        mSurfaceHolder = view.getHolder();
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                System.out.println("~~SurfaceView.callback.surfaceCreated~~");

                thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Random random = new Random();
                        while (!thread.isInterrupted()) {
                            try {
                                Thread.sleep(1000L);
                                Canvas canvas = mSurfaceHolder.lockCanvas();;
                                canvas.drawARGB(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
                                System.out.println("--");
                                Paint p = new Paint();
                                p.setColor(getResources().getColor(R.color.AliceBlue, null));
                                canvas.drawCircle(100f, 100f, 100f, p);
                                mSurfaceHolder.unlockCanvasAndPost(canvas);
                            } catch (InterruptedException e) {
                                thread.interrupt();
                            }
                        }

                    }
                });
//                thread.start();

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                System.out.println("~~SurfaceView.callback.surfaceChanged~~");

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                System.out.println("~~SurfaceView.callback.surfaceDestroyed~~");

            }
        });
    }


    public void addView(View view) {
        System.out.println("~~button.addView~~");

//        mSurfaceHolder

    }

    public void stop(View view) {
        System.out.println("~~button.addView~~");
        thread.interrupt();

    }
}




