package mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Locale;
import java.util.Set;

public class OneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("**********  One.onCreate  ***********");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        TextView textView = findViewById(R.id.textView);

        //根据Category判断使用哪种业务逻辑
        Set<String> categories = getIntent().getCategories();
        for (String s : categories) {
            switch (s) {
                case "gk":
                    textView.setText(getClass().getSimpleName() + "|" + s);
                    break;
                case "gt":
                    textView.setText(getClass().getSimpleName() + "|" + s);
                    break;
            }
        }


    }

    @Override
    protected void onNewIntent(Intent intent) {
        System.out.println("**********  One.onNewIntent  ***********");
        super.onNewIntent(intent);
    }


    @Override
    protected void onStart() {
        System.out.println("**********  One.onStart  ***********");
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("**********  One.onRestoreInstanceState  ***********");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        System.out.println("**********  One.onRestart  ***********");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        System.out.println("**********  One.onResume  ***********");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("**********  One.onPause  ***********");
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        System.out.println("**********  One.onBackPressed  ***********");
        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("**********  One.onStop  ***********");
        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("**********  One.onSaveInstanceState  ***********");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("**********  One.onDestroy  ***********");
        super.onDestroy();
    }
}
