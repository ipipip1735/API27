package mine.broadcast;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
//    BasicReceiver broadcastReceiverTrial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("**** " + getClass().getSimpleName() + ".onCreate ****");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.activity_oldmain);
//        broadcastReceiverTrial = new BasicReceiver();
    }

    @Override
    protected void onResume() {
        System.out.println("**** " + getClass().getSimpleName() + ".onResume ****");
        super.onResume();


//        IntentFilter intentFilter = new IntentFilter("xx");
//        registerReceiver(broadcastReceiverTrial, intentFilter);

    }

    @Override
    protected void onDestroy() {
        System.out.println("**** " + getClass().getSimpleName() + ".onDestroy ****");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        System.out.println("**** " + getClass().getSimpleName() + ".onPause ****");
        super.onPause();
//        unregisterReceiver(broadcastReceiverTrial);
    }

    public void sendBC(View view) {
        System.out.println("~~ sendBC ~~");

        //显式Intent
//        Intent intent = new Intent(this, BasicReceiver.class);

        //隐式Intent
        Intent intent = new Intent("r1");
        intent.setPackage(getPackageName());
//        sendBroadcast(intent, "a.b"); //增加权限限制
//        intent.putExtra("mReceiver", "One");//携带数据

        sendBroadcast(intent); //发送广播

        System.out.println(Thread.currentThread());

    }

    public void sendOBC(View view) {
        System.out.println("~~ sendOBC ~~");

        Intent intent = new Intent("BCBC");
        intent.setPackage(getPackageName());
//        intent.putExtra("mReceiver", "One");
        sendOrderedBroadcast(intent, null);
//        sendOrderedBroadcast(intent, "xxy");
    }

    public void showInfo(View view) {

    }



}
