package mine.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/3/26.
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager layoutManager;
    View target;

    List<String> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_main);

        layoutManager = new LinearLayoutManager(this);

        dataset = new ArrayList<>(8);
        for (int i = 0; i < 10; i++) {
            dataset.add("item" + i);
        }
        adapter = new RVAdapter<>(dataset);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

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


    public void add(View view) {
        System.out.println("~~button.add~~");

//        dataset.add("item_" + dataset.size());
//        recyclerView.getAdapter().notifyItemInserted(0);


//        layoutManager.attachView();

    }


    public void del(View view) {
        System.out.println("~~button.del~~");

//        recyclerView.removeView(target);
//        recyclerView.removeViewAt(textView);


        layoutManager.removeViewAt(1);
        layoutManager.removeAllViews();
        layoutManager.requestLayout();



    }

    public void info(View view) {
        System.out.println("~~button.info~~");

//        ViewGroup viewGroup = recyclerView;
//        System.out.println("recyclerView.getChildCount is " + viewGroup.getChildCount());


        //适配器信息
        System.out.println("adapter.getItemCount is " + adapter.getItemCount());

        //布局信息
        System.out.println("layoutManager.getChildCount is " + layoutManager.getChildCount());
        System.out.println("layoutManager.getItemCount is " + layoutManager.getItemCount());
        System.out.println("layoutManager.getHeight is " + layoutManager.getHeight());
        System.out.println("layoutManager.getWidth is " + layoutManager.getWidth());



//        ViewInfo(); //获取测量值


//        for ()
////        System.out.println("adapter.getItemCount() is " + adapter.get);
//        System.out.println("adapter.getItemCount() is " + adapter.getItemCount());
//        System.out.println("adapter.getItemCount() is " + adapter.getItemCount());
//        System.out.println("adapter.getItemCount() is " + adapter.getItemCount());



    }

    public void find(View view) {
        System.out.println("~~button.find~~");

        //遍历可见子View，也就是遍历RecyclerView
//        for (int i = 0; i < layoutManager.getChildCount(); i++) {
//            TextView textView = (TextView) layoutManager.getChildAt(i);
//            System.out.println(textView.getText());
//
//            textView = (TextView) layoutManager.findViewByPosition(i);
//            System.out.println(textView.getText());
//        }


        //遍历所有子View
//        for (int i = 0; i < recyclerView.getChildLayoutPosition(); i++) {
//
//        }

//        View v = recyclerView.getLayoutManager().findViewByPosition(1);
//        System.out.println(v);


    }

    public void query(View view) {
        System.out.println("~~button.query~~");

    }


    public void modify(View view) {
        System.out.println("~~button.modify~~");

//        modifyLayout(); //修改RecyclerView布局，迫使LayoutManager增加子View
        move();
    }


    /*---------------modify------------------*/
    private void move() {

//        layoutManager.moveView(0,6);
//        layoutManager.scrollToPosition(0);
//        recyclerView.smoothScrollToPosition(0);
//        layoutManager.scrollToPositionWithOffset(0, 2);


    }

    private void modifyLayout() {

        ViewGroup.LayoutParams layoutParams = recyclerView.getLayoutParams();
        layoutParams.height += 150f;
        recyclerView.setLayoutParams(layoutParams);

    }

    /*--------------info-------------------*/
    private void ViewInfo() {

        //获取子View尺寸（包含装饰器附加部分)
        layoutManager.getDecoratedMeasuredHeight(layoutManager.getChildAt(0));
        layoutManager.getDecoratedMeasuredHeight(layoutManager.getChildAt(0));

    }
    /*---------------------------------*/
    /*---------------------------------*/
    /*---------------------------------*/
}
