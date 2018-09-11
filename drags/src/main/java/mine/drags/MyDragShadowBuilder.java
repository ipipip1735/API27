package mine.drags;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by Administrator on 2016/8/11.
 */
public class MyDragShadowBuilder extends View.DragShadowBuilder {

    private static Drawable shadow;

    public MyDragShadowBuilder(View view) {
        super(view);
        System.out.println(" -------  MyDragShadowBuilder  ------- ");
        shadow = new ColorDrawable(Color.LTGRAY);
    }


    @Override
    public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
        System.out.println("*****  MyDragShadowBuilder.onProvideShadowMetrics  ****");
        shadowSize.set(500, 500);
        shadowTouchPoint.set(0, 0);
//        super.onProvideShadowMetrics(shadowSize, shadowTouchPoint);
    }

//    @Override
//    public void onDrawShadow(Canvas canvas) {
//        System.out.println("*****  MyDragShadowBuilder.onDrawShadow  ****");
//
////        canvas.drawColor(Color.BLACK);
//        super.onDrawShadow(canvas);
//    }


}