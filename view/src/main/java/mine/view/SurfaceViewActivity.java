package mine.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.Random;

/**
 * Created by Administrator on 2017/8/27.
 */

public class SurfaceViewActivity extends AppCompatActivity {

    //    private SurfaceHolder mSurfaceHolder;
    private static final String TAG = "Svetlin SurfaceView";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_surface);
//        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.msfv);
//        surfaceView.getHolder().addCallback(this);

        MySurfaceView view = new MySurfaceView(this);
//        surfaceView.setZOrderOnTop(true);
        setContentView(view);
//        view.getHolder().addCallback(this);
    }




}




