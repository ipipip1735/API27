package mine.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.Objects;

/**
 * Created by Administrator on 2018/7/24.
 */

public class BaseService extends Service {

    private Handler serviceHandler;

    public BaseService() {
        System.out.println("+++ " + getClass().getSimpleName() + ".BaseService +++");
        System.out.println(Thread.currentThread());

    }


    public Handler getServiceHandler() {
        System.out.println(serviceHandler);
        return serviceHandler;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("---- " + getClass().getSimpleName() + ".onBind ----");
        return new BaseBinder(serviceHandler);
    }


    @Override
    public void onCreate() {
        System.out.println("---- " + getClass().getSimpleName() + ".onCreate ----");

        serviceHandler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                System.out.println("-.- " + getClass().getSimpleName() + ".handleMessage -.-");

                if (msg.what == 1) { //根据what判断服务启动类别，是bind，还是start
                    System.out.println("service|" + msg.what);
                } else {
                    System.out.println("bind|" + msg.what);
                }


                //回应服务端信息
                if (Objects.nonNull(msg.replyTo)) { //判断是否传递Messenger
                    Messenger messenger = msg.replyTo;
                    try {
                        messenger.send(Message.obtain(null, 99));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

//                stopSelf(msg.what); //是否手动终止服务取决于业务逻辑
            }
        };
    }


    @Override
    public void onDestroy() {
        System.out.println("---- " + getClass().getSimpleName() + ".onDestroy ----");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("---- " + getClass().getSimpleName() + ".onStartCommand ----");

        System.out.println("start id  is " + startId);

        Message message = Message.obtain();
        message.what = startId;
        serviceHandler.sendMessage(message);



        return START_STICKY;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("---- " + getClass().getSimpleName() + ".onUnbind ----");
//        return super.onUnbind(intent);

//        stopSelf();

        return true;
    }


    @Override
    public void onRebind(Intent intent) {
        System.out.println("---- " + getClass().getSimpleName() + ".onRebind ----");
        super.onRebind(intent);
    }


    @Override
    public void onTaskRemoved(Intent rootIntent) {
        System.out.println("---- " + getClass().getSimpleName() + ".onTaskRemoved ----");
        super.onTaskRemoved(rootIntent);
    }


    public void waitOne() {
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("one is end");
    }


    public void waitTwo() {
        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("two is end");
    }


}
