package mine.view;

import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

import java.io.IOException;

/**
 * Created by Administrator on 2017/4/14.
 */

public class LayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("**********  Example  onCreate  ***********");

//        setContentView(R.layout.activity_request_layout);
        setContentView(R.layout.activity_linear_layout);

    }

    @Override
    protected void onStart() {
        System.out.println("****Example  onStart*****");
        super.onStart();

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("****  Example  onRestoreInstanceState  *****");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        System.out.println("****Example  onRestart*****");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        System.out.println("****Example  onResume*****");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("****Example  onPause*****");
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        System.out.println("********Example***onBackPressed**********");
        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("****Example  onStop*****");
        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("****  Example   onSaveInstanceState  *****");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("****Example  onDestroy*****");
        super.onDestroy();
    }


    public void start(View view) {
        System.out.println("---------  start  --------");


        //测试requestLayout() 和 invalidate()
        View v = findViewById(R.id.ll1);
//        v.invalidate();
        v.requestLayout();


    }
}

