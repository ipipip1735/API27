package mine.recyclerview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static android.view.MotionEvent.ACTION_CANCEL;
import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;

/**
 * Created by Administrator on 2021/7/14.
 * fix at top and three level slide together
 */
public class MultipleTwoActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView, recyclerView1, recyclerView2;
    private FrameLayout top, fl;
    private RecyclerView.Adapter adapter, adapter1, adapter2;
    Map<Integer, Map<Integer, List<String>>> data;
    List<String> items;
    int d1 = 0, d = 0;
    boolean isUp;
    NestedScrollView nestedScrollView;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_multiple);
        linearLayout = findViewById(R.id.ll);
        fl = findViewById(R.id.fl);

        top = findViewById(R.id.top);
        recyclerView = findViewById(R.id.head);
        nestedScrollView = findViewById(R.id.nsv);


        nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                System.out.println("~~" + getClass().getSimpleName() + ".onScrollChange~~");
                System.out.println("scrollX = " + scrollX + ", scrollY = " + scrollY + ", oldScrollX = " + oldScrollX + ", oldScrollY = " + oldScrollY + ", v = " + v);

                top.setY(scrollY);
                if (scrollY >= fl.getHeight()) {
                    nestedScrollView.setScrollY(fl.getHeight());
                    if (linearLayout.findViewById(R.id.head) != null) {
                        linearLayout.removeView(recyclerView);
                        linearLayout.setPadding(0, recyclerView.getHeight(), 0, 0);
                        top.addView(recyclerView);
                    }
                    System.out.println("++++++++++true");
                    recyclerView1.setNestedScrollingEnabled(true);
                    recyclerView2.setNestedScrollingEnabled(true);


                } else {
                    if (top.findViewById(R.id.head) != null) {
                        top.removeView(recyclerView);
                        linearLayout.addView(recyclerView, 0);
                        linearLayout.setPadding(0, 0, 0, 0);
                    }
                    System.out.println("++++++++++false");
                    if (d1 == 0) {
                        recyclerView1.setNestedScrollingEnabled(false);
                        recyclerView2.setNestedScrollingEnabled(false);
                    } else {
                        nestedScrollView.setScrollY(fl.getHeight());
                    }

                }
            }
        });


        data = new HashMap<>();
        items = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < random.nextInt(20); i++) {
            Map<Integer, List<String>> data1 = new HashMap<>();
            for (int j = 0; j < random.nextInt(100); j++) {
                List<String> data2 = new ArrayList<>();
                for (int k = 0; k < random.nextInt(10); k++) {
                    data2.add("right|i=" + i + ",j=" + j + ",k=" + k + "|" + random.nextInt(1000));
                }
                data1.put(j, data2);
            }
            data.put(i, data1);
        }

