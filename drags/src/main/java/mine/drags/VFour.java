package mine.drags;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2018/8/2.
 */

public class VFour extends android.support.v7.widget.AppCompatImageView {
    public VFour(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        System.out.println("++++++++++  " + getClass().getSimpleName() + "  ++++++++++");

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println("**********  " + getClass().getSimpleName() + ".dispatchTouchEvent  **********");

        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("**********  " + getClass().getSimpleName() + ".onTouchEvent  **********");
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                System.out.println(getClass().getSimpleName() + "'action is ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println(getClass().getSimpleName() + "'action is ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println(getClass().getSimpleName() + "'action is ACTION_MOVE");
                break;
            default:
                System.out.println("Default");
        }
        return false;
//        return true;
    }
}
