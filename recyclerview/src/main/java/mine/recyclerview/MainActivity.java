package mine.recyclerview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.support.v7.widget.RecyclerView.HORIZONTAL;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_SETTLING;
import static android.support.v7.widget.RecyclerView.VERTICAL;
import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;

/**
 * Created by Administrator on 2019/3/26.
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    List<TextView> list = new ArrayList<>();
    View target;
    List<String> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_main);


        //线性布局
        linearLayoutManager = new LinearLayoutManager(this, VERTICAL, false) {
            @Override
            public void onDetachedFromWindow(RecyclerView view, RecyclerView.Recycler recycler) {
                System.out.println("~~onDetachedFromWindow~~");
                System.out.println("recycler.getScrapList() = " + recycler.getScrapList());
                super.onDetachedFromWindow(view, recycler);
            }
        };


        //网格布局
        gridLayoutManager = new GridLayoutManager(this, 4, VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                System.out.println("position % 4 = " + position % 4);
                return position % 4 == 0 ? 1 : 4;
            }
        });
        new Thread(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gridLayoutManager.setSpanCount(new Random().nextInt(5));
        }).start();


        dataset = new ArrayList<>(12);
        for (int i = 0; i < 100; i++) {
            dataset.add("item" + i);
        }
        adapter = new RVAdapter<>(dataset, this.list);
//        adapter = new MultipleRVAdapter(dataset, this.list);

        recyclerView = findViewById(R.id.rv);
//        recyclerView.setHasFixedSize(true);//使用固定尺寸
        recyclerView.setLayoutManager(linearLayoutManager);//绑定布局管理器
//        recyclerView.setLayoutManager(gridLayoutManager);//绑定布局管理器
        recyclerView.setAdapter(adapter);//绑定适配器

//        recyclerView.setItemViewCacheSize(3);//设置离屏缓存尺寸，默认尺寸为2
//        recyclerView.getRecycledViewPool().setMaxRecycledViews(0, 6);//设置缓存池尺寸，默认尺寸为5


//        bindListen(); //绑定各种监听器
//        bindAnimator(); //绑定动画
//        bindDecoration(); //绑定装饰器
//        bindDragDrop();//绑定侧滑
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


        //单个更新
        int postion = 1;
        dataset.add(postion, "insert-" + dataset.size());//插入到数据集
        adapter.notifyItemInserted(postion);//更新RecyclerView


        //局部更新
//        int start = 1, end = 3;
//        for (int i = start; i < end; i++) {
//            dataset.add(start, "insert-" + start);//插入到数据集
//        }
//        adapter.notifyItemRangeInserted(start, end - start); //更新适配器，刷新UI


        //全部更新
//        Random random = new Random();
//        for (int i = 0; i < 3; i++) {
//            int r = random.nextInt(10); //创建随机数
//            dataset.add(r, "insert-" + r);//随机插入到数据集
//        }
//        adapter.notifyDataSetChanged(); //更新适配器，刷新UI

    }


    public void del(View view) {
        System.out.println("~~button.del~~");

        //单个更新
        int postion = 1;
        target = recyclerView.getChildAt(postion);
        System.out.println("del'target is " + ((TextView) target).getText());
        dataset.remove(postion); //删除数据集
        adapter.notifyItemRemoved(postion); //更新适配器，刷新UI


        //局部更新
//        int start = 0, end = 2;
//        for (int i = start; i < end; i++) {
//            dataset.remove(i);//删除数据集
//        }
//        adapter.notifyItemRangeRemoved(start, end - start); //更新适配器，刷新UI


        //全部更新
//        Random random = new Random();
//        for (int i = 0; i < 3; i++) {
//            int r = random.nextInt(10); //创建随机数
//            dataset.remove(r);//随机删除数据集
//        }
//        adapter.notifyDataSetChanged(); //更新适配器，刷新UI
    }

    public void info(View view) {
        System.out.println("~~button.info~~");

        //RecyclerView信息
//        System.out.println("recyclerView.getChildCount is " + recyclerView.getChildCount());


        //查询Postion
        View v = recyclerView.getChildAt(0);
        System.out.println("getChildAdapterPosition is " + recyclerView.getChildAdapterPosition(v));
        System.out.println("getChildLayoutPosition is " + recyclerView.getChildLayoutPosition(v));


        //查找子View对应的ViewHolder
//        if (!this.list.isEmpty()) {
//            TextView textView = this.list.get(this.list.size() - 1);
//            RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(textView);
//            System.out.println(textView.getText() + "|" + holder);
//        }


        //打印 ViewHolder
//        for (int i = 0; i < recyclerView.getChildCount(); i++) {
//            RecyclerView.ViewHolder vh;
//            vh = recyclerView.findContainingViewHolder(recyclerView.getChildAt(i));
//            System.out.println(vh);

//            vh = recyclerView.findViewHolderForItemId(i);
//            System.out.println("LayoutPosition is " + vh); //获取Postion

//            vh = recyclerView.findViewHolderForLayoutPosition(i);
//            System.out.println("LayoutPosition is " + vh); //获取Postion

//            vh = recyclerView.findViewHolderForAdapterPosition(i);
//            System.out.println("AdapterPosition is " + vh); //获取Postion

//        }


        //适配器信息
//        System.out.println("adapter.getItemCount is " + adapter.getItemCount());
//        System.out.println(dataset);//打印数据集


        //布局信息
//        System.out.println("layoutManager.getChildCount is " + layoutManager.getChildCount());
//        System.out.println("layoutManager.getItemCount is " + layoutManager.getItemCount());
//        System.out.println("layoutManager.getHeight is " + layoutManager.getHeight());
//        System.out.println("layoutManager.getWidth is " + layoutManager.getWidth());

//        for (int i = 0; i < layoutManager.getChildCount(); i++) { //遍历可见子View，也就是遍历RecyclerView
//            TextView textView = (TextView) layoutManager.getChildAt(i);
//            System.out.println(textView.getText());
//
//            textView = (TextView) layoutManager.findViewByPosition(i);
//            System.out.println(textView.getText());
//        }


//        ViewInfo(); //获取测量值
    }

    public void query(View view) {
        System.out.println("~~button.query~~");

        //查询缓存池缓存个数
//        RecyclerView.RecycledViewPool pool = recyclerView.getRecycledViewPool();
//        System.out.println("getRecycledViewCount is " + pool.getRecycledViewCount(0));


        //取出所有缓存，即清空缓存池
//        while (pool.getRecycledViewCount(0) != 0) {
//            RecyclerView.ViewHolder holder = pool.getRecycledView(0);
//            System.out.println("holder is " + holder);
//            System.out.println("getRecycledViewCount is " + pool.getRecycledViewCount(0));
//        }


        //查询子View的ViewHolder以及它的Position
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            TextView target = (TextView) recyclerView.getChildAt(i);
            System.out.println(((TextView) target).getText() + "|" + recyclerView.getChildViewHolder(target));
            System.out.println("getChildLayoutPosition is " + recyclerView.getChildLayoutPosition(target));
            System.out.println("getChildAdapterPosition is " + recyclerView.getChildAdapterPosition(target));
        }

    }


    public void update(final View view) {
        System.out.println("~~button.update~~");

        //单个更新
//        int postion = 0;
//        dataset.set(postion, "xxxx"); //修改数据
//        adapter.notifyItemChanged(postion); //更新适配器，刷新UI

        //局部更新
//        int start = 0, end = 2;
//        for (int i = start; i < end; i++) {
//            dataset.set(i, "xxxx-" + i); //修改数据
//        }
//        adapter.notifyItemRangeChanged(start, end - start); //更新适配器，刷新UI

        //全部更新
//        int start = 0, end = 2;
//        for (int i = start; i < end; i++) {
//            dataset.set(i, "xxxx-" + i); //修改数据
//        }
//        adapter.notifyDataSetChanged(); //更新适配器，刷新UI


        move();//滚动
    }




    /*---------------bind------------------*/


    private void bindDecoration() {

        //方式一：使用系统默认
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, VERTICAL));


        //方式二：自定义
        RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
            int offset = 20;

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                System.out.println("~~onDraw~~");

                Random random = new Random();
                Paint paint = new Paint();
                int width = parent.getWidth();

                for (int i = 1; i < parent.getChildCount(); i++) { //遍历绘制
                    int left = parent.getChildAt(i).getLeft();
                    int top = parent.getChildAt(i).getTop() - offset;
                    paint.setColor(Color.BLUE);
//                    paint.setColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
                    c.drawLine(left, top, width, top, paint);
                }

            }

            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                System.out.println("~~onDrawOver~~");

                System.out.println(state);
                System.out.println("getItemCount is " + state.getItemCount());
                System.out.println("getChildCount is " + parent.getChildCount());
                System.out.println("getRemainingScrollHorizontal is " + state.getRemainingScrollHorizontal());
                System.out.println("getRemainingScrollVertical is " + state.getRemainingScrollVertical());
                System.out.println("getTargetScrollPosition is " + state.getTargetScrollPosition());

