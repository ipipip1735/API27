package mine.apptemp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import mine.services.IRemoteService;


public class AIDLActivity extends AppCompatActivity implements ServiceConnection {
    IRemoteService stub;
    boolean isBunding = false;
    Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("**********  Client.onStart  ***********");
        setContentView(R.layout.activity_client);
//        serviceIntent = new Intent("messenger");
//        serviceIntent.setPackage("mine.services");

        serviceIntent = new Intent();
        serviceIntent.setClassName("mine.services",
                "mine.services.AIDLService");

//        serviceIntent.setClassName()

    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("**********  Client.onStart  ***********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("**********  Client.onRestoreInstanceState  ***********");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("**********  Client.onRestart  ***********");
        getLoaderManager().enableDebugLogging(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("**********  Client.onResume  ***********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("**********  Client.onPause  ***********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("**********  Client.onBackPressed  ***********");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("**********  Client.onStop  ***********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("**********  Client.onSaveInstanceState  ***********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("**********  Client.onDestroy  ***********");
    }


    public void start(View view) {
        System.out.println("~~start~~");

        startService(serviceIntent);

        //        startForegroundService(serviceIntent);

    }


    public void stop(View view) {
        System.out.println("~~stop~~");

        stopService(serviceIntent);

    }


    public void bind(View view) {
        System.out.println("~~bind~~");

//        Intent serviceIntent = new Intent("bs");
//        MessengerActivity.serviceIntent.setClassName("mine.services", "mine.services.BaseService");

        isBunding = bindService(serviceIntent, this, BIND_AUTO_CREATE);
        System.out.println("bind is " + isBunding);

    }

    public void unbind(View view) {
        System.out.println("~~unbind~~");
//        if (isBunding) {
//
//            Intent serviceIntent = new Intent();
//            MessengerActivity.serviceIntent.setClassName("mine.services", "BaseService");
//            unbindService(this);
//            isBunding = false;
//        }

    }

    public void reloading(View view) {
        System.out.println("~~reloading~~");

    }


    public void del(View view) {
//        System.out.println("~~del~~");
////        stub.waitOne();
//        Message message = Message.obtain(null, 0, 1123, 0);
//        Messenger messenger = new Messenger(new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                System.out.println("Client|" + msg.arg1);
//            }
//        });
//        message.replyTo = messenger;
//        stub.sendMessage(message);
    }


    public void query(View view) {
        System.out.println("~~query~~");
//        stub.waitTwo();

    }


    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        System.out.println("---- " + getClass().getSimpleName() + ".onServiceConnected ----");
        System.out.println("services is " + service);
        System.out.println("name is " + service);
        isBunding = true;

        stub = IRemoteService.Stub.asInterface(service);

        try {
            int r = stub.basicTypes(13, 20l, false, 10.23f, 23.34d, "ok");
            System.out.println("result is " + r);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        System.out.println("---- " + getClass().getSimpleName() + ".onServiceDisconnected ----");
        isBunding = false;
//        stub = null;
    }
}