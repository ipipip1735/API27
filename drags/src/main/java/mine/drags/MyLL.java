package mine.drags;

import android.content.Context;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2016/8/7.
 */
public class MyLL extends LinearLayout {
    public MyLL(Context context) {
        super(context);
        System.out.println("--------  MyLL Constructor  --------");
    }

    public MyLL(Context context, AttributeSet attrs) {
        super(context, attrs);
        System.out.println("--------  MyLL Constructor  --------");

    }


//    @Override
//    public boolean dispatchDragEvent(DragEvent event) {
//        System.out.println("*****  myll dispatchTouchEvent  *******");
//        System.out.println("`````````````  myLL action is " + event.getAction() + "  `````````````");
//        return super.dispatchDragEvent(event);
//    }


//    @Override
//    public boolean onDragEvent(DragEvent event) {
//        System.out.println("*****  myll onDrag  *******");
//        System.out.println("`````````````  myLL action is " + event.getAction() + "  `````````````");
//
//
//        final int action = event.getAction();
//        System.out.println(action);
//        // Handles each of the expected events
//        switch(action) {
//
//            case DragEvent.ACTION_DRAG_STARTED:
//                System.out.println("---->>  DRAG_started  <<----");
//                return true;
//
//            case DragEvent.ACTION_DRAG_ENTERED:
//                System.out.println("---->>  DRAG_entered  <<----");
//                return true;
//
//            case DragEvent.ACTION_DRAG_LOCATION:
//                System.out.println("---->>  DRAG_location  <<----");
//                return true;
//
//            case DragEvent.ACTION_DRAG_EXITED:
//                System.out.println("---->>  DRAG_exited  <<----");
//                return true;
//
//            case DragEvent.ACTION_DROP:
//                System.out.println("---->>  DRAG_drop  <<----");
//                return true;
//
//            case DragEvent.ACTION_DRAG_ENDED:
//                System.out.println("---->>  DRAG_ended  <<----");
//                return true;
//
//            default:
//                System.out.println("---->>  DRAG_default  <<----");
//                break;
//        }
//
//        return true;
//    }


//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        System.out.println("*****  myll.onInterceptTouchEvent  *******");
//            System.out.println(ev.getAction());
//        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
//            return true;
//        }
//        return super.onInterceptTouchEvent(ev);
////        return readMotionAction(ev.getAction());
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("*****  myll.onTouchEvent  *******");
        System.out.println(event);
//        System.out.println(event.getAction());
//        System.out.println(event.getActionMasked());
        return readMotionAction(event.getAction());
//        return super.onTouchEvent(event);
    }


    private boolean readMotionAction(int action) {
        switch (action) {

            case MotionEvent.ACTION_DOWN:
                System.out.println("---->>  ACTION_DOWN  <<----");
                return true;
            case MotionEvent.ACTION_MOVE:
                System.out.println("---->>  ACTION_MOVE  <<----");
                return true;
            case MotionEvent.ACTION_UP:
                System.out.println("---->>  ACTION_UP  <<----");
                return true;
            default:
                System.out.println("---->>  DRAG_default  <<----");
                break;
        }
        return false;
    }


}
