package mine.nestedscrollview;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Administrator on 2021/7/4.
 */
public class ScrollViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        setContentView(R.layout.activity_scrollview);


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


        scroll();
//        other();


    }

    private void other() {
        ScrollView scrollView = findViewById(R.id.sv);

        //获取可以滚动总像素
//        System.out.println("scrollView.getMaxScrollAmount() = " + scrollView.getMaxScrollAmount());

        //设置地点边界后的色块颜色
//        scrollView.setTopEdgeEffectColor(Color.RED);
    }

    private void scroll() {
        ScrollView scrollView = findViewById(R.id.sv);

        //按箭头键滚动
//        scrollView.arrowScroll(View.FOCUS_UP);
//        scrollView.arrowScroll(View.FOCUS_DOWN);

        //滚动端点（最顶、底、左、右部）
//        scrollView.fullScroll(View.FOCUS_DOWN);

        //一次滚动一屏
//        scrollView.pageScroll(View.FOCUS_DOWN);

        //平滑滚动（带动画效果）
//        scrollView.smoothScrollBy(0,200);

        //屏幕顶部对齐子顶部，或是屏幕底部对齐子View底部
//        scrollView.scrollToDescendant(findViewById(R.id.tv1));


        //滚动监听器
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                System.out.println("~~ScrollViewActivity.onScrollChange~~");
                System.out.println("scrollX = " + scrollX + ", scrollY = " + scrollY + ", oldScrollX = " + oldScrollX + ", oldScrollY = " + oldScrollY + ", v = " + v);


            }
        });

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


    public void query(View view) {
        System.out.println("~~button.query~~");

    }

}
