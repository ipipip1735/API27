package mine.recyclerview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2021/7/14.
 */
public class MultipleTwoActivity extends AppCompatActivity {

    private RecyclerView main;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_coordinator);

        main = findViewById(R.id.main);


//        Random random = new Random();
//        for (int i = 0; i < 5; i++) {
//
//        }


        adapter = new RecyclerView.Adapter() {

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_view_one, parent, false)) {
                };

            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                System.out.println("~~" + getClass().getSimpleName() + ".onBindViewHolder~~");

                if(position == 1) return;

//                List<String> list = new ArrayList<>();
//                for (int j = 0; j < 100; j++) {
//                    list.add(j + "|xxx");
//                }
//
//                ListView listView = holder.itemView.findViewById(R.id.lv);
//                listView.setAdapter(new ArrayAdapter(MultipleTwoActivity.this, android.R.layout.simple_list_item_1, list));
//
//                TextView textView = new TextView(MultipleTwoActivity.this);
//                textView.setText("++++++header++++++");
//                listView.addHeaderView(textView);
//                listView.setNestedScrollingEnabled(true);

                RecyclerView recyclerView = holder.itemView.findViewById(R.id.rv);
                recyclerView.setHasFixedSize(true);
                recyclerView.setNestedScrollingEnabled(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(MultipleTwoActivity.this));
                recyclerView.setAdapter(new RecyclerView.Adapter() {
                    @NonNull
                    @Override
                    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        TextView textView = new TextView(MultipleTwoActivity.this);

                        textView.setPadding(150,150,150,150);
                        return  new RecyclerView.ViewHolder(textView){};
                    }

                    @Override
                    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

                        TextView textView = (TextView) holder.itemView;
                        textView.setText(position + "|xxxxx");
                    }

                    @Override
                    public int getItemCount() {
                        return 500;
                    }
                });






            }

            @Override
            public int getItemCount() {
                return 1;
            }
        };
        main.setLayoutManager(new GridLayoutManager(this, 1));
        main.setAdapter(adapter);
        main.setHasFixedSize(true);
//        main.setLayoutManager(new LinearLayoutManager(this));
        main.setNestedScrollingEnabled(true);



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

    }

    public void stop(View view) {
        System.out.println("~~button.stop~~");
    }
}
