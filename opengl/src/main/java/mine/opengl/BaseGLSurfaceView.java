package mine.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2018/8/18.
 */
public class BaseGLSurfaceView extends GLSurfaceView {

    BaseRenderer mRender;
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float mPreviousX;
    private float mPreviousY;

    public BaseGLSurfaceView(Context context) {
        super(context);
        System.out.println("++++  " + getClass().getSimpleName() + "  ++++");

        setEGLContextClientVersion(3);
        mRender = new BaseRenderer(context);
        setRenderer(mRender);
//        setRenderMode(RENDERMODE_CONTINUOUSLY);
        setRenderMode(RENDERMODE_WHEN_DIRTY);

    }

    @Override
    public void onPause() {
        System.out.println("*****  " + getClass().getSimpleName() + ".onPause  *****");

        super.onPause();
    }

    @Override
    public void onResume() {
        System.out.println("*****  " + getClass().getSimpleName() + ".onResume  *****");

        super.onResume();
    }

    @Override
    protected void onAttachedToWindow() {
        System.out.println("*****  " + getClass().getSimpleName() + ".onAttachedToWindow  *****");

        super.onAttachedToWindow();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("*****  " + getClass().getSimpleName() + ".onTouchEvent  *****");
        float x = event.getX();
        float y = event.getY();
        System.out.println("x is " + x);
        System.out.println("y is " + y);

        switch (event.getAction()) {


            case MotionEvent.ACTION_DOWN:
                System.out.println("down");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("up");
                break;
            case MotionEvent.ACTION_MOVE:

                float dx = x - mPreviousX;
                float dy = y - mPreviousY;

                // reverse direction of rotation above the mid-line
                if (y > getHeight() / 2) {
                    dx = dx * -1 ;
                }

                // reverse direction of rotation to left of the mid-line
                if (x < getWidth() / 2) {
                    dy = dy * -1 ;
                }

                mRender.setAngle(
                        mRender.getAngle() +
                                ((dx + dy) * TOUCH_SCALE_FACTOR));
                requestRender();
        }

        mPreviousX = x;
        mPreviousY = y;
        return true;
    }

    @Override
    protected void onDetachedFromWindow() {
        System.out.println("*****  " + getClass().getSimpleName() + ".onDetachedFromWindow  *****");

        super.onDetachedFromWindow();
    }
}
