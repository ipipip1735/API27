package mine.broadcast;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
//    BroadcastReceiverTrial broadcastReceiverTrial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.activity_oldmain);
//        broadcastReceiverTrial = new BroadcastReceiverTrial();
    }

    @Override
    protected void onResume() {
        System.out.println("****Example**onResume*****");
        super.onResume();


//        IntentFilter intentFilter = new IntentFilter("xx");
//        registerReceiver(broadcastReceiverTrial, intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("****Example**onDestroy*****");
    }

    @Override
    protected void onPause() {
        System.out.println("****Example**onPause*****");
        super.onPause();
//        unregisterReceiver(broadcastReceiverTrial);
    }

    public void sendBC(View view) {
        System.out.println("*******sendBC*******");


        //显式Intent
//        Intent intent = new Intent(this, BroadcastReceiverTrial.class);

        //隐式Intent
        Intent intent = new Intent("receivertrial");
        intent.setPackage(getPackageName());

        intent.putExtra("one", "111");
        sendBroadcast(intent);

    }

    public void sendOBC(View view) {
        System.out.println("*******sendOBC*******");

        Intent intent = new Intent("receivertrial");
        intent.putExtra("mReceiver", "One");
        sendOrderedBroadcast(intent, "xxy");
    }

    public void showInfo(View view) {
        System.out.println("*******show info*******");

    }



}
