package mine.drags;

import android.content.ClipData;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.DragEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/5/4.
 */
public class DragActivity extends AppCompatActivity {

    private AppCompatImageView one;
    private AppCompatImageView two;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("**********  Example  onCreate  ***********");

//        setContentView(R.layout.activity_drag_event);
        setContentView(R.layout.activity_touch_event);


        this.one = (AppCompatImageView) findViewById(R.id.VOne);
        this.two = (AppCompatImageView) findViewById(R.id.VTWO);


        //长按监听器
        one.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                System.out.println("**********  " + getClass().getSimpleName() + ".onLongClick  **********");

                String[] mineType = {"aa", "bb"};
                ClipData clipData = new ClipData("ok", mineType, new ClipData.Item("cia"));
                View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v) {
                    @Override
                    public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
                        System.out.println("~~ onProvideShadowMetrics ~~");
                        super.onProvideShadowMetrics(outShadowSize, outShadowTouchPoint);

//                        outShadowSize.set(400, 500);
//                        outShadowTouchPoint.set(0, 0);

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

                v.startDragAndDrop(null, dragShadowBuilder, null, 0);
//                v.startDragAndDrop(clipData, dragShadowBuilder, null, View.DRAG_FLAG_GLOBAL);
                return true;
            }
        });


        View pv = (View) two.getParent();
        pv.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                System.out.println("~~PV.onDrag~~");

                int action = event.getAction();
                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        System.out.println("action is ACTION_DRAG_STARTED");
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        System.out.println("action is ACTION_DRAG_ENTERED");
                        break;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        System.out.println("action is ACTION_DRAG_LOCATION");
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        System.out.println("action is ACTION_DRAG_EXITED");
                        break;
                    case DragEvent.ACTION_DROP:
                        System.out.println("action is ACTION_DROP");
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        System.out.println("action is ACTION_DRAG_ENDED");
                        break;
                    default:
                        System.out.println("Default");
                }

//                return false;
                return true;
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestoreInstanceState  *********");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestart  *********");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("*********  " + getClass().getSimpleName() + ".onResume  *********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("*********  " + getClass().getSimpleName() + ".onBackPressed  *********");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onSaveInstanceState  *********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");
    }


    public void start(View view) {
        System.out.println("~~button.start~~");

    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");

    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void del(View view) {
        System.out.println("~~button.del~~");

    }


}


