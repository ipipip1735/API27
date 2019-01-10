package mine.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;


public class NotificationActivity extends AppCompatActivity {
    int mId = 1, incr = 0;
    NotificationCompat.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("**********  " + getClass().getSimpleName() + ".onStart  ***********");
        setContentView(R.layout.activity_notify);

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("**********  " + getClass().getSimpleName() + ".onStart  ***********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("**********  " + getClass().getSimpleName() + ".onRestoreInstanceState  ***********");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("**********  " + getClass().getSimpleName() + ".onRestart  ***********");
        getLoaderManager().enableDebugLogging(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("**********  " + getClass().getSimpleName() + ".onResume  ***********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("**********  " + getClass().getSimpleName() + ".onPause  ***********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("**********  " + getClass().getSimpleName() + ".onBackPressed  ***********");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("**********  " + getClass().getSimpleName() + ".onStop  ***********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("**********  " + getClass().getSimpleName() + ".onSaveInstanceState  ***********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("**********  " + getClass().getSimpleName() + ".onDestroy  ***********");
    }


    public void start(View view) {
        System.out.println("~~button.start~~");

        //创建通道
        NotificationManager notificationManager = getSystemService(NotificationManager.class);

        String channelId = "c1";
        CharSequence channelName = "cf";
        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);

        notificationChannel.setShowBadge(false);
        notificationManager.createNotificationChannel(notificationChannel);

        //创建通知




//        customNotify(notificationManager);
//        groupNotify(notificationManager);
        actionNotify(notificationManager);
//        normalNotify(notificationManager);


    }

    private void customNotify(NotificationManager notificationManager) {

        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.notification_small);
        RemoteViews notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.notification_large);


        Notification customNotification = new NotificationCompat.Builder(this, "c1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(notificationLayout)
                .setCustomBigContentView(notificationLayoutExpanded)
                .build();
        notificationManager.notify(1, customNotification);
    }

    private void groupNotify(NotificationManager notificationManager) {


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "c1");
        Notification notification1 = mBuilder.setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("n1")
                .setContentText("aaaaaaaaaa")
                .setGroup("myGroup")
                .setNumber(15)
                .setAutoCancel(true)
                .build();

        Notification notification2 = mBuilder.setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("n2")
                .setContentText("bbbbbbbbbbbbbb")
                .setGroup("myGroup")
                .setAutoCancel(true)
                .build();

        Notification groupNotify = mBuilder.setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("GG")
                .setContentText("n1, n2")
                .setGroup("myGroup")
                .setAutoCancel(true)
                .setGroupSummary(true)
                .build();
//        notificationManager.notify(3, groupNotify);
        notificationManager.notify(1, notification1);
//        notificationManager.notify(2, notification2);

    }

    private void actionNotify(NotificationManager notificationManager) {

        Intent intent = new Intent(this, OneActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Intent mbrIntent = new Intent(this, MBR.class);
        PendingIntent mbrPendingIntent = PendingIntent.getBroadcast(this, 0, mbrIntent, 0);

        MediaSession mediaSession = new MediaSession(this, "gop");

        Notification.MediaStyle style = new Notification.MediaStyle();
        style.setShowActionsInCompactView(1).setMediaSession(mediaSession.getSessionToken());


        Notification.Builder mBuilder = new Notification.Builder(this, "c1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("My notification")
                .setVisibility(Notification.VISIBILITY_PRIVATE)
                .setGroup("ook")
                .setAutoCancel(true)
                .setContentText("Hello World!")
                .setProgress(0, 0, false)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.w1, "MBR", mbrPendingIntent)
                .setStyle(style);

        notificationManager.notify(mId, mBuilder.build());
    }


    private void normalNotify(NotificationManager notificationManager) {
        String channelId = "c1";
        CharSequence channelName = "cf";
        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);

//        notificationChannel.enableLights(true);
//        notificationChannel.setLightColor(R.color.colorAccent);


        notificationManager.createNotificationChannel(notificationChannel);


//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//        stackBuilder.addParentStack(OneActivity.class);
//        stackBuilder.addNextIntent(new Intent(this, OneActivity.class));
//        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


        Intent intent = new Intent(this, OneActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        Intent mbrIntent = new Intent(this, MBR.class);
        PendingIntent mbrPendingIntent = PendingIntent.getBroadcast(this, 0, mbrIntent, 0);


        mBuilder = new NotificationCompat.Builder(this, "c1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("My notification")
                .setGroup("ook")
                .setAutoCancel(true)
                .setContentText("Hello World!")
                .setProgress(0, 0, false)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.w1, "MBR", mbrPendingIntent);


        notificationManager.notify(mId, mBuilder.build());
    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");


//        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        mNotificationManager.cancel(1);

//        incr += 20;
//        mBuilder.setProgress(0, 0, false);


//        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        mNotificationManager.notify(mId, mBuilder.build());
//        PendingIntent pendingIntent;
//        Intent resultIntent = new Intent(this, MainActivity.class);
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//        stackBuilder.addNextIntent(resultIntent);


//        PendingIntent pendingIntent;
//        pendingIntent = PendingIntent.getActivity(
//                this, 1,
//                new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
//        System.out.println(pendingIntent);
//
//        PendingIntent.OnFinished finishFn = new PendingIntent.OnFinished() {
//            @Override
//            public void onSendFinished(PendingIntent pendingIntent, Intent intent, int resultCode, String resultData, Bundle resultExtras) {
//                System.out.println("~~ onFinish ~~");
//                try {
//                    Thread.sleep(2000l);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(resultCode);
//            }
//        };
//
//        Handler handler = new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                System.out.println(msg);
//            }
//        };
//
//        try {
//            pendingIntent.send(2, finishFn, handler);
//        } catch (PendingIntent.CanceledException e) {
//            e.printStackTrace();
//        }


//        pendingIntent.cancel();
//        try {
//            pendingIntent.send();
//        } catch (PendingIntent.CanceledException e) {
//            e.printStackTrace();
//        }


    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");

    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void del(View view) {
        System.out.println("~~button.del~~");

    }


    public void query(View view) {
        System.out.println("~~button.query~~");

    }

}
