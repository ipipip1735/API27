package mine.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2018/7/24.
 */

public class AIDLService extends Service {


    public AIDLService() {
        System.out.println("+++ " + getClass().getSimpleName() + " +++");
        System.out.println(Thread.currentThread());
    }

    @Override
    public void onCreate() {
        System.out.println("---- " + getClass().getSimpleName() + ".onCreate ----");
        System.out.println(Thread.currentThread());

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("---- " + getClass().getSimpleName() + ".onBind ----");
        AIDLBinder aidlBinder = new AIDLBinder();
        return aidlBinder;
//        return null;
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

        return START_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("---- " + getClass().getSimpleName() + ".onUnbind ----");

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

}
