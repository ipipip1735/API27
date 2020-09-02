package mine.view;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Administrator on 2020/9/1.
 */

public class ViewTreeObserverActivity extends AppCompatActivity {

    int id;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("*******" + getClass().getSimpleName() + ".onCreate*******");

        setContentView(R.layout.activity_vew_tree_observer);


        FrameLayout frameLayout = new FrameLayout(this) {
            @Override
            protected void onDraw(Canvas canvas) {
                System.out.println("~~frameLayout.onDraw~~");
                super.onDraw(canvas);
            }

            @Override
            protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
                System.out.println("~~frameLayout.onLayout~~");
                super.onLayout(changed, left, top, right, bottom);
            }


            @Override
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                System.out.println("~~frameLayout.onMeasure~~");
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        };
        frameLayout.setId(id = View.generateViewId());
        frameLayout.setBackgroundColor(getResources().getColor(R.color.DarkCyan, null));
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(850, 550));
        frameLayout.setX(100);
        frameLayout.setY(250);

        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.top);
        viewGroup.addView(frameLayout);

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


        ((ViewGroup)findViewById(R.id.top)).removeView(findViewById(id));


    }


    public void stop(final View view) {
        System.out.println("~~stop~~");

        final FrameLayout frameLayout = findViewById(id);


        //OnPreDrawListener监听器
//        frameLayout.getViewTreeObserver()
//                .addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                System.out.println("~~frameLayoutTVO.onPreDraw~~");
//                frameLayout.getViewTreeObserver().removeOnPreDrawListener(this);
//
//                System.out.println(findViewById(id).getX());
//
//                return false;
//            }
//        });
//
//        System.out.println(frameLayout.getX());
//        frameLayout.setX(350);


        //addOnWindowAttachListener监听器
//        frameLayout.getViewTreeObserver()
//                .addOnWindowAttachListener(new ViewTreeObserver.OnWindowAttachListener() {
//                    @Override
//                    public void onWindowAttached() {
//                        System.out.println("~~frameLayoutTVO.onWindowAttached~~");
//                    }
//
//                    @Override
//                    public void onWindowDetached() {
//                        System.out.println("~~frameLayoutTVO.onWindowDetached~~");
//
//                    }
//                });




        //addOnScrollChangedListener监听器
        ListView listView = findViewById(R.id.lv);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (int i = 0; i < 180; i++) {
            arrayAdapter.add("ooo" + i);
        }
        listView.setAdapter(arrayAdapter);
        frameLayout.getViewTreeObserver()
                .addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        System.out.println("~~frameLayoutTVO.onScrollChanged~~");
                    }
                });




    }
}




