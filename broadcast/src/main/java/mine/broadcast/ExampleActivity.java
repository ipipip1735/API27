package mine.broadcast;

import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

import java.util.Set;

/**
 * Created by Administrator on 2017/4/14.
 */

public class ExampleActivity extends AppCompatActivity {



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        System.out.println("*******  Example  on  Create  Menu!  *********");
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        System.out.println("*******  Example  on  Prepare  Menu!!!  *********");
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("**********  Example  onCreate  ***********");

        setContentView(R.layout.activity_main);
        if (bundle == null) {
            System.out.println("Example bundle is empty");
        } else {
            System.out.println(bundle.size());
            Set<String> keySet = bundle.keySet();
            for (String i : keySet) {
                System.out.println(i);
            }
        }


    }

    @Override
    protected void onNewIntent(Intent intent) {
        System.out.println("****  Example  onNewIntent  *****");
        super.onNewIntent(intent);
    }


    @Override
    protected void onStart() {
        System.out.println("****Example**onStart*****");
        super.onStart();

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("****  Example  onRestoreInstanceState  *****");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        System.out.println("****Example**onRestart*****");
        super.onRestart();
    }



    @Override
    public void onBackPressed() {
        System.out.println("********Example***onBackPressed**********");
        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("****Example**onStop*****");
        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("****  Example   onSaveInstanceState  *****");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("****Example**onDestroy*****");
        super.onDestroy();
    }



    @Override
    protected void onResume() {
        System.out.println("****Example**onResume*****");
        super.onResume();


//        IntentFilter intentFilter = new IntentFilter("xx");
//        registerReceiver(oneReceiver, intentFilter);

    }

    @Override
    protected void onPause() {
        System.out.println("****Example**onPause*****");
        super.onPause();
//        unregisterReceiver(oneReceiver);
    }




    public void sendBC(View view) {
        System.out.println("*******sendBC*******");

//        Intent intent = new Intent("tt");
//        Intent intent = new Intent(this, MyReceiver.class);
//        intent.setAction("tt");
//        intent.putExtra("mReceiver", "One");
//        sendBroadcast(intent);
    }


    public void sendOBC(View view) {
        System.out.println("*******sendOBC*******");

        Intent intent = new Intent("myreceiver");
        intent.putExtra("mReceiver", "One");
        sendOrderedBroadcast(intent, "xxy");
    }

    public void showInfo(View view) {
        System.out.println("*******show info*******");

    }



}