package mine.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static android.graphics.Path.Direction.CCW;

/**
 * Created by Administrator on 2019/3/7.
 */

public class CanvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        super.onCreate(savedInstanceState);

        SurfaceView surfaceView = new SurfaceView(this);
        setContentView(surfaceView);

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Canvas canvas = holder.lockCanvas();
                Paint paint = new Paint();
                paint.setColor(getColor(R.color.red));
                paint.setStyle(Paint.Style.STROKE);

                Path path = new Path();
//                path.addRect(150, 150, 500, 300, CCW);//矩形路径
//                path.addArc(100, 100, 500, 300, 0f, -90f);//绘制弧

                path.arcTo(new RectF(100, 300, 500, 600), 0f, 90, false);

                path.lineTo(900f, 980f);

                canvas.drawPath(path, paint);


                holder.unlockCanvasAndPost(canvas);

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });


    }
}




