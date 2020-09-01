package mine.view;

import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2020/8/31.
 */

public class OverlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("*******" + getClass().getSimpleName() + ".onCreate*******");

        setContentView(R.layout.activity_overlay);


    }

    @Override
    protected void onNewIntent(Intent intent) {
        System.out.println("*******" + getClass().getSimpleName() + ".onNewIntent *******");
        super.onNewIntent(intent);
    }


    @Override
    protected void onStart() {
        System.out.println("*******" + getClass().getSimpleName() + ".onStart*******");
        super.onStart();

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("*******" + getClass().getSimpleName() + ".onRestoreInstanceState*******");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        System.out.println("*******" + getClass().getSimpleName() + ".onRestart*******");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        System.out.println("*******" + getClass().getSimpleName() + ".onResume*******");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("*******" + getClass().getSimpleName() + ".onPause*******");
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        System.out.println("*******" + getClass().getSimpleName() + ".onBackPressed*******");
        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("*******" + getClass().getSimpleName() + ".onStop*******");
        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("*******" + getClass().getSimpleName() + ".onSaveInstanceState *******");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("*******" + getClass().getSimpleName() + ".onDestroy*******");
        super.onDestroy();
    }


    public void start(View view) {
        System.out.println("~~start~~");

        Drawable drawable = new Drawable() {
            @Override
            public void draw(@NonNull Canvas canvas) {
                System.out.println("~~" + getClass().getSimpleName() + ".draw~~");

                Paint paint = new Paint();
                paint.setColor(getResources().getColor(R.color.Orchid, null));

                canvas.drawCircle(-10, -10, 100, paint);

            }

            @Override
            public void setAlpha(int alpha) {
                System.out.println("~~" + getClass().getSimpleName() + ".setAlpha~~");
            }

            @Override
            public void setColorFilter(@Nullable ColorFilter colorFilter) {
                System.out.println("~~" + getClass().getSimpleName() + ".setColorFilter~~");
            }

            @Override
            public int getOpacity() {
                System.out.println("~~" + getClass().getSimpleName() + ".getOpacity~~");
                return PixelFormat.OPAQUE;
            }
        };


        FrameLayout frameLayout = findViewById(R.id.fl);
//        frameLayout.setForeground(drawable);
//        frameLayout.setClipChildren(false);
        frameLayout.getOverlay().add(drawable);



    }


    public void stop(View view) {
        System.out.println("~~stop~~");

    }
}




