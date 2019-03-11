package mine.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
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

import static android.graphics.PixelFormat.RGBA_8888;
import static android.graphics.PixelFormat.TRANSPARENT;

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
//        surfaceHolder.setFormat(TRANSPARENT);
        surfaceHolder.setFormat(RGBA_8888);
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
//        MySurfaceView tween = new MySurfaceView(this);
//        setContentView(tween);
//        surfaceView.setZOrderOnTop(true);
//        tween.getHolder().addCallback(this);

    }


    public void start(View view) {
        System.out.println("*********  button.start  *********");

//        thread.start();//启动线程，每秒绘制一帧


//        getRegion();//测试区域对象

//        gatherTransparent();//获取透明区域，并绘制


    }

    private void gatherTransparent() {
        Region region = new Region(0, 0, 1080, 1300);
        boolean b = surfaceView.gatherTransparentRegion(region); // 获取透明区域

        System.out.println("b is " + b);
        System.out.println("region is " + region);

        System.out.println("getX is " + surfaceView.getX());
        System.out.println("getY is " + surfaceView.getY());
        System.out.println("getWidth is " + surfaceView.getWidth());
        System.out.println("getHeight is " + surfaceView.getHeight());


        Paint p = new Paint();
        p.setColor(getResources().getColor(R.color.maroon, null));

        Canvas canvas = surfaceHolder.lockCanvas();
        canvas.drawPath(region.getBoundaryPath(), p);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    private void getRegion() {

        boolean b;
        Region region1 = new Region(0, 0, 100, 100);
        Region region2 = new Region(50, 10, 850, 400);
        System.out.println(region1);
        System.out.println(region2);


//       b = region1.op(region2, Region.Op.DIFFERENCE);
//        System.out.println("DIFFERENCE is " + b);
//        System.out.println(region1);
//        System.out.println(region2);
//
//
//       b = region1.op(region2, Region.Op.INTERSECT);
//        System.out.println("INTERSECT is " + b);
//        System.out.println(region1);
//        System.out.println(region2);
//
//
//       b = region1.op(region2, Region.Op.REPLACE);
//        System.out.println("REPLACE is " + b);
//        System.out.println(region1);
//        System.out.println(region2);
//
//
//       b = region1.op(region2, Region.Op.REVERSE_DIFFERENCE);
//        System.out.println("REVERSE_DIFFERENCE is " + b);
//        System.out.println(region1);
//        System.out.println(region2);


        b = region1.op(region2, Region.Op.UNION);
        System.out.println("UNION is " + b);
        System.out.println(region1);
        System.out.println(region2);

//       b = region1.op(region2, Region.Op.XOR);
//        System.out.println("XOR is " + b);
//        System.out.println(region1);
//        System.out.println(region2);



        Paint p = new Paint();
        p.setColor(getResources().getColor(R.color.maroon, null));

        Canvas canvas = surfaceHolder.lockCanvas();
        canvas.drawPath(region1.getBoundaryPath(), p);
        surfaceHolder.unlockCanvasAndPost(canvas);

    }

    public void stop(View view) {
        System.out.println("*********  button.stop  *********");

//        thread.interrupt();//终止线程，不要再每秒绘制Canvas，让Canvas显示最后一帧


        surfaceHolder.setFixedSize(1080, 1333); //使用固定尺寸Buffer
    }

    public void rect(View view) {
        System.out.println("*********  button.rect  *********");


        //渲染
        Paint p = new Paint();
        p.setColor(getResources().getColor(R.color.AliceBlue, null));
        Canvas canvas = surfaceHolder.lockCanvas();

        canvas.drawARGB(random.nextInt(255), //填充颜色
                random.nextInt(255),
                random.nextInt(255),
                random.nextInt(255));
        canvas.drawRect(0, 0, 1700, 1700, p);
//        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR); //填充为透明背景

        canvas.drawRect(0, 0, 170, 170, p);
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




