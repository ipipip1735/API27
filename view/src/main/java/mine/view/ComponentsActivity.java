package mine.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by Administrator on 2017/4/14.
 */

public class ComponentsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("**********  Example  onCreate  ***********");

        setContentView(R.layout.activity_button);
//        TextView textView = (TextView) findViewById(R.id.textView);
        System.out.println("complete");



    }


    @Override
    protected void onNewIntent(Intent intent) {
        System.out.println("****  Example  onNewIntent  *****");
        super.onNewIntent(intent);
    }


    @Override
    protected void onStart() {
        System.out.println("****   Example  onStart  *****");
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("****  Example  onRestoreInstanceState  *****");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        System.out.println("****   Example  onRestart  *****");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        System.out.println("****   Example  onResume  *****");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("****   Example  onPause  *****");
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        System.out.println("*****   Example  onBackPressed  *****");
        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("*****   Example**onStop  *****");
        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("****  Example  onSaveInstanceState  *****");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("****   Example  onDestroy*****");
        super.onDestroy();
    }


//**************按钮事件******************

    public void addView(View view) {

//        TextView textView = new TextView(this);
//        textView.setText("my text");
//        ViewGroup viewGroup = (ViewGroup)findViewById(R.id.viewContainer);
//        viewGroup.addView(textView);
//        System.out.println("****button.addView*****");
//        findViewById(R.id.Custom).requestLayout();
        CustomView customView = (CustomView) findViewById(R.id.Custom);
//        customView.invalidate();
        customView.setX(6);
//        customView.setPadding(200,20,200,300);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setPadding(10,20, 300, 400);


    }


    public void restartService(View view) {



    }


    public void stopService(View view) {

    }


}




