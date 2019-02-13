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

public class MySurfaceView extends SurfaceView {

    private SurfaceHolder mSurfaceHolder;
    private Context mContext;

//    public float mx;
//    public float my;


    public MySurfaceView(Context context) {
        super(context);
        System.out.println("++++++++++  MySurfaceView.Constructor0  +++++++++++");
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        System.out.println("*********  " + getClass().getSimpleName() + ".Constructor1  *********");

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

        System.out.println("*********  " + getClass().getSimpleName() + ".Constructor2  *********");

    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        System.out.println("*********  " + getClass().getSimpleName() + ".Constructor3  *********");

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onMeasure  *********");

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        System.out.println("getMeasuredWidth is "  + getMeasuredWidth());
        System.out.println("getMeasuredHeight is "  + getMeasuredHeight());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onLayout  *********");

        super.onLayout(changed, left, top, right, bottom);

        System.out.println("changed is "  + changed);
        System.out.println("left is "  + left);
        System.out.println("top is "  + top);
        System.out.println("right is "  + right);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onDraw  *********");

//        super.onDraw(canvas);
//        canvas = mSurfaceHolder.lockCanvas();
//        System.out.println("my canvas is " + canvas);
//        System.out.println(mx);
//        System.out.println(my);
//        Paint p = new Paint();
//        p.setColor(mContext.getColor(R.color.AliceBlue));
//        canvas.drawCircle(mx, my, 100f, p);
//        mSurfaceHolder.unlockCanvasAndPost(canvas);
//        postInvalidate();
        ToolClass.showThread();


    }

}

