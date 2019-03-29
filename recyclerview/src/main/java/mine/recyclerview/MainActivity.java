package mine.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_SETTLING;

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


//        addListen();

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

        //插入元素
//        int postion = 0;
//        dataset.add(postion, "item_" + dataset.size());//插入到数据集
//        adapter.notifyItemInserted(postion);//更新RecyclerView
//
//
//        System.out.println(dataset);//打印数据集


        int i = 5;
//        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            RecyclerView.ViewHolder vh;
//            vh = recyclerView.findContainingViewHolder(recyclerView.getChildAt(i));
//            System.out.println(vh);

//            vh = recyclerView.findViewHolderForItemId(i);
//            System.out.println("LayoutPosition is " + vh);

//            vh = recyclerView.findViewHolderForLayoutPosition(i);
//            System.out.println("LayoutPosition is " + vh);

            vh = recyclerView.findViewHolderForAdapterPosition(i);
            System.out.println("AdapterPosition is " + vh);
//        }

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
//        System.out.println("adapter.getItemCount is " + adapter.getItemCount());

        //布局信息
//        System.out.println("layoutManager.getChildCount is " + layoutManager.getChildCount());
//        System.out.println("layoutManager.getItemCount is " + layoutManager.getItemCount());
//        System.out.println("layoutManager.getHeight is " + layoutManager.getHeight());
//        System.out.println("layoutManager.getWidth is " + layoutManager.getWidth());


//        ViewInfo(); //获取测量值


//        recyclerView.getAdapterPositionFor();


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
    private void addListen() {


        //拦截器
//        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                System.out.println("~~onInterceptTouchEvent~~");
//                return true;
//            }
//
//            @Override
//            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//                System.out.println("~~onTouchEvent~~");
//
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//                System.out.println("~~onRequestDisallowInterceptTouchEvent~~");
//
//            }
//        });


//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                System.out.println("~~onScrollStateChanged~~");
//                switch (newState) {
//                    case SCROLL_STATE_IDLE:
//                        System.out.println("newState is SCROLL_STATE_IDLE");
//                        break;
//                    case SCROLL_STATE_DRAGGING:
//                        System.out.println("newState is SCROLL_STATE_DRAGGING");
//                        break;
//                    case SCROLL_STATE_SETTLING:
//                        System.out.println("newState is SCROLL_STATE_SETTLING");
//                        break;
//                    default:
//                        System.out.println("newState is unknown");
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                System.out.println("~~onScrolled~~");
//                System.out.println("dx is " + dx);
//                System.out.println("dy is " + dy);
//                //滚动监听器
//                System.out.println("computeHorizontalScrollExtent() is " + recyclerView.computeHorizontalScrollExtent());
//                System.out.println("computeHorizontalScrollOffset() is " + recyclerView.computeHorizontalScrollOffset());
//                System.out.println("computeHorizontalScrollRange() is " + recyclerView.computeHorizontalScrollRange());
//                System.out.println("computeVerticalScrollExtent() is " + recyclerView.computeVerticalScrollExtent());
//                System.out.println("computeVerticalScrollOffset() is " + recyclerView.computeVerticalScrollOffset());
//                System.out.println("computeVerticalScrollRange() is " + recyclerView.computeVerticalScrollRange());
//            }
//        });


    }

    /*---------------modify------------------*/
    private void move() {

//        layoutManager.moveView(0,6);

        //滚动
//        layoutManager.scrollToPosition(0);
//        recyclerView.smoothScrollToPosition(0);
//        layoutManager.scrollToPositionWithOffset(0, 2);

        //平滑滚动，带动画效果
//        recyclerView.scrollBy(0, 500);
//        recyclerView.scrollToPosition(25);
//        recyclerView.smoothScrollBy(0, 500);

//        recyclerView.offsetChildrenHorizontal(150);
        recyclerView.offsetChildrenVertical(150);


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
