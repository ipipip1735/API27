package mine.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by Administrator on 2017/7/18.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

//    private SurfaceHolder mSurfaceHolder;
//    private Context mContext;

//    public float mx;
//    public float my;


    public MySurfaceView(Context context) {
        super(context);
        System.out.println("*********  " + getClass().getSimpleName() + ".Constructor1  *********");

        //        mSurfaceHolder = this.getHolder();
//        mSurfaceHolder.addCallback(this);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        System.out.println("*********  " + getClass().getSimpleName() + ".Constructor2  *********");

        //        mSurfaceHolder = getHolder();
//        mSurfaceHolder.addCallback(this);
//        this.mContext = context;

//        mx = 100f;
//        my = 100f;
//        setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                System.out.println(" on touch ");
//                mx = event.getX();
//                my = event.getY();
//
//                return true;
//            }
//        });
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        System.out.println("*********  " + getClass().getSimpleName() + ".constructor3  *********");

    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        System.out.println("*********  " + getClass().getSimpleName() + ".constructor4  *********");

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onMeasure  *********");

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        System.out.println("getMeasuredWidth is " + getMeasuredWidth());
        System.out.println("getMeasuredHeight is " + getMeasuredHeight());
    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        System.out.println("*********  " + getClass().getSimpleName() + ".surfaceCreated  *********");


//        final SurfaceHolder h = holder;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    Canvas canvas = null;
//                    ToolClass.sleep(1000l);
//                    System.out.println("--");
//                    Paint p = new Paint();
//                    canvas = mSurfaceHolder.lockCanvas();
//                    canvas.drawARGB(255,255, 0, 0);
//                    p.setColor(getContext().getColor(R.color.AliceBlue));
//                    canvas.drawCircle(100f, 100f, 100f, p);
//                    mSurfaceHolder.unlockCanvasAndPost(canvas);
//                }
//
//            }
//        }).start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int frmt, int w, int h) {
        System.out.println("*********  " + getClass().getSimpleName() + ".surfaceChanged  *********");

        System.out.println("holder is " + holder);
        System.out.println("frmt is " + frmt);
        System.out.println("h is " + h);
        System.out.println("w is " + w);



    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        System.out.println("*********  " + getClass().getSimpleName() + ".surfaceDestroyed  *********");
        System.out.println("holder is " + holder);
    }


}

