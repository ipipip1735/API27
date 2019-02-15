package mine.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.Random;

/**
 * Created by Administrator on 2017/8/27.
 */

public class SurfaceViewActivity extends AppCompatActivity {

    private SurfaceHolder surfaceHolder;
    private SurfaceView surfaceView;
    private Thread thread;
    final Random random = new Random();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        super.onCreate(savedInstanceState);


        //方式一：窗口模式
        setContentView(R.layout.activity_surface);
        surfaceView = (SurfaceView) findViewById(R.id.msfv);
//        surfaceView.setZOrderOnTop(true);//设置顺序策略
//        surfaceView.setZOrderMediaOverlay(true);//置还顺序策略
//        surfaceView.setSecure(false);//使用安全截屏


        surfaceHolder = surfaceView.getHolder();
//        surfaceHolder.setKeepScreenOn(true);//设置屏幕常亮
//        surfaceHolder.setFixedSize(5000, 5000); //使用固定Buffer尺寸
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                System.out.println("~~~~~~~  " + getClass().getSimpleName() + ".surfaceCreated  ~~~~~~~");


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

                System.out.println("holder is " + holder);
                System.out.println("frmt is " + frmt);
                System.out.println("h is " + h);
                System.out.println("w is " + w);

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

        boolean b = surfaceView.gatherTransparentRegion(new Region(920, 12400, 924, 1242));
        System.out.println(b);
        System.out.println("getX is " + surfaceView.getX());
        System.out.println("getY is " + surfaceView.getY());
        System.out.println("getWidth is " + surfaceView.getWidth());
        System.out.println("getHeight is " + surfaceView.getHeight());



    }

    public void stop(View view) {
        System.out.println("*********  button.stop  *********");

//        thread.interrupt();

    }

    public void rect(View view) {
        System.out.println("*********  button.rect  *********");



        //渲染
        Paint p = new Paint();
        p.setColor(getResources().getColor(R.color.AliceBlue, null));

        Canvas canvas = surfaceHolder.lockCanvas();
        canvas.drawARGB(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
        canvas.drawRect(0, 0, 1700, 1700, p);
        surfaceHolder.unlockCanvasAndPost(canvas);




        //测试混合模式
//        Random random = new Random();
//        Paint p = new Paint();
//        p.setColor(getResources().getColor(R.color.AliceBlue, null));
//        Canvas canvas = surfaceHolder.lockCanvas(new Rect(0,0, 100, 100));
//        canvas.drawCircle(100f, 100f, 100f, p);
//        canvas.drawColor(getResources().getColor(R.color.MediumSeaGreen, null),
//                PorterDuff.Mode.DST);//使用DST模式
//        surfaceHolder.unlockCanvasAndPost(canvas);


        //填充颜色
//        Random random = new Random();
//        Canvas canvas = surfaceHolder.lockCanvas(new Rect(0,0, 100, 100));
//        canvas.drawARGB(random.nextInt(255),random.nextInt(255),random.nextInt(255),random.nextInt(255));
//        surfaceHolder.unlockCanvasAndPost(canvas);


        //测试固定buffer
//        Canvas canvas = surfaceHolder.lockCanvas(new Rect(0, 0, 800, 600));
//        Canvas canvas = surfaceHolder.lockCanvas();
//        Drawable d = getResources().getDrawable(R.drawable.center, null);
//        d.setBounds(200, 200, 600, 800);
//        d.draw(canvas);
//        surfaceHolder.unlockCanvasAndPost(canvas);





    }
}




