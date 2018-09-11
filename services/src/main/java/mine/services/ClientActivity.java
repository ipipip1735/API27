package mine.services;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ClientActivity extends AppCompatActivity implements ServiceConnection {
    Handler baseService;
    boolean isBunding = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("**********  Client.onStart  ***********");
        setContentView(R.layout.activity_main);
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

        Intent intent = new Intent("bs");
        intent.setPackage(getPackageName());


//        Intent intent = new Intent("messenger");
//        intent.setPackage("mine.services");


//        Intent intent = new Intent("messenger");
//        intent.setClassName(this, "MessengerService");



        startService(intent);

    }


    public void stop(View view) {
        System.out.println("~~stop~~");

        Intent intent = new Intent("bs");
        intent.setAction("bs");
        intent.setPackage(getPackageName());
        stopService(intent);

    }


    public void bind(View view) {
        System.out.println("~~bind~~");

        Intent intent = new Intent("bs");
        intent.setAction("bs");
        intent.setPackage(getPackageName());
        bindService(intent, this, BIND_AUTO_CREATE);

    }

    public void unbind(View view) {
        System.out.println("~~unbind~~");
        if (isBunding) {

            Intent intent = new Intent(this, BaseService.class);
            intent.setAction("bs");
            unbindService(this);
            isBunding = false;
        }

    }

    public void reloading(View view) {
        System.out.println("~~reloading~~");

    }


    public void del(View view) {
        System.out.println("~~del~~");
//        baseService.waitOne();
        Message message = Message.obtain(null, 0, 1123, 0);
        Messenger messenger = new Messenger(new Handler() {
            @Override
            public void handleMessage(Message msg) {
                System.out.println("Client|" + msg.arg1);
            }
        });
        message.replyTo = messenger;
        baseService.sendMessage(message);
    }


    public void query(View view) {
        System.out.println("~~query~~");
//        baseService.waitTwo();

    }


    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        System.out.println("---- " + getClass().getSimpleName() + ".onServiceConnected ----");
        System.out.println(service);

        isBunding = true;
        baseService = ((BaseBinder) service).getServiceHandler();

        System.out.println(baseService);


    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        System.out.println("---- " + getClass().getSimpleName() + ".onServiceDisconnected ----");
        isBunding = false;
        baseService = null;
    }
}