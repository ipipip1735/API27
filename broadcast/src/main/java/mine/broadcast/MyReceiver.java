package mine.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

/**
 * Created by Administrator on 2017/4/18.
 */

public class MyReceiver extends BroadcastReceiver {


    public MyReceiver() {
        super();
        System.out.println("-=-=-=-=-=-=-=-=  MyReceiver'Constructor  =-=-=-=-=-=-=-=-");
        ToolClass.showThread();

    }

    @Override
    public IBinder peekService(Context myContext, Intent service) {
        System.out.println("-=-=-=-=-=-=-=-=  peekService  =-=-=-=-=-=-=-=-");
        return super.peekService(myContext, service);

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("-=-=-=-=-=-=-=-=  onReceive  =-=-=-=-=-=-=-=-");
        ToolClass.showThread();

//        order(); //有序广播
        sync(); //异步任务


    }

    private void sync() {

        //方式一
        final PendingResult pendingResult = goAsync();

        new Thread(new Runnable() {
            int i = 0;

            @Override
            public void run() {
                try {
                    while (i++ < 10) {
                        Thread.sleep(1000L);
                        System.out.println(i + "|" + Thread.currentThread());
                        if (i > 3) pendingResult.finish();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

//提前销毁
        //方式二
//        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
//
//                System.out.println("msg is " + msg.obj);
//                PendingResult pendingResult = (PendingResult) msg.obj;
//                System.out.println(pendingResult.getResultCode());
//                return false;
//            }
//        });
//        Message message = new Message();
//        message.obj = goAsync(); //获取PendingResult对象
//
//        handler.sendMessage(message);
//
//
//        PendingResult pendingResult = this.goAsync();
//        pendingResult.finish();  //销毁PendingResult对象
    }

    private void order() {
        System.out.println("getAbortBroadcast is " + getAbortBroadcast());
//        abortBroadcast();//终止广播
        System.out.println("getAbortBroadcast is " + getAbortBroadcast());


//        System.out.println(intent.getExtras().get("mReceiver"));

        System.out.println("getResultCode is " + getResultCode());
        setResultCode(5);//设置结果码
        System.out.println("getResultCode is " + getResultCode());
    }
}
