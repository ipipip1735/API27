package mine.drags;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2018/8/2.
 */

public class GVOne extends RelativeLayout {


    public GVOne(Context context, AttributeSet attrs) {
        super(context, attrs);
        System.out.println("+++  " + getClass().getSimpleName() + ".Constructor  +++");
    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println("**********  " + getClass().getSimpleName() + ".dispatchTouchEvent  **********");
        System.out.println("action is " + ev.actionToString(ev.getAction()));

//        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
//            System.out.println("OOK-----");
//        }


//        return true;
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        System.out.println("**********  " + getClass().getSimpleName() + ".onInterceptTouchEvent  **********");

//        if(ev.getAction() == MotionEvent.ACTION_DOWN)
        if(ev.getAction() == MotionEvent.ACTION_MOVE)
        return true;
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("**********  " + getClass().getSimpleName() + ".onTouchEvent  **********");
        System.out.println("action is " + event.actionToString(event.getAction()));


        return true;
//        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDragEvent(DragEvent event) {
        System.out.println("**********  " + getClass().getSimpleName() + ".onDragEvent  **********");

        int action = event.getAction();
        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                System.out.println(getClass().getSimpleName() + "'action is ACTION_DRAG_STARTED");
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                System.out.println(getClass().getSimpleName() + "'action is ACTION_DRAG_ENTERED");
                break;
            case DragEvent.ACTION_DRAG_LOCATION:
                System.out.println(getClass().getSimpleName() + "'action is ACTION_DRAG_LOCATION");
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                System.out.println(getClass().getSimpleName() + "'action is ACTION_DRAG_EXITED");
                break;
            case DragEvent.ACTION_DROP:
                System.out.println(getClass().getSimpleName() + "'action is ACTION_DROP");
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                System.out.println(getClass().getSimpleName() + "'action is ACTION_DRAG_ENDED");
                break;
            default:
                System.out.println("Default");
        }

        return false;
//        return true;
    }
}
