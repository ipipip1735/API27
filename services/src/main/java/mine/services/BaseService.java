package mine.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.Nullable;

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
        System.out.println(Thread.currentThread());

        serviceHandler = new Handler(getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                System.out.println("service|" + msg.what);
                stopSelf(msg.what);
            }
        };
    }


    @Override
    public void onDestroy() {
        System.out.println("---- " + getClass().getSimpleName() + ".onDestroy ----");
        System.out.println(Thread.currentThread());
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("---- " + getClass().getSimpleName() + ".onStartCommand ----");
        System.out.println(Thread.currentThread());

        System.out.println("start id  is " + startId);

        Message message = new Message();
        message.what = startId;
        serviceHandler.sendMessage(message);

        return START_STICKY;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("---- " + getClass().getSimpleName() + ".onUnbind ----");
//        stopSelf();
//        return super.onUnbind(intent);
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
