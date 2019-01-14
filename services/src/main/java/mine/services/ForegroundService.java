package mine.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import java.util.Objects;

/**
 * Created by Administrator on 2019/1/9.
 */
public class ForegroundService extends Service {
    public ForegroundService() {
        System.out.println("+++ " + getClass().getSimpleName() + ".constructor +++");

    }


    @Override
    public void onCreate() {
        System.out.println("---- " + getClass().getSimpleName() + ".onCreate ----");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("---- " + getClass().getSimpleName() + ".onStartCommand ----");

        if (intent.getIntExtra("category", -1) == 1) {
            stopForeground(true);
            return START_STICKY;
        }


        //创建通知通道
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        String channelId = "c1";
        CharSequence channelName = "cf";
        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
        notificationChannel.setShowBadge(false);
        notificationManager.createNotificationChannel(notificationChannel);

        //创建通知
        Notification notification = new NotificationCompat.Builder(this, "c1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("ttt")
                .setContentText("cccc")
                .setSubText("ssss")
                .build();

        //启动前台服务
        startForeground(1, notification);

        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        System.out.println("---- " + getClass().getSimpleName() + ".onDestroy ----");
        System.out.println(Thread.currentThread());
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("---- " + getClass().getSimpleName() + ".onBind ----");
        return null;
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
