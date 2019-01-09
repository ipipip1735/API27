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

import java.util.Objects;

/**
 * Created by Administrator on 2019/1/9.
 */
public class IntentActivity extends AppCompatActivity {

    ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("**********  Main.onStart  ***********");
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("**********  Main.onStart  ***********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("**********  Main.onRestoreInstanceState  ***********");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("**********  Main.onRestart  ***********");
        getLoaderManager().enableDebugLogging(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("**********  Main.onResume  ***********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("**********  Main.onPause  ***********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("**********  Main.onBackPressed  ***********");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("**********  Main.onStop  ***********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("**********  Main.onSaveInstanceState  ***********");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("**********  Main.onDestroy  ***********");
    }


    public void start(View view) {
        System.out.println("~~start~~");

        //启动服务
        Intent intent = new Intent(this, ThreaderService.class);
        startService(intent);

    }

    public void stop(View view) {
        System.out.println("~~stop~~");
        Intent intent = new Intent(this, BaseService.class);
        intent.setAction("bs");
        stopService(intent);

    }

    public void bind(View view) {
        System.out.println("~~bind~~");

        Intent intent = new Intent(this, BaseService.class);
        intent.setAction("bs");

        if (Objects.isNull(serviceConnection))
            serviceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    System.out.println("..onServiceConnected..");
                    System.out.println("componentName is " + componentName);
                    System.out.println("iBinder is " + iBinder);

                    //发送信息给服务端
                    BaseBinder baseBinder = (BaseBinder) iBinder; //强制转换
                    Handler serviceHandler = baseBinder.getServiceHandler();
                    Message message = Message.obtain(null, 2);

//                    message.replyTo = new Messenger(iBinder); //方式一
                    message.replyTo = new Messenger(new Handler(new Handler.Callback() { //方式二
                        @Override
                        public boolean handleMessage(Message msg) {
                            System.out.println("Clinet|" + msg.what);
                             return true;
                        }
                    }));
//
                    serviceHandler.handleMessage(message);
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    System.out.println("..onServiceDisconnected..");
                    System.out.println("componentName is " + componentName);
                }
            };
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);

    }


    public void unbind(View view) {
        System.out.println("~~unbind~~");
        if (Objects.nonNull(serviceConnection)) unbindService(serviceConnection);

    }


    public void query(View view) {
        System.out.println("~~query~~");

    }
}
