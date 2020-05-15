package mine.swiperefreshlayout;

import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2018/8/25.
 */
public class MainActivity extends AppCompatActivity {
    List<String> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_main);


        dataset = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            dataset.add("name" + i);
        }

        final ListView listView = findViewById(android.R.id.list);
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, this.dataset));

        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swiperefresh);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                System.out.println("~~onRefresh~~");

                //方式一：同步更新
//                listView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        dataset.set(1, "name--");
//                        ((ArrayAdapter<String>) listView.getAdapter()).notifyDataSetChanged();
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                }, 2000L);

                //方式二：异步更新
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //耗时任务
                        for (int i = 5; i < 7; i++) {
                            dataset.set(i, "name--" + i);
                            try {
                                Thread.sleep(500L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        //更新UI
                        listView.post(new Runnable() {
                            @Override
                            public void run() {
                                ((ArrayAdapter<String>) listView.getAdapter()).notifyDataSetChanged();
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                }).start();

            }
        });


        //设置进度条颜色
//        swipeRefreshLayout.setColorSchemeColors(Color.argb(255, 0, 0, 255),
//                Color.argb(255, 255, 0, 0),
//                Color.argb(255, 0, 255, 0));
//        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);//设置尺寸

        //绑定向上滚动监听器
        swipeRefreshLayout.setOnChildScrollUpCallback(new SwipeRefreshLayout.OnChildScrollUpCallback() {

            boolean isAdding = false;
            int tag = 0;

            @Override
            public boolean canChildScrollUp(@NonNull final SwipeRefreshLayout parent, @Nullable final View child) {
                System.out.println("~~canChildScrollUp~~");

                final ListView lv = (ListView) child;


                if (!lv.canScrollList(1) && !isAdding) {//判断能否向下滚动，按需加载
                    isAdding = true;
                    final Toast toast = Toast.makeText(child.getContext(), "Loading...", Toast.LENGTH_LONG);
                    toast.show();

                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                Thread.sleep(3000L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            child.post(new Runnable() {
                                @Override
                                public void run() {
                                    TextView textView = new TextView(child.getContext());
                                    textView.setText("nameHead-");

                                    ArrayAdapter<String> arrayAdapter = (ArrayAdapter) ((ListView) child).getAdapter();


                                    int n = lv.getLastVisiblePosition() - lv.getFirstVisiblePosition();
                                    tag++;
                                    //方式一
                                    for (int i = 0; i < n; i++) {
                                        arrayAdapter.add("name" + tag);
                                    }

                                    //方式二
//                                    for (int i = 0; i < n; i++) {
//                                        arrayAdapter.add("name" + tag);
//                                    }
//                                    arrayAdapter.notifyDataSetChanged();


                                    isAdding = false;//加载完成修改标记
                                    toast.cancel();
                                }
                            });
                        }
                    }).start();
                    return false;
                }

                return child.canScrollVertically(-1); //判断能否向上滚动，下拉刷新UI
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
        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setProgressViewEndTarget(true, 100);
        swipeRefreshLayout.setRefreshing(true);


    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");
        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setRefreshing(false);
    }

    public void add(View view) {
        System.out.println("~~button.add~~");

        ListView listView = findViewById(android.R.id.list);
        TextView textView = new TextView(this);
        textView.setText("nameHead-");
        listView.addHeaderView(textView);


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
