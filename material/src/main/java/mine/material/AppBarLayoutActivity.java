package mine.material;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.AppBarLayout;

/**
 * Created by Administrator on 2020/6/28.
 */
public class AppBarLayoutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
//        one();
        two();


    }

    private void two() {

//        setContentView(R.layout.activity_app_bar_one);
        setContentView(R.layout.activity_app_bar_two);


        LinearLayout linearLayout = findViewById(R.id.ll);
        if (linearLayout != null) {
            for (int i = 0; i < 100; i++) {
                TextView textView = new TextView(this);
                textView.setText("TV" + i);
                linearLayout.addView(textView);
            }
        }

        LinearLayout linearLayout1 = findViewById(R.id.ll1);
        if (linearLayout1 != null) {
            for (int i = 0; i < 100; i++) {
                TextView textView = new TextView(this);
                textView.setText("1|TV" + i);
                linearLayout1.addView(textView);
            }
        }

        LinearLayout linearLayout2 = findViewById(R.id.ll2);
        if (linearLayout2 != null) {
            for (int i = 0; i < 100; i++) {
                TextView textView = new TextView(this);
                textView.setText("2|TV" + i);
                linearLayout2.addView(textView);
            }
        }

//        LinearLayout linearLayout3 = findViewById(R.id.ll3);
//        for (int i = 0; i < 100; i++) {
//            TextView textView = new TextView(this);
//            textView.setText("3|TV" + i);
//            linearLayout3.addView(textView);
//        }
    }

    private void one() {
        setContentView(R.layout.activity_app_bar);


        LinearLayout linearLayout = findViewById(R.id.ll);

        for (int i = 0; i < 100; i++) {
            TextView textView = new TextView(this);
            textView.setText("TV" + i);
            linearLayout.addView(textView);
        }

        AppBarLayout appBarLayout = findViewById(R.id.abl);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                System.out.println("~~AppBarLayoutActivity.onOffsetChanged~~");
                System.out.println("verticalOffset = " + verticalOffset + ", appBarLayout = " + appBarLayout);
                System.out.println("appBarLayout.isLiftOnScroll() = " + appBarLayout.isLiftOnScroll());
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
}
