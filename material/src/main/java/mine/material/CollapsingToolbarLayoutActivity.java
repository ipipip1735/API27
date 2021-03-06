package mine.material;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ActionProvider;

import com.google.android.material.appbar.CollapsingToolbarLayout;

/**
 * Created by Administrator on 2020/7/1.
 */
public class CollapsingToolbarLayoutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_collapsing_toolbar);

//        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
//        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.silver, null));//设置展开后标题颜色
//        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.blue, null));//设置折叠时标题颜色
//        collapsingToolbarLayout.setTitleEnabled(true);
//        collapsingToolbarLayout.setTitle("TTTTTT");


//        Toolbar toolbar = findViewById(R.id.toolbar);//设置Toolbar是无效的，优先级没有CollapsingToolbarLayout高
//        toolbar.setTitle("SSSSSSSSSSS");
//        setSupportActionBar(toolbar);


        LinearLayout linearLayout = findViewById(R.id.ll);

        for (int i = 0; i < 100; i++) {
            TextView textView = new TextView(this);
            textView.setText("TV" + i);
            linearLayout.addView(textView);
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestoreInstanceState  *********");

        super.onRestoreInstanceState(savedInstanceState);
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
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");

        super.onPause();
    }

    @Override
    public void onBackPressed() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onBackPressed  *********");

        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");

        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onSaveInstanceState  *********");

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");

        super.onDestroy();
    }


    public void start(View view) {
        System.out.println("~~button.start~~");

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

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void del(View view) {
        System.out.println("~~button.del~~");

    }


    public void query(View view) {
        System.out.println("~~button.query~~");

    }


    static class BasicActionProvider extends ActionProvider {

        public BasicActionProvider(Context context) {
            super(context);
            System.out.println("+++ " + getClass().getSimpleName() + ".Constructor +++");
        }

        @Override
        public View onCreateActionView() {
            System.out.println("*********  " + getClass().getSimpleName() + ".onCreateActionView  *********");

            //方式一：代码方式
            Button button = new Button(getContext());
            button.setText("btn1");
            return button;

        }

        @Override
        public boolean onPerformDefaultAction() {
            System.out.println("*********  " + getClass().getSimpleName() + ".onPerformDefaultAction  *********");

            return super.onPerformDefaultAction();
        }
    }
}
