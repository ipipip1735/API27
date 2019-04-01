package mine.recyclerview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.TextView;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        for (int i = 0; i < 20; i++) {
            dataset.add("item" + i);
        }
        adapter = new RVAdapter<>(dataset);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


//        bindListen(); //绑定各种监听器
        bindAnimator(); //绑定动画

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
//        int postion = 0;
//        dataset.add(postion, "insert-" + dataset.size());//插入到数据集
//        adapter.notifyItemInserted(postion);//更新RecyclerView


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
        int postion = 5;

        target = recyclerView.getChildAt(postion);
        System.out.println("target is " + ((TextView) target).getText());

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


        //获取 Postion
//        View v = recyclerView.getChildAt(0);
//        System.out.println("getChildAdapterPosition is " + recyclerView.getChildAdapterPosition(v));
//        System.out.println("getChildLayoutPosition is " + recyclerView.getChildLayoutPosition(v));


        System.out.println(recyclerView.getChildViewHolder(target));
        System.out.println(recyclerView.getChildLayoutPosition(target));
        System.out.println(recyclerView.getChildAdapterPosition(target));


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
    }


    public void update(final View view) {
        System.out.println("~~button.update~~");



//        final View v = recyclerView.getChildAt(0);
//        final ViewPropertyAnimator animation = v.animate();
//        animation.setDuration(5000L).alpha(0).setListener(
//                new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationStart(Animator animator) {
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animator) {
//                        animation.setListener(null);
//                        v.setAlpha(1);
//                    }
//                }).start();



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

    }














    /*---------------add------------------*/

    private void bindAnimator() {

        RecyclerView.ItemAnimator animator = new RecyclerView.ItemAnimator() {
            View target;
            Map<RecyclerView.ViewHolder, int[]> map = new HashMap<>();

            @Override
            public boolean animateDisappearance(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo preLayoutInfo, @Nullable ItemHolderInfo postLayoutInfo) {
                System.out.println("-->animateDisappearance<--");
                System.out.println("viewHolder is " + viewHolder);

                //获取信息
//                if (preLayoutInfo != null) {
//                    System.out.println("preLayoutInfo.left is " + preLayoutInfo.left);
//                    System.out.println("preLayoutInfo.top is " + preLayoutInfo.top);
//                    System.out.println("preLayoutInfo.right is " + preLayoutInfo.right);
//                    System.out.println("preLayoutInfo.bottom is " + preLayoutInfo.bottom);
//                }else{
//                    System.out.println("preLayoutInfo is " + preLayoutInfo);
//                }
//                if (postLayoutInfo != null) {
//                    System.out.println("postLayoutInfo.left is " + postLayoutInfo.left);
//                    System.out.println("postLayoutInfo.top is " + postLayoutInfo.top);
//                    System.out.println("postLayoutInfo.right is " + postLayoutInfo.right);
//                    System.out.println("postLayoutInfo.bottom is " + postLayoutInfo.bottom);
//                }else{
//                    System.out.println("postLayoutInfo is " + postLayoutInfo);
//                }



                int[] ints = new int[2];
                ints[0] = preLayoutInfo.left;
                ints[1] = preLayoutInfo.left + 150;








//                System.out.println("viewHolder.isRemoved() is " + viewHolder.isRemoved());

//                target = viewHolder.itemView;
//                System.out.println("target.getLeft() is " + target.getLeft());
//                System.out.println("target.getTop() is " + target.getTop());
//                System.out.println("target.getRight() is " + target.getRight());
//                System.out.println("target.getBottom() is " + target.getBottom());




                return false;
            }

            @Override
            public boolean animateAppearance(@NonNull RecyclerView.ViewHolder viewHolder, @Nullable ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
                System.out.println("-->animateAppearance<--");
//                System.out.println("viewHolder is " + viewHolder);
//                System.out.println("preLayoutInfo is " + preLayoutInfo);
//                System.out.println("postLayoutInfo is " + postLayoutInfo);
                return false;
            }

            @Override
            public boolean animatePersistence(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
                System.out.println("-->animatePersistence<--");
//                System.out.println("viewHolder is " + viewHolder);
//                System.out.println("preLayoutInfo is " + preLayoutInfo);
//                System.out.println("postLayoutInfo is " + postLayoutInfo);
                return false;
            }

            @Override
            public boolean animateChange(@NonNull RecyclerView.ViewHolder oldHolder, @NonNull RecyclerView.ViewHolder newHolder, @NonNull ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
                System.out.println("-->animateChange<--");
                return false;
            }

            @Override
            public void runPendingAnimations() {
                System.out.println("-->runPendingAnimations<--");
                target.animate().start();//播放动画
            }

            @Override
            public void endAnimation(RecyclerView.ViewHolder item) {
                System.out.println("-->runPendingAnimations<--");
            }

            @Override
            public void endAnimations() {
                System.out.println("-->endAnimations<--");
            }

            @Override
            public boolean isRunning() {
                System.out.println("-->isRunning<--");
                return false;
            }
        };

        recyclerView.setItemAnimator(animator);


    }

    private void bindListen() {


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
