package mine.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class LeftRightActivity extends AppCompatActivity {

    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("**********  Main.onCreate  ***********");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    protected void onStart() {
        System.out.println("**********  Main.onStart  ***********");
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("**********  Main.onRestoreInstanceState  ***********");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        System.out.println("**********  Main.onRestart  ***********");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        System.out.println("**********  Main.onResume  ***********");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("**********  Main.onPause  ***********");
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        System.out.println("**********  Main.onBackPressed  ***********");
        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("**********  Main.onStop  ***********");
        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("**********  Main.onSaveInstanceState  ***********");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("**********  Main.onDestroy  ***********");
        super.onDestroy();
    }

    public void del(View view) {
        System.out.println("~~del~~");

        TextView textView = findViewById(R.id.textView);
        textView.setText("gogoggoog");
        System.out.println(textView);


    }
    public void add(View view) {
        System.out.println("~~add~~");



    }

    public void start(View view) {
        System.out.println("~~start~~");



    }

    public void stop(View view) {
        System.out.println("~~stop~~");



    }
}
