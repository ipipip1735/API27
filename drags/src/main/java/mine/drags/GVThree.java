package mine.drags;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2018/8/2.
 */

public class GVThree extends RelativeLayout {


    public GVThree(Context context, AttributeSet attrs) {
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

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("**********  " + getClass().getSimpleName() + ".onTouchEvent  **********");
        System.out.println("action is " + event.actionToString(event.getAction()));

        return false;
//        return true;
//        return super.onTouchEvent(event);
    }

}