//        d1 = data.get(d).size() > 3 ? 3 : 0;
        for (String list : data.get(d).get(d1)) System.out.println("list = " + list);
        items.addAll(data.get(d).get(d1));


        adapter = new RecyclerView.Adapter() {

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                TextView textView = new TextView(MultipleTwoActivity.this);
                textView.setTextSize(20);
                textView.setTag(1);
                textView.setPadding(100, 20, 100, 20);
                textView.setOnClickListener(MultipleTwoActivity.this);
                return new RecyclerView.ViewHolder(textView) {
                };
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                if (data == null) {
                    System.out.println("data.get(d) = " + data.get(d));
                } else {
                    if (data.keySet() == null) {
                        System.out.println("data.get(d).keySet() = " + data.get(d).keySet());
                    } else {
                        TextView textView = (TextView) holder.itemView;
                        textView.setText(data.get(d).keySet().toArray()[position].toString());
                        if (d == position) {
                            textView.setTextColor(getColor(R.color.white));
                        } else {
                            textView.setTextColor(getColor(R.color.black));
                        }
                    }
                }
            }

            @Override
            public int getItemCount() {
                return data.size();
            }
        };


        adapter1 = new RecyclerView.Adapter() {

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                TextView textView = new TextView(MultipleTwoActivity.this);
                textView.setTextSize(20);
                textView.setPadding(20, 100, 20, 100);
                textView.setOnClickListener(MultipleTwoActivity.this);
                return new RecyclerView.ViewHolder(textView) {
                };
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//                System.out.println("holder = " + holder + ", position = " + position);
                if (data.get(d) == null) {
                    System.out.println("data.get(d) = " + data.get(d));
                } else {
                    if (data.get(d).keySet() == null) {
                        System.out.println("data.get(d).keySet() = " + data.get(d).keySet());
                    } else {
                        TextView textView = (TextView) holder.itemView;
                        textView.setText(data.get(d).keySet().toArray()[position].toString());
                        if (d1 == position) {
                            textView.setTextColor(getColor(R.color.red));
                        } else {
                            textView.setTextColor(getColor(R.color.black));
                        }
                    }
                }
            }

            @Override
            public int getItemCount() {
                return data.get(d).keySet().size();
            }
        };


        adapter2 = new RecyclerView.Adapter() {

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                TextView textView = new TextView(MultipleTwoActivity.this);
                textView.setTextSize(20);
                textView.setBackgroundColor(getColor(R.color.Aquamarine));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.topMargin = 120;
                layoutParams.bottomMargin = 120;
                textView.setLayoutParams(layoutParams);
                textView.setPadding(0, 100, 0, 100);
                return new RecyclerView.ViewHolder(textView) {
                };
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                ((TextView) holder.itemView).setText(items.get(position));
            }

            @Override
            public int getItemCount() {
                return items.size();
            }
        };


        recyclerView = findViewById(R.id.head);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        recyclerView1 = findViewById(R.id.left);
        recyclerView1.setAdapter(adapter1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        recyclerView2 = findViewById(R.id.right);
        recyclerView2.setAdapter(adapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView2.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            float y = 0;

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                System.out.println("~~" + getClass().getSimpleName() + ".onInterceptTouchEvent~~");
                System.out.println("e = " + e + ", rv = " + rv);
                System.out.println("isUp = " + isUp);
                System.out.println("recyclerView.getScrollState() = " + recyclerView.getScrollState());
                System.out.println("recyclerView.getScrollY() = " + recyclerView.getScrollY());


                switch (e.getAction()) {
                    case ACTION_MOVE:
                        if (y != 0) {
                            isUp = y < e.getY();
                            y = e.getY();
                        } else {
                            y = e.getY();
                        }
                        break;
                    case ACTION_CANCEL:
                        System.out.println("rv = " + rv);
                        break;
                    case ACTION_DOWN:
                        System.out.println("rv = " + rv);
                        break;
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                System.out.println("~~" + getClass().getSimpleName() + ".onTouchEvent~~");
                System.out.println("e = " + e + ", rv = " + rv);

                switch (e.getAction()) {
                    case ACTION_DOWN:
                        System.out.println("rv = " + rv);
                        break;
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                System.out.println("~~" + getClass().getSimpleName() + ".onRequestDisallowInterceptTouchEvent~~");
                System.out.println("disallowIntercept = " + disallowIntercept);

            }
        });
        recyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                System.out.println("~~" + getClass().getSimpleName() + ".onScrolled~~");
                System.out.println("dx = " + dx + ", dy = " + dy + ", recyclerView = " + recyclerView);
                isUp = dy < 0;
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                System.out.println("~~RecyclerView2.onScrollStateChanged~~");
                System.out.println("recyclerView2.canScrollVertically(-1) is " + recyclerView2.canScrollVertically(-1));
                System.out.println("recyclerView2.canScrollVertically(1) is " + recyclerView2.canScrollVertically(1));
                super.onScrollStateChanged(recyclerView, newState);

                if (newState != SCROLL_STATE_IDLE) return;
                if (!recyclerView2.canScrollVertically(-1) && isUp) {
                    if (d1 - 1 >= 0) {
                        d1--;
                        System.out.println("------|d = " + d + ", d1 = " + d1);
                        if (data.get(d).get(d1) != null) {
                            adapter1.notifyDataSetChanged();
                            items = data.get(d).get(d1);
                            adapter2.notifyDataSetChanged();
                            recyclerView2.scrollToPosition(items.size() - 1);

                            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView1.getLayoutManager();
                            System.out.println("linearLayoutManager.findLastVisibleItemPosition() = " + linearLayoutManager.findLastVisibleItemPosition());
                            System.out.println("linearLayoutManager.findFirstVisibleItemPosition() = " + linearLayoutManager.findFirstVisibleItemPosition());
                            System.out.println("linearLayoutManager.findFirstCompletelyVisibleItemPosition() = " + linearLayoutManager.findFirstCompletelyVisibleItemPosition());
                            if (d1 < linearLayoutManager.findFirstVisibleItemPosition())
                                recyclerView1.scrollToPosition(d1);
                        }
                        System.out.println("items = " + items);
                    }
                }

                if (!recyclerView2.canScrollVertically(1) && !isUp) {
                    System.out.println("recyclerView2 = " + recyclerView2);
                    System.out.println("d1 = " + d1);
                    if (d1 + 1 < data.get(d).size()) {
                        d1++;
                        System.out.println("+++++|d = " + d + ", d1 = " + d1);
                        if (data.get(d).get(d1) != null) {
                            items = data.get(d).get(d1);
                            adapter1.notifyDataSetChanged();
                            adapter2.notifyDataSetChanged();
                            recyclerView2.scrollToPosition(0);
                            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView1.getLayoutManager();
                            System.out.println("linearLayoutManager.findLastVisibleItemPosition() = " + linearLayoutManager.findLastVisibleItemPosition());
                            System.out.println("linearLayoutManager.findFirstVisibleItemPosition() = " + linearLayoutManager.findFirstVisibleItemPosition());
                            System.out.println("linearLayoutManager.findFirstCompletelyVisibleItemPosition() = " + linearLayoutManager.findFirstCompletelyVisibleItemPosition());
                            if (d1 >= linearLayoutManager.findLastVisibleItemPosition())
                                linearLayoutManager.scrollToPositionWithOffset(linearLayoutManager.findFirstCompletelyVisibleItemPosition() + 1, 0);
                        }
                        System.out.println("items = " + items);
                    }

                }


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
//        linearLayoutManager.smoothScrollToPosition(recyclerView1, null, p);
//        recyclerView1.scrollToPosition(d1);
//        System.out.println("d1 = " + d1);
//        System.out.println("d1 = " + d1);
//        recyclerView1.smoothScrollToPosition(d1);

//        ((LinearLayoutManager) (recyclerView1.getLayoutManager())).scrollToPositionWithOffset(p, 0);
//        System.out.println("p = " + p);
    }

    public void stop(View view) {
        System.out.println("~~button.stop~~");

//        linearLayoutManager.smoothScrollToPosition(recyclerView1, null, p);
//        recyclerView1.smoothScrollToPosition(p++);
//        recyclerView1.scrollToPosition(p++);
//        System.out.println("p = " + p);
//        System.out.println("d1 = " + d1);
//        recyclerView1.smoothScrollToPosition(d1);
    }

    @Override
    public void onClick(View v) {

        if (v.getTag() == null) {
            System.out.println("recyclerView1.indexOfChild(v) = " + recyclerView1.indexOfChild(v));
            System.out.println("recyclerView1.findContainingViewHolder(v).getAdapterPosition() = " + recyclerView1.findContainingViewHolder(v).getAdapterPosition());

            d1 = recyclerView1.findContainingViewHolder(v).getAdapterPosition();
            items = data.get(d).get(d1);
            adapter1.notifyDataSetChanged();
            adapter2.notifyDataSetChanged();
        } else {
            System.out.println("recyclerView.indexOfChild(v) = " + recyclerView.indexOfChild(v));
            System.out.println("recyclerView.findContainingViewHolder(v) = " + recyclerView.findContainingViewHolder(v));
            System.out.println("recyclerView.findContainingViewHolder(v).getAdapterPosition() = " + recyclerView.findContainingViewHolder(v).getAdapterPosition());

            d = recyclerView.findContainingViewHolder(v).getAdapterPosition();
            recyclerView1.smoothScrollToPosition(d1 = 0);
            adapter.notifyDataSetChanged();
            adapter1.notifyDataSetChanged();
            items = data.get(d).get(d1);
            adapter2.notifyDataSetChanged();
        }

    }
}
