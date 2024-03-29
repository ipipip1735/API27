package mine.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Administrator on 2019/2/5.
 */

public class TransactService extends Service {
    private int n = 0;

    private class TransactBinder extends Binder {
        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {

            if (code == INTERFACE_TRANSACTION) {
                int result = parseString(data.readString());
                reply.writeInt(result);
            }
            xxx();
            return true;
        }


    }

    public void xxx() {
        System.out.println("xxx" + n);
    }

    public TransactService() {
        System.out.println("+++ " + getClass().getSimpleName() + ".Constructor +++");
        System.out.println(Thread.currentThread());

    }

    private int parseString(String s) {
        Integer integer = new Integer(s);
        return integer.intValue() * 2;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("---- " + getClass().getSimpleName() + ".onBind ----");
        return new TransactBinder();
    }


    @Override
    public void onCreate() {
        System.out.println("---- " + getClass().getSimpleName() + ".onCreate ----");
    }


    @Override
    public void onDestroy() {
        System.out.println("---- " + getClass().getSimpleName() + ".onDestroy ----");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("---- " + getClass().getSimpleName() + ".onStartCommand ----");


        //卡死UI
//        try {
//            Thread.sleep(1000L * 10);  //睡10秒，一般8秒就会卡死
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        n = startId;
        System.out.println("startId is " + n);

        return START_STICKY;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("---- " + getClass().getSimpleName() + ".onUnbind ----");
        return false;
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
