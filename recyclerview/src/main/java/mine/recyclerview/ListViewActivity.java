package mine.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2019/3/26.
 */
public class ListViewActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> nameAdapter;
    String[] nameArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_listview);

        listView = findViewById(R.id.lv);
        System.out.println(listView);

//        int n = 500;
//        nameArray = new String[n];
//
//        for (int i = 0; i < n; i++) {
//            nameArray[i] = "chris" + new Random().nextInt(9999);
//        }

        nameArray=new  String[]{
                "bob",
                "jack",
                "mack",
                "anna"
        };

//        nameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.nameArray);
        nameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        listView.setAdapter(nameAdapter);


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

//        ViewGroup viewGroup = findViewById(R.id.lv);
//        View v = viewGroup.getChildAt(0);
//        v.animate().x(150f);

//        ViewPropertyAnimator viewPropertyAnimator = v.animate();
//        System.out.println(viewPropertyAnimator);
//
//        ViewPropertyAnimator viewPropertyAnimator1 = v.animate();
//        System.out.println(viewPropertyAnimator1);





//                .notifyDataSetChanged();


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

    public void add(View view) {
        System.out.println("~~button.add~~");

        //方式一
//        TextView textView = new TextView(this);
//        textView.setText("aaaaa");
//        listView.addHeaderView(textView);

        //方式二
        nameAdapter.add("sss" + new Random().nextInt(999));
//        nameAdapter.insert("sss",  + new Random().nextInt(999));

        nameAdapter.notifyDataSetChanged();

    }


    public void del(View view) {
        System.out.println("~~button.del~~");

        nameAdapter.remove("sss");
        nameAdapter.notifyDataSetChanged();


    }


    public void query(View view) {
        System.out.println("~~button.query~~");

    }
}
