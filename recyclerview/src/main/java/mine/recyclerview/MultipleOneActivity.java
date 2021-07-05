package mine.recyclerview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * Created by Administrator on 2021/7/14.
 */
public class MultipleOneActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView, recyclerView1, recyclerView2;
    private RecyclerView.Adapter adapter, adapter1, adapter2;
    Map<Integer, Map<Integer, List<String>>> data;
    List<String> items;
    int d1 = 0, d = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_multiple);

        data = new HashMap<>();
        items = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            Map<Integer, List<String>> data1 = new HashMap<>();
            for (int j = 0; j < 90; j++) {
                List<String> data2 = new ArrayList<>();
                for (int k = 0; k < 5; k++) {
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
                TextView textView = new TextView(MultipleOneActivity.this);
                textView.setTextSize(20);
                textView.setTag(1);
                textView.setPadding(100, 20, 100, 20);
                textView.setOnClickListener(MultipleOneActivity.this);
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
                TextView textView = new TextView(MultipleOneActivity.this);
                textView.setTextSize(20);
                textView.setPadding(20, 100, 20, 100);
                textView.setOnClickListener(MultipleOneActivity.this);
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
                TextView textView = new TextView(MultipleOneActivity.this);
                textView.setTextSize(20);
                textView.setPadding(0, 200, 0, 200);
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
        recyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                System.out.println("~~MultipleActivity.onScrollStateChanged~~");
                System.out.println("recyclerView2.canScrollVertically(-1) is " + recyclerView2.canScrollVertically(-1));
                System.out.println("recyclerView2.canScrollVertically(1) is " + recyclerView2.canScrollVertically(1));
                super.onScrollStateChanged(recyclerView, newState);

                if (newState != SCROLL_STATE_IDLE) return;
                if (!recyclerView2.canScrollVertically(-1)) {
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

                if (!recyclerView2.canScrollVertically(1)) {
                    System.out.println("recyclerView2 = " + recyclerView2);
                    System.out.println("d1 = " + d1);
                    if (d1 + 1 < data.get(d).size()) {
                        d1++;
                        System.out.println("d = " + d + ", d1 = " + d1);
                        if (data.get(d).get(d1) != null) {
                            items = data.get(d).get(d1);
                            adapter1.notifyDataSetChanged();
                            adapter2.notifyDataSetChanged();
                            recyclerView2.scrollToPosition(0);
                            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView1.getLayoutManager();
                            System.out.println("linearLayoutManager.findLastVisibleItemPosition() = " + linearLayoutManager.findLastVisibleItemPosition());
                            System.out.println("linearLayoutManager.findFirstVisibleItemPosition() = " + linearLayoutManager.findFirstVisibleItemPosition());
                            System.out.println("linearLayoutManager.findFirstCompletelyVisibleItemPosition() = " + linearLayoutManager.findFirstCompletelyVisibleItemPosition());
                            if (d1 > linearLayoutManager.findLastVisibleItemPosition())
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
