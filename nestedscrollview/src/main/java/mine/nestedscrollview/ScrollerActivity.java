package mine.nestedscrollview;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.OverScroller;
import android.widget.Scroller;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Administrator on 2020/6/28.
 */
public class ScrollerActivity extends AppCompatActivity {

    OverScroller overScroller;
    Scroller scroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        setContentView(R.layout.activity_scroller);


        LinearLayout linearLayout = findViewById(R.id.ll);
        for (int i = 0; i < 100; i++) {
            TextView textView = new TextView(this);
            textView.setText("TV - " + i);
            linearLayout.addView(textView);
        }

        ListView listView = findViewById(R.id.lv);
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (int i = 0; i < 100; i++) {
            arrayAdapter.add("ListView TV - " + i);
        }
        listView.setAdapter(arrayAdapter);
        listView.setNestedScrollingEnabled(true);


        overScroller = new OverScroller(this);
        scroller = new Scroller(this);

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

        //使用Scroller
//        scroller.forceFinished(true);
//        scroller.startScroll(0, 0, 500, 500, 5000);

//        int start = 0, velocity = 20000, min = 0, max = 10000;
//        scroller.fling(start, start, velocity, velocity, min, max, min, max);
//        System.out.println("scroller.getFinalX()=" + scroller.getFinalX() + ", scroller.getFinalY()=" + scroller.getFinalY());
//        System.out.println("scroller.getDuration()=" + scroller.getDuration());


        //使用OverScroller
        int start = -2000, velocity = 2000, min = 0, max = 10000;
        overScroller.springBack(start, start, min, min, max, max);
        System.out.println("overScroller.getFinalX()=" + overScroller.getFinalX());
        System.out.println("overScroller.getCurrVelocity()=" + overScroller.getCurrVelocity());

    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

        overScroller.abortAnimation();

    }

    public void info(View view) {
        System.out.println("~~button.info~~");

//        if (scroller.computeScrollOffset()) {
//            System.out.println("scroller.getStartX()=" + scroller.getStartX() + ", scroller.getStartY()=" + scroller.getStartY());
//            System.out.println("scroller.getCurrX()=" + scroller.getCurrX() + ", scroller.getCurrY()=" + scroller.getCurrY());
//            System.out.println("scroller.getFinalX()=" + scroller.getFinalX() + ", scroller.getFinalY()=" + scroller.getFinalY());
//            System.out.println("scroller.getCurrVelocity()=" + scroller.getCurrVelocity());
//            System.out.println("scroller.isFinished()=" + scroller.isFinished());
//            System.out.println("scroller.timePassed()=" + scroller.timePassed());
//            System.out.println("scroller.getDuration()=" + scroller.getDuration());
//        }


        if (overScroller.computeScrollOffset()) {
            System.out.println("overScroller.getStartX()=" + overScroller.getStartX() + ", overScroller.getStartY()=" + overScroller.getStartY());
            System.out.println("overScroller.getFinalX()=" + overScroller.getFinalX() + ", overScroller.getFinalY()=" + overScroller.getFinalY());
            System.out.println("overScroller.getCurrX()=" + overScroller.getCurrX() + ", overScroller.getCurrY()=" + overScroller.getCurrY());
            System.out.println("overScroller.getCurrVelocity()=" + overScroller.getCurrVelocity());
            System.out.println("overScroller.isFinished()=" + overScroller.isFinished());
        }

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


    public void query(View view) {
        System.out.println("~~button.query~~");

    }

}
