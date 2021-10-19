package mine.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
        async(); //异步任务


    }

    private void async() {
        PendingResult pendingResult = goAsync();

        //方式一
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    System.out.println("pendingResult = " + pendingResult);
//                    for (int i = 0; i < 10; i++) {
//                        Thread.sleep(1000L);
//                        System.out.println(i + "|" + Thread.currentThread());
//                        if (i == 3) pendingResult.finish();//提前销毁
//                    }
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();


        //方式二
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        Thread.sleep(1000L);
                        System.out.println(i + "|" + Thread.currentThread());

                        if (i == 3) {
                            Message message = new Message();
                            Bundle bundle = new Bundle();
                            bundle.putInt("one", 111);
                            pendingResult.setResult(999, "AAA", bundle);
                            message.obj = pendingResult;

                            new Handler(Looper.getMainLooper(), new Handler.Callback() {
                                @Override
                                public boolean handleMessage(Message msg) {

                                    System.out.println("msg is " + msg.obj);
                                    PendingResult pendingResult = (PendingResult) msg.obj;
                                    System.out.println("getResultCode is " + pendingResult.getResultCode());
                                    System.out.println("getResultData is " + pendingResult.getResultData());

                                    Bundle bundle = pendingResult.getResultExtras(true);
                                    System.out.println(bundle.getInt("one"));

                                    pendingResult.finish();  //销毁PendingResult对象
                                    return true;
                                }
                            }).sendMessage(message);
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

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
