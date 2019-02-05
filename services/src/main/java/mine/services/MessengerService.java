package mine.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Button;

/**
 * Created by Administrator on 2018/7/24.
 */

public class MessengerService extends Service {
    private Messenger messenger;

    public MessengerService() {
        System.out.println("+++ " + getClass().getSimpleName() + " +++");
        System.out.println(Thread.currentThread());
    }


    @Override
    public void onCreate() {
        System.out.println("---- " + getClass().getSimpleName() + ".onCreate ----");
        System.out.println(Thread.currentThread());

        Handler serviceHandler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                System.out.println("service|" + msg.what);
                System.out.println(Thread.currentThread());

                try {
                    msg.replyTo.send(Message.obtain(null, 0, 555, 0));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                stopSelf(msg.what);
            }
        };

        messenger = new Messenger(serviceHandler);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("---- " + getClass().getSimpleName() + ".onBind ----");
        System.out.println(messenger.getBinder());
        return messenger.getBinder();
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

        //方式一：直接从Intent中获取Messenger对象
//        Messenger messenger = intent.getParcelableExtra("Messenger");
//        System.out.println(messenger);
//        try {
//            messenger.send(Message.obtain(null, 3232));
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }


        //方式二：启动前台服务
//        serviceHandler.handleMessage(Message.obtain(null, startId));

//        Notification notification = new Notification(R.drawable.ic_launcher_background, getText(R.string.tc),
//                System.currentTimeMillis());
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
////        notification.setLatestEventInfo(this, getText(R.string.notification_title),
////                getText(R.string.notification_message), pendingIntent);
//        startForeground(1, notification);

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
