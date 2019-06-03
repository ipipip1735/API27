package mine.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Icon;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;


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


        BasicNotify(notificationManager); //创建通知
//        ComponentNotify(notificationManager); //组件通知(点击通知启动组件)
//        StackNotify(notificationManager); //回退栈通知

//        groupNotify(notificationManager); //通知分组
//        actionNotify(notificationManager); //附加任通知
//        progressNotify(notificationManager); //进度条通知

//        StyleNotify(notificationManager); //样式通知
//        mediaStyleNotify(notificationManager); //媒体样式通知
//        customNotify(notificationManager); //自定义样式通知
//        foldNotify(notificationManager); //可折叠通知


    }

    private void ComponentNotify(NotificationManager notificationManager) {


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
                .setContentIntent(pendingIntent); //设置点击通知后要启动的Intent
        notificationManager.notify(mId, mBuilder.build());

    }

    private void StyleNotify(NotificationManager notificationManager) {

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
//        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle()
//                .addLine("XXX")
//                .addLine("YYY")
//                .addLine("ZZZ")
//                .setBigContentTitle(title)
//                .setSummaryText(subText);


        //消息样式
//        NotificationCompat.MessagingStyle style = new NotificationCompat.MessagingStyle("userName")
//                .addMessage("one", System.currentTimeMillis(), "sam")
//                .addMessage("two", System.currentTimeMillis(), "bob")
//                .addMessage("one one", System.currentTimeMillis(), "sam")
//                .addMessage("one one one", System.currentTimeMillis(), "sam")
//                .addMessage("虎虎虎", System.currentTimeMillis(), "王")
//                .setConversationTitle("conversation");
//
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
//                .setStyle(style)
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

    private void StackNotify(NotificationManager notificationManager) {

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "c1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("My notizfication")
                .setContentText("Hello World!")
                .setAutoCancel(true);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(OneActivity.class);
        stackBuilder.addNextIntent(new Intent(this, OneActivity.class)); //增加第一个Activity
        stackBuilder.addNextIntent(new Intent(this, MainActivity.class)); //增加第二个Activity
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_ONE_SHOT);
        mBuilder.setContentIntent(resultPendingIntent);


        notificationManager.notify(mId, mBuilder.build());
    }

    private void BasicNotify(NotificationManager notificationManager) {


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, "c1")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("ttt")
                        .setContentText("Hello World!")
//                        .setContentInfo("OOOOO")//本方法API24+已经废弃，请使用setSubText()代替
                        .setSubText("stststst");
        notificationManager.notify(mId, mBuilder.build());


//        String subText = "sssssssssssssssssssssssssssssssssssssssssssssssss" +
//                "sssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
//                "sssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
//                "sssssssssssssssssssssssssssssssssssssssssssssssssssssss";
//
//        String title = "ttttttttttttttttttttttttttttttttttttttttttttttttt" +
//                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttt" +
//                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttt" +
//                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttt";
//
//        String context = "ccccccccccccccccccccccccccccccccccccccccccccccccc" +
//                "ccccccccccccccccccccccccccccccccccccccccccccccccccccccc" +
//                "ccccccccccccccccccccccccccccccccccccccccccccccccccccccc" +
//                "ccccccccccccccccccccccccend";
//
//        NotificationCompat.Builder mBuilder =
//                new NotificationCompat.Builder(this, "c1")
//                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setContentTitle(title)
//                .setContentText(context)
//                .setSubText(subText);
//        notificationManager.notify(mId, mBuilder.build());


    }

    private void customNotify(NotificationManager notificationManager) {

        //部分自定义
//        Bitmap gg = BitmapFactory.decodeResource(getResources(), R.drawable.gg);
//        Bitmap ff = BitmapFactory.decodeResource(getResources(), R.drawable.ff);
//
//        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.notification_small); //折叠时样式
//        RemoteViews notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.notification_large); //展开后样式
////        notificationLayoutExpanded.setBitmap(R.id.imageView, "setImageBitmap", ff); //运行时，动态修改子元素

//        Notification customNotification = new NotificationCompat.Builder(this, "c1")
//                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setLargeIcon(gg)
//                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
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
        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.notification_small);
        RemoteViews notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.notification_large);

        Notification customNotification = new NotificationCompat.Builder(this, "c1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContent(notificationLayout)
                .setCustomContentView(notificationLayout)
                .setCustomBigContentView(notificationLayoutExpanded)
                .build();
        notificationManager.notify(mId, customNotification);


    }

    private void groupNotify(NotificationManager notificationManager) {

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "c1");
        Notification notification1 = mBuilder.setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("n1")
                .setContentText("aaaaaaaaaa")
                .setGroup("myGroup") //指定所属的组
                .setNumber(15)
                .setAutoCancel(true)
                .build();

        Notification notification2 = mBuilder.setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("n2")
                .setContentText("bbbbbbbbbbbbbb")
                .setGroup("myGroup") //指定所属的组
                .setAutoCancel(true)
                .build();

        Notification groupNotify = mBuilder.setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("GG")
                .setContentText("n1, n2")
                .setGroup("myGroup")
                .setAutoCancel(true)
                .setGroupSummary(true) //设置为组通知
                .build();
        notificationManager.notify(3, groupNotify);
        notificationManager.notify(1, notification1);
        notificationManager.notify(2, notification2);

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
                .addAction(new Notification.Action.Builder(icon, "CCC", mbrPendingIntent).build());

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
                .setShowActionsInCompactView(1, 0);


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
//                .setContentTitle("cccccccc")
//                .setContentText("tttttttttt")
                .setProgress(100, 10, false)
                .setContentIntent(pendingIntent);

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

    }


    public void query(View view) {
        System.out.println("~~button.query~~");

    }

}
