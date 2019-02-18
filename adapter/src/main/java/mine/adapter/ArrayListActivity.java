package mine.adapter;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Administrator on 2019/2/18.
 */
public class ArrayListActivity extends AppCompatActivity {

    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("**********  Main.onStart  ***********");
        setContentView(R.layout.activity_array_adapter);

        arrayAdapter = new ArrayAdapter<>(this, R.layout.item);
        arrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                System.out.println("~~onChanged~~");
                ViewGroup viewGroup = findViewById(R.id.ll);
            }

            @Override
            public void onInvalidated() {
                System.out.println("~~onInvalidated~~");
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("**********  Main.onStart  ***********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("**********  Main.onRestoreInstanceState  ***********");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("**********  Main.onRestart  ***********");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("**********  Main.onResume  ***********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("**********  Main.onPause  ***********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("**********  Main.onBackPressed  ***********");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("**********  Main.onStop  ***********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("**********  Main.onSaveInstanceState  ***********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("**********  Main.onDestroy  ***********");
    }


    public void add(View view) {
        System.out.println("~~add~~");

        arrayAdapter.add("asdfs");





    }

    public void notify(View view) {
        System.out.println("~~notify~~");


    }

    public void query(View view) {
        System.out.println("~~query~~");




    }


    public void start(View view) {
        System.out.println("~~start~~");

        TextView textView;

        textView = (TextView) arrayAdapter.getView(0, null, null);
        System.out.println(textView);

//        textView = new TextView(this);
//        textView.setText("ASDJFLS;AJDLF");

        ViewGroup viewGroup = findViewById(R.id.ll);
        System.out.println(viewGroup);
        viewGroup.addView(textView);




    }

    public void stop(View view) {
        System.out.println("~~stop~~");

    }


}
