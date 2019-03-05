package mine.drags;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2018/8/2.
 */

public class VTwo extends android.support.v7.widget.AppCompatImageView {
    public VTwo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        System.out.println("+++  " + getClass().getSimpleName() + ".Constructor  +++");
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
                setImageResource(R.drawable.ic_launcher_background);
                break;
            case DragEvent.ACTION_DRAG_LOCATION:
                System.out.println(getClass().getSimpleName() + "'action is ACTION_DRAG_LOCATION");
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                System.out.println(getClass().getSimpleName() + "'action is ACTION_DRAG_EXITED");
                setImageResource(R.drawable.a);
                break;
            case DragEvent.ACTION_DROP:
                System.out.println(getClass().getSimpleName() + "'action is ACTION_DROP");
                setImageResource(R.drawable.a);
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                System.out.println(getClass().getSimpleName() + "'action is ACTION_DRAG_ENDED");
//                setImageResource(R.drawable.b);
                break;
            default:
                System.out.println("Default");
        }



//        return false;
        return true;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println("**********  " + getClass().getSimpleName() + ".dispatchTouchEvent  **********");

        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("**********  " + getClass().getSimpleName() + ".onTouchEvent  **********");
        System.out.println("action is " + event.actionToString(event.getAction()));

        return false;
//        return true;
    }
}
