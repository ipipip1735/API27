package mine.drags;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2018/8/2.
 */

public class GVTwo extends RelativeLayout {


    public GVTwo(Context context, AttributeSet attrs) {
        super(context, attrs);
        System.out.println("+++  " + getClass().getSimpleName() + ".Constructor1  +++");

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println("**********  " + getClass().getSimpleName() + ".dispatchTouchEvent  **********");
        System.out.println("action is " + ev.actionToString(ev.getAction()));

        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        System.out.println("**********  " + getClass().getSimpleName() + ".onInterceptTouchEvent  **********");

//        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
//            return true;
//        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("**********  " + getClass().getSimpleName() + ".onTouchEvent  **********");
        System.out.println("action is " + event.actionToString(event.getAction()));


//        return true;
        return super.onTouchEvent(event);
    }

}
