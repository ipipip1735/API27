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
        System.out.println("++++++++++  " + getClass().getSimpleName() + ".dispatchTouchEvent  ++++++++++");

        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                System.out.println("**********  " + getClass().getSimpleName() + ".onLongClick  **********");

                String[] mineType = {"aa", "bb"};
                ClipData clipData = new ClipData("ok", mineType, new ClipData.Item("cia"));
                DragShadowBuilder dragShadowBuilder = new DragShadowBuilder(v) {
                    @Override
                    public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
                        System.out.println("~~ onProvideShadowMetrics ~~");
//                        super.onProvideShadowMetrics(outShadowSize, outShadowTouchPoint);

                        outShadowSize.set(400, 500);
                        outShadowTouchPoint.set(0, 0);

                    }

                    @Override
                    public void onDrawShadow(Canvas canvas) {
                        System.out.println("~~ onDrawShadow ~~");

                        Paint paint = new Paint();
                        canvas.drawColor(getResources().getColor(R.color.MEDIUMPURPLE, null));

                        canvas.scale(1f, 0.5f);
                        canvas.rotate(30f);
                        paint.setColor(getResources().getColor(R.color.IVORY, null));
                        canvas.drawCircle(300, 200, 100, paint);

//                        canvas.save();


//                        canvas.scale(1f, 1f);
//                        canvas.rotate(30f);
//                        paint.setColor(getResources().getColor(R.color.PERU, null));
//                        canvas.drawCircle(200, 420, 100, paint);
//                        canvas.save();




                    }
                };

                startDragAndDrop(clipData, dragShadowBuilder, null, View.DRAG_FLAG_GLOBAL);
                return true;
            }
        });
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println("**********  " + getClass().getSimpleName() + ".dispatchTouchEvent  **********");

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
                System.out.println();
        }
        return super.onTouchEvent(event);
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

//        return false;
        return true;
    }
}
