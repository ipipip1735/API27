package mine.recyclerview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * Created by Administrator on 2019/3/26.
 */
public class MultipleActivity extends AppCompatActivity {

    private RecyclerView recyclerView1, recyclerView2;
    private RecyclerView.Adapter adapter1, adapter2;
    Map<Integer, Map<Integer, List<String>>> data;
    Map<Integer, List<String>> data1;
    List<String> data2, items;
    int d1 = 0, d = 0, p = 1, dy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_multiple);

        data = new HashMap<>();
        items = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            data1 = new HashMap<>();
            for (int j = 0; j < 90; j++) {
                data2 = new ArrayList<>();
                for (int k = 0; k < 5; k++) {
                    System.out.println("iiiright|i=" + i + ",j=" + j + ",k=" + k + "|");
                    data2.add("right|i=" + i + ",j=" + j + ",k=" + k + "|" + random.nextInt(1000));
                }
                System.out.println("jjjright|i=" + i + ",j=" + j);
                data1.put(j, data2);
            }
            data.put(i, data1);
        }

        for (String list : data.get(d).get(0)) System.out.println("list = " + list);
        items.addAll(data.get(d).get(0));


        adapter1 = new RecyclerView.Adapter() {

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                TextView textView = new TextView(MultipleActivity.this);
                textView.setTextSize(20);
                textView.setPadding(0, 100, 0, 100);
                return new RecyclerView.ViewHolder(textView) {
                };
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                if (data1 == null) {
                    System.out.println("data1 = " + data1);
                } else {
                    if (data1.keySet() == null) {
                        System.out.println("data1.keySet() = " + data1.keySet());
                    } else {
                        ((TextView) holder.itemView).setText(data1.keySet().toArray()[position].toString());
                    }
                }
            }

            @Override
            public int getItemCount() {
                return data.get(0).keySet().size();
            }
        };


        adapter2 = new RecyclerView.Adapter() {

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                TextView textView = new TextView(MultipleActivity.this);
                textView.setTextSize(20);
                textView.setPadding(0, 200, 0, 200);
                return new RecyclerView.ViewHolder(textView) {
                };
            }

            @Override
            public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
                super.onViewAttachedToWindow(holder);
//                if (holder.getAdapterPosition() == data2.size()) {
//                    System.out.println("xxxxx");
//                    ((LinearLayoutManager)(recyclerView1.getLayoutManager())).scrollToPositionWithOffset(d1++, 0);
//                    System.out.println("d1 = " + d1);
//
//                }
            }

            @Override
            public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
                super.onViewDetachedFromWindow(holder);
                System.out.println("holder.getAdapterPosition() = " + holder.getAdapterPosition());
//                if (holder.getAdapterPosition() == data2.size()) {
//
//                    System.out.println("xxxxx");
////                    ((LinearLayoutManager)(recyclerView1.getLayoutManager())).scrollToPositionWithOffset(d1++, 0);
////                    System.out.println("d1 = " + d1);
//
//                }

                if (holder.getAdapterPosition() == -1) return;

                int s = 0, e = 0;
                for (int i = 0; i < d1; i++) {
                    s += data.get(d).get(i).size();
                }
                e += data.get(d).get(d1).size() + s - 1;
                System.out.println("s = " + s + ", e = " + e + ", d = " + d + ", d1 = " + d1);
                if (holder.getAdapterPosition() == s && dy < 0) {
                    d1--;
                    ((LinearLayoutManager) (recyclerView1.getLayoutManager())).scrollToPositionWithOffset(d1, 0);
                    System.out.println("----");
                }
                if (holder.getAdapterPosition() == e && dy > 0) {
                    d1++;
                    ((LinearLayoutManager) (recyclerView1.getLayoutManager())).scrollToPositionWithOffset(d1, 0);
                    System.out.println("++++");
                }


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


        recyclerView1 = findViewById(R.id.left);
        recyclerView1.setAdapter(adapter1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        recyclerView1.setLayoutManager(linearLayoutManager);


        recyclerView2 = findViewById(R.id.right);
        recyclerView2.setAdapter(adapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                System.out.println("~~MultipleActivity.onScrolled~~");
//                System.out.println("dx = " + dx + ", dy = " + dy);
                super.onScrolled(recyclerView, dx, dy);
                MultipleActivity.this.dy = dy;
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                System.out.println("~~MultipleActivity.onScrollStateChanged~~");
                System.out.println("recyclerView2.canScrollVertically(1) is " + recyclerView2.canScrollVertically(1));
                System.out.println("recyclerView2.canScrollVertically(2) is " + recyclerView2.canScrollVertically(2));
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == SCROLL_STATE_IDLE && !recyclerView2.canScrollVertically(1)) {
                    System.out.println("recyclerView2 = " + recyclerView2);
                    System.out.println("d1 = " + d1);
                    if (d1 + 1 < data1.size() && dy > 0) {
                        d1++;
                        p++;
                        System.out.println("d = " + d + ", d1 = " + d1 + ", dy = " + dy);
                        if (data1.get(d1) != null) {
                            items.addAll(data.get(d).get(d1));
                            adapter2.notifyDataSetChanged();
//                            recyclerView1.smoothScrollToPosition(d1);
                            ((LinearLayoutManager) (recyclerView1.getLayoutManager())).scrollToPositionWithOffset(d1, 0);

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

        ((LinearLayoutManager) (recyclerView1.getLayoutManager())).scrollToPositionWithOffset(p, 0);
        System.out.println("p = " + p);
    }

    public void stop(View view) {
        System.out.println("~~button.stop~~");
        p++;
        System.out.println("p = " + p);

//        linearLayoutManager.smoothScrollToPosition(recyclerView1, null, p);
//        recyclerView1.smoothScrollToPosition(p++);
//        recyclerView1.scrollToPosition(p++);
//        System.out.println("p = " + p);
//        System.out.println("d1 = " + d1);
//        recyclerView1.smoothScrollToPosition(d1);
    }
}