//                Paint paint = new Paint();
//                int width = parent.getWidth();
//                int height = parent.getHeight();
//                c.drawRect(100, 100, width/2, height/2, paint);


                Random random = new Random();
                Paint paint = new Paint();
                int width = parent.getWidth();

                for (int i = 0; i < parent.getChildCount(); i++) { //遍历绘制
                    int left = parent.getChildAt(i).getLeft();
                    int top = parent.getChildAt(i).getTop();
                    int right = parent.getChildAt(i).getRight();
                    int bottom = parent.getChildAt(i).getBottom();
                    System.out.println(parent.getChildAt(i));
//                    paint.setColor(Color.BLUE);
                    paint.setColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
                    c.drawRect(left + 15, top + 15, right - 15, bottom - 15, paint);
                }
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                System.out.println("~~getItemOffsets~~");
//                super.getItemOffsets(outRect, view, parent, state);

                int paddingLeft, paddingTop, paddingRight, paddingBottom;
                paddingLeft = 0;
                paddingTop = offset;
                paddingRight = 0;
                paddingBottom = offset;
                outRect.set(paddingLeft, paddingTop, paddingRight, paddingBottom);
            }
        };
        recyclerView.addItemDecoration(itemDecoration);
    }


    private void bindAnimator() {

//        RecyclerView.ItemAnimator animator = new ItemAnimator(); //实现RecyclerView.ItemAnimator抽象类
        RecyclerView.ItemAnimator animator = new SimpleItemAnimator(); //实现RecyclerView.SimpleItemAnimator抽象类


//        animator.isRunning(new RecyclerView.ItemAnimator.ItemAnimatorFinishedListener() { //所有Item动画完成时调用
//            @Override
//            public void onAnimationsFinished() {
//                System.out.println("~~onAnimationsssssssFinished~~");
//            }
//        });
        recyclerView.setItemAnimator(animator);

    }

    private void bindListen() {
        //子View点击监听器
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                System.out.println("~~onInterceptTouchEvent~~");
                System.out.println("e = " + e + ", rv = " + rv);

//                switch (e.getAction()) {
////                    case ACTION_DOWN:
////                        return true;
//                    case ACTION_MOVE:
//                        return true;
//                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                System.out.println("~~onTouchEvent~~");
                System.out.println("e = " + e + ", rv = " + rv);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                System.out.println("~~onRequestDisallowInterceptTouchEvent~~");
                System.out.println("disallowIntercept = " + disallowIntercept);
            }
        });


        //滚动监听器
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                System.out.println("~~onScrollStateChanged~~");
                switch (newState) {
                    case SCROLL_STATE_IDLE:
                        System.out.println("newState is SCROLL_STATE_IDLE");
                        break;
                    case SCROLL_STATE_DRAGGING:
                        System.out.println("newState is SCROLL_STATE_DRAGGING");
                        break;
                    case SCROLL_STATE_SETTLING:
                        System.out.println("newState is SCROLL_STATE_SETTLING");
                        break;
                    default:
                        System.out.println("newState is unknown");
                }
                System.out.println("recyclerView.canScrollVertically(1) = " + recyclerView.canScrollVertically(1));
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                System.out.println("~~onScrolled~~");
                System.out.println("dx is " + dx);
                System.out.println("dy is " + dy);
                //滚动监听器
                System.out.println("computeHorizontalScrollExtent() is " + recyclerView.computeHorizontalScrollExtent());
                System.out.println("computeHorizontalScrollOffset() is " + recyclerView.computeHorizontalScrollOffset());
                System.out.println("computeHorizontalScrollRange() is " + recyclerView.computeHorizontalScrollRange());
                System.out.println("computeVerticalScrollExtent() is " + recyclerView.computeVerticalScrollExtent());
                System.out.println("computeVerticalScrollOffset() is " + recyclerView.computeVerticalScrollOffset());
                System.out.println("computeVerticalScrollRange() is " + recyclerView.computeVerticalScrollRange());
            }
        });


    }


    private void bindDragDrop() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                System.out.println("~~getMovementFlags~~");

                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(dragFlags, swipeFlags);

            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                System.out.println("~~onMove~~");
                System.out.println("viewHolder.itemView is " + viewHolder.itemView);
                System.out.println("target.itemView is " + target.itemView); //将viewHolder移动到target上
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                System.out.println("~~onSwiped~~");
                System.out.println("viewHolder is " + viewHolder);
                System.out.println("direction is " + direction);

                adapter.notifyItemRemoved(viewHolder.getAdapterPosition()); //右滑，删除元素
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    /*---------------modify------------------*/
    private void move() {
        System.out.println("..move..");

        //使用RecyclerView滚动
//        linearLayoutManager.scrollToPosition(0);//以像素为单位滚动（recyclerView.scrollToPosition()就是间接调用此方法，且更安全）
//        linearLayoutManager.scrollToPositionWithOffset(10, 30);//以索引为单位滚动，可附加偏移量，正为左偏移，赋值为右偏移


        //使用RecyclerView滚动
//        recyclerView.scrollBy(500, 0);//以像素为单位滚动
//        recyclerView.scrollToPosition(25);//以索引为单位滚动
//        recyclerView.smoothScrollBy(0, 500);//以像素为单位滚动，并带动画效果
        recyclerView.smoothScrollToPosition(1);//以索引为单位滚动，并带动画效果

//        recyclerView.offsetChildrenHorizontal(150);
//        recyclerView.offsetChildrenVertical(150);


    }

    /*--------------info-------------------*/
    private void ViewInfo() {

        //获取子View尺寸（包含装饰器附加部分)
        linearLayoutManager.getDecoratedMeasuredHeight(linearLayoutManager.getChildAt(0));
    }
    /*---------------------------------*/


    /*---------------------------------*/
    /*---------------------------------*/
}
