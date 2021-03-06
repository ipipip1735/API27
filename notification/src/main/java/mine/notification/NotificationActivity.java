package mine.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

import static android.app.Notification.BADGE_ICON_SMALL;


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
//        int importance = NotificationManager.IMPORTANCE_LOW;
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
//        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
        notificationChannel.setShowBadge(false);
        notificationManager.createNotificationChannel(notificationChannel);


//        basicNotify(notificationManager); //创建通知
//        componentNotify(notificationManager); //组件通知(点击通知启动组件)
//        stackNotify(notificationManager); //回退栈通知
//
//        groupNotify(notificationManager); //通知分组
//        actionNotify(notificationManager); //附加任务通知
//        progressNotify(notificationManager); //进度条通知
//
//        styleNotify(notificationManager); //样式通知
        mediaStyleNotify(notificationManager); //媒体样式通知
//        fullScreenNotify(notificationManager); //全屏通知
//        customNotify(notificationManager); //自定义样式通知
//        foldNotify(notificationManager); //可折叠通知
//        visibleNotify(notificationManager);//锁屏通知

    }

    private void fullScreenNotify(NotificationManager notificationManager) {

        Intent intent = new Intent(this, OneActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "c1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("ctitle")
                .setContentText("cText")
                .setSubText("sText")
                .setFullScreenIntent(pendingIntent, true); //启动全屏通知，并设置点击通知后要启动的Intent
        notificationManager.notify(mId, mBuilder.build());
    }

    private void visibleNotify(NotificationManager notificationManager) {


        Bitmap gg = BitmapFactory.decodeResource(getResources(), R.drawable.gg);
        Bitmap kk = BitmapFactory.decodeResource(getResources(), R.drawable.kk);
        Bitmap ff = BitmapFactory.decodeResource(getResources(), R.drawable.ff);


        String subText = "sssssssssssssssssssssssssssssssssssssssssssssssss" +
                "sssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                "sssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                "sssssssssssssssssssssssssssssssssssssssssssssssssssssss";

        String title = "ttttttttttttttttttttttttttttttttttttttttttttttttt" +
                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttt" +
                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttt" +
                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttt";

        String context = "ccccccccccccccccccccccccccccccccccccccccccccccccc" +
                "ccccccccccccccccccccccccccccccccccccccccccccccccccccccc" +
                "ccccccccccccccccccccccccccccccccccccccccccccccccccccccc" +
                "ccccccccccccccccccccccccend";


        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle()//长文本样式

                .setSummaryText(subText)
                .setBigContentTitle(title)
                .bigText(context);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "c1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setVisibility(Notification.VISIBILITY_SECRET)
//                .setVisibility(Notification.VISIBILITY_PRIVATE)//默认值，显示通知图标和内容标题等基本信息，但是隐藏通知的完整内容
                .setStyle(style)
                .setContentTitle("cTitle")
                .setSubText("sbsbsb")
                .setContentText("cText");
        notificationManager.notify(mId, mBuilder.build());

    }

    private void componentNotify(NotificationManager notificationManager) {


        //Activity/Services/Broadcast都能包装为PendingIntent，点击通知后就能启动
        //方式一：将Activity包装为PendingIntent
        Intent intent = new Intent(this, OneActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //方式二：将Broadcast包装为PendingIntent
//        Intent intent = new Intent(this, MBR.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "c1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Start Component")
                .setContentText("start Activity/Service/Broadcast")
                .setSubText("start")
//                .setVisibility(Notification.VISIBILITY_PRIVATE)
                .setContentIntent(pendingIntent); //设置点击通知后要启动的Intent
        notificationManager.notify(mId, mBuilder.build());

    }

    private void styleNotify(NotificationManager notificationManager) {

        Bitmap gg = BitmapFactory.decodeResource(getResources(), R.drawable.gg);
        Bitmap kk = BitmapFactory.decodeResource(getResources(), R.drawable.kk);
        Bitmap ff = BitmapFactory.decodeResource(getResources(), R.drawable.ff);


        String subText = "sssssssssssssssssssssssssssssssssssssssssssssssss" +
                "sssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                "sssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                "sssssssssssssssssssssssssssssssssssssssssssssssssssssss";

        String title = "ttttttttttttttttttttttttttttttttttttttttttttttttt" +
                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttt" +
                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttt" +
                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttt";

        String context = "ccccccccccccccccccccccccccccccccccccccccccccccccc" +
                "ccccccccccccccccccccccccccccccccccccccccccccccccccccccc" +
                "ccccccccccccccccccccccccccccccccccccccccccccccccccccccc" +
                "ccccccccccccccccccccccccend";


        //长文本样式
//        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle()
//                .setSummaryText(subText)
//                .setBigContentTitle(title)
//                .bigText(context);


        //大图片样式
//        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle()
//                .bigPicture(kk)
//                .bigLargeIcon(gg)
//                .setBigContentTitle(title)
//                .setSummaryText(subText);


        //短信样式
        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle()
                .addLine("XXX")
                .addLine("YYY")
                .addLine("ZZZ")
                .setBigContentTitle(title)
                .setSummaryText(subText);


        //消息样式
//        NotificationCompat.MessagingStyle style = new NotificationCompat.MessagingStyle("userName")
//                .addMessage("one", System.currentTimeMillis(), "sam")
//                .addMessage("two", System.currentTimeMillis(), "bob")
//                .addMessage("one one", System.currentTimeMillis(), "sam")
//                .addMessage("one one one", System.currentTimeMillis(), "sam")
//                .addMessage("虎虎虎", System.currentTimeMillis(), "王")
//                .setConversationTitle("conversation");

//        System.out.println("getUserDisplayName is " + style.getUserDisplayName());
//        System.out.println("getConversationTitle is " + style.getConversationTitle());
//        System.out.println("getMessages is " + style.getMessages());


        //媒体样式
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, "c1")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setLargeIcon(ff)
                        .setSubText("subText") //优先级比NotificationCompat.BigPictureStyle.setSummaryText()高
                        .setContentTitle("title")
                        .setContentText("context")
                        .setStyle(style)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(mId, mBuilder.build());

    }

    private void foldNotify(NotificationManager notificationManager) {

        Bitmap gg = BitmapFactory.decodeResource(getResources(), R.drawable.gg);
        Bitmap kk = BitmapFactory.decodeResource(getResources(), R.drawable.kk);
        Bitmap ff = BitmapFactory.decodeResource(getResources(), R.drawable.ff);


        String subText = "sssssssssssssssssssssssssssssssssssssssssssssssss" +
                "sssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                "sssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                "sssssssssssssssssssssssssssssssssssssssssssssssssssssss";
        String title = "ttttttttttttttttttttttttttttttttttttttttttttttttt" +
                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttt" +
                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttt" +
                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttt";
        String context = "ccccccccccccccccccccccccccccccccccccccccccccccccc" +
                "ccccccccccccccccccccccccccccccccccccccccccccccccccccccc" +
                "ccccccccccccccccccccccccccccccccccccccccccccccccccccccc" +
                "ccccccccccccccccccccccccccccccccccccccccccccccccccccccc" +
                "ccccccccccccccccccccccccccccccccccccccccccccccccccccccc" +
                "ccccccccccccccccccccccccend";


        NotificationCompat.BigTextStyle style =
                new NotificationCompat.BigTextStyle()
                        .setBigContentTitle(title)
                        .setSummaryText(subText)
                        .bigText(context);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "c1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(kk)
                .setContentTitle("title")
                .setContentText("content")
                .setSubText("sub")
                .setStyle(style) //增加长文本样式，通知将自动使用折叠模式
                .setAutoCancel(true);

        notificationManager.notify(mId, mBuilder.build());


    }

    private void stackNotify(NotificationManager notificationManager) {

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "c1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("My notizfication")
                .setContentText("Hello World!")
                .setAutoCancel(true);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);//将给启动的Intent创建新的Task栈
        stackBuilder.addParentStack(OneActivity.class);//增加OneActivity的父Activity，即NotificationActivity
        stackBuilder.addNextIntent(new Intent(this, OneActivity.class)); //增加第一个Activity
        stackBuilder.addNextIntent(new Intent(this, TwoActivity.class)); //增加第二个Activity
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);


        notificationManager.notify(mId, mBuilder.build());
    }

    private void basicNotify(NotificationManager notificationManager) {



//        NotificationCompat.Builder mBuilder =
//                new NotificationCompat.Builder(this, "c1")
//                        .setSmallIcon(R.drawable.ic_launcher_foreground)
////                        .setWhen(System.currentTimeMillis())//通知启动时间为当前时间（默认值就是当前时间戳，可以省略此方法）
////                        .setWhen(Instant.now().plus(Duration.ofMinutes(1)).toEpochMilli())//超过当前时间则显示now
////                        .setWhen(Instant.now().minus(Duration.ofMinutes(1)).toEpochMilli())//当前时间减1分钟
//                        .setShowWhen(false)
//                        .setContentTitle("ttt")
//                        .setContentText("Hello World!")
////                        .setContentInfo("OOOOO")//本方法API24+已经废弃，请使用setSubText()代替
//                        .setSubText("stststst");
//        notificationManager.notify(mId, mBuilder.build());


        String subText = "sssssssssssssssssssssssssssssssssssssssssssssssss" +
                "sssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                "sssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                "sssssssssssssssssssssssssssssssssssssssssssssssssssssss";

        String title = "ttttttttttttttttttttttttttttttttttttttttttttttttt" +
                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttt" +
                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttt" +
                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttt";

        String context = "ccccccccccccccccccccccccccccccccccccccccccccccccc" +
                "ccccccccccccccccccccccccccccccccccccccccccccccccccccccc" +
                "ccccccccccccccccccccccccccccccccccccccccccccccccccccccc" +
                "ccccccccccccccccccccccccend";

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, "c1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(context)
                .setSubText(subText);
        notificationManager.notify(mId, mBuilder.build());


    }

    private void customNotify(NotificationManager notificationManager) {

        //部分自定义
//        Bitmap gg = BitmapFactory.decodeResource(getResources(), R.drawable.gg);
//        Bitmap ff = BitmapFactory.decodeResource(getResources(), R.drawable.ff);
//
//        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.notification_small); //折叠时样式
//        RemoteViews notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.notification_large); //展开后样式
//        notificationLayoutExpanded.setBitmap(R.id.imageView, "setImageBitmap", ff); //运行时，动态修改子元素
//
//        Notification customNotification = new NotificationCompat.Builder(this, "c1")
//                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setLargeIcon(gg)
//                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())//使用部分自定义通知样式
//                .setCustomContentView(notificationLayout) //通知折叠时的UI
//                .setCustomBigContentView(notificationLayoutExpanded) //通知展开时的UI
//                .setSubText("sub")
////                .setContentTitle("My notification") //由于使用自定义布局，此方法无效
////                .setContentText("Hello World!") //由于使用自定义布局，此方法无效
//                .build();
//        notificationManager.notify(mId, customNotification);


        //完全自定义
        Intent intent = new Intent(this, OneActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews notificationLayout1 = new RemoteViews(getPackageName(), R.layout.notification_small);
        RemoteViews notificationLayout2 = new RemoteViews(getPackageName(), R.layout.notification_normal);
        RemoteViews notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.notification_large);

        Notification customNotification = new NotificationCompat.Builder(this, "c1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContent(notificationLayout1)//折叠时的布局
//                .setCustomContentView(notificationLayout2)//比setContent()优先级高
//                .setCustomBigContentView(notificationLayoutExpanded)//设置展开后的样式，优先级最高
                .build();
        notificationManager.notify(mId, customNotification);


    }

    private void groupNotify(NotificationManager notificationManager) {

        //单个分组
//        Notification notification1 = new NotificationCompat.Builder(this, "c1")
//                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setContentTitle("n1")
//                .setContentText("aaaaaaaaaa")
//                .setGroup("myGroup") //指定所属的组
//                .setAutoCancel(true)
//                .build();
//        Notification groupNotify = new NotificationCompat.Builder(this, "c1")
//                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setContentTitle("GG")
//                .setContentText("n1, n2")
//                .setGroup("myGroup")
//                .setAutoCancel(true)
//                .setGroupSummary(true) //设置为组简洁通知
//                .build();
//        Notification notification2 = new NotificationCompat.Builder(this, "c1")
//                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setContentTitle("n2")
//                .setContentText("bbbbbbbbbbbbbb")
//                .setGroup("myGroup") //指定所属的组
//                .setAutoCancel(true)
//                .build();
//
//        notificationManager.notify(1, notification1);
//        notificationManager.notify(3, groupNotify);
//        notificationManager.notify(2, notification2);


        //多个分组
        Notification notification10 = new NotificationCompat.Builder(this, "c1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("n10")
                .setContentText("aaaaaaaaaa")
                .setGroup("myGroup1") //指定所属的组
                .setAutoCancel(true)
                .build();
        Notification groupNotify1 = new NotificationCompat.Builder(this, "c1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("GG")
                .setContentText("n1")
                .setGroup("myGroup1")
                .setAutoCancel(true)
                .setGroupSummary(true) //设置为组简介通知
                .build();

        notificationManager.notify(10, notification10);
        notificationManager.notify(1, groupNotify1);


        Notification notification20 = new NotificationCompat.Builder(this, "c1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("n20")
                .setContentText("aaaaaaaaaa")
                .setGroup("myGroup2") //指定所属的组
                .setAutoCancel(true)
                .build();
        Notification notification21 = new NotificationCompat.Builder(this, "c1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("n21")
                .setContentText("aaaaaaaaaa")
                .setGroup("myGroup2") //指定所属的组
                .setAutoCancel(true)
                .build();
        Notification groupNotify2 = new NotificationCompat.Builder(this, "c1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("GG")
                .setContentText("n2")
                .setGroup("myGroup2")
                .setAutoCancel(true)
                .setGroupSummary(true) //设置为组简介通知
                .build();

        notificationManager.notify(20, notification20);
        notificationManager.notify(21, notification21);
        notificationManager.notify(2, groupNotify2);


    }

    private void actionNotify(NotificationManager notificationManager) {

        Bitmap kk = BitmapFactory.decodeResource(getResources(), R.drawable.kk);
        Bitmap ff = BitmapFactory.decodeResource(getResources(), R.drawable.ff);

        Icon icon = Icon.createWithResource(this, R.drawable.kk);

        Intent intent = new Intent(this, OneActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Intent mbrIntent = new Intent(this, MBR.class);
        PendingIntent mbrPendingIntent = PendingIntent.getBroadcast(this, 0, mbrIntent, 0);

        Notification.Builder mBuilder = new Notification.Builder(this, "c1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(ff)
                .setContentTitle("Action")
                .setContentText("TTTTT")
                .setSubText("ssss")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .addAction(new Notification.Action.Builder(icon, "pppp", mbrPendingIntent).build())
                .addAction(new Notification.Action.Builder(icon, "DDD", mbrPendingIntent).build())
                .addAction(new Notification.Action.Builder(icon, "CCC", pendingIntent).build());

        notificationManager.notify(mId, mBuilder.build());
    }


    private void mediaStyleNotify(NotificationManager notificationManager) {

        Bitmap kk = BitmapFactory.decodeResource(getResources(), R.drawable.kk);
        Bitmap ff = BitmapFactory.decodeResource(getResources(), R.drawable.ff);

        Icon icon = Icon.createWithResource(this, R.drawable.kk);

        Intent intent = new Intent(this, OneActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Intent mbrIntent = new Intent(this, MBR.class);
        PendingIntent mbrPendingIntent = PendingIntent.getBroadcast(this, 0, mbrIntent, 0);


        MediaSession mediaSession = new MediaSession(this, "XXX");
        Notification.MediaStyle style = new Notification.MediaStyle()
                .setShowActionsInCompactView(1);


        Notification.Builder mBuilder = new Notification.Builder(this, "c1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(ff)
                .setContentTitle("My notification")
                .setContentText("content text")
                .setSubText("ssss")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setStyle(style)
                .addAction(new Notification.Action.Builder(icon, "pppp", mbrPendingIntent).build())
                .addAction(new Notification.Action.Builder(icon, "CCC", mbrPendingIntent).build());

        notificationManager.notify(mId, mBuilder.build());
    }


    private void progressNotify(NotificationManager notificationManager) {


        Intent intent = new Intent(this, OneActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "c1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .setContentTitle("cccccccc")
                .setProgress(100, 30, false)
                .setContentText("tttttttttt")//进度条将占据文本内容
//                .setProgress(0, 0, true)
                .setContentIntent(pendingIntent);
        notificationManager.notify(mId, mBuilder.build());
        System.out.println("mid is " + mId);


        //定时更新进度条UI
//        new Thread(new Runnable() {
//            int propress = 20;
//
//            @Override
//            public void run() {
//                while (propress < 100) {
//
//                    try {
//                        Thread.sleep(1000L);
//                        notificationManager.notify(mId,
//                                mBuilder.setProgress(100, propress += 10, false).build());
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");


        //方式一：打印Notification对象
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        for (StatusBarNotification statusBarNotification : mNotificationManager.getActiveNotifications()) {
            System.out.println("getId is " + statusBarNotification.getId());
            System.out.println("getKey is " + statusBarNotification.getKey());
            System.out.println("getGroupKey is " + statusBarNotification.getGroupKey());
            System.out.println("getTag is " + statusBarNotification.getTag());
            System.out.println("getNotification is " + statusBarNotification.getNotification());
        }


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

        //创建通道
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        String channelId = "c2";
        CharSequence channelName = "cth";
        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
        notificationChannel.setShowBadge(false);
        notificationManager.createNotificationChannel(notificationChannel);


        Bitmap gg = BitmapFactory.decodeResource(getResources(), R.drawable.gg);
        Bitmap kk = BitmapFactory.decodeResource(getResources(), R.drawable.kk);
        Bitmap ff = BitmapFactory.decodeResource(getResources(), R.drawable.ff);


        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        style.bigPicture(gg)
                .bigLargeIcon(ff)
                .setBigContentTitle("ggg")
                .setSummaryText("ccccccc");

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "c2")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(kk)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setStyle(style)
                .setOngoing(false)
                .setAutoCancel(true);

        notificationManager.notify(mId + 1, mBuilder.build());

    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");

    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void del(View view) {
        System.out.println("~~button.del~~");


        //勋章通知
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        String channelId = "c1";
        CharSequence channelName = "cf";

        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
        notificationManager.createNotificationChannel(notificationChannel);


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, "c1")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setNumber(3)
                        .setContentTitle("CTitleeee")
                        .setContentText("cTexttttt")
                        .setSubText("sText");
        notificationManager.notify(new Random().nextInt(99), mBuilder.build());


    }


    public void query(View view) {
        System.out.println("~~button.query~~");
    }

}
