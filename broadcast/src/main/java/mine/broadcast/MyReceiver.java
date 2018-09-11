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
        System.out.println("-=-=-=-=-=-=-=-=  broadcast receiver  =-=-=-=-=-=-=-=-");
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
//        getAbortBroadcast();
        System.out.println(intent.getExtras().get("mReceiver"));

        setResultCode(5);

        System.out.println(getResultCode());



//        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
//
//                System.out.println("msg is " + msg.obj);
//                PendingResult pendingResult = (PendingResult) msg.obj;
//                System.out.println(pendingResult.getResultCode() );
//                return false;
//            }
//        });
//        Message message = new Message();
//        message.obj = goAsync();
//
//        handler.sendMessage(message);








//        PendingResult pendingResult = this.goAsync();
//        pendingResult.finish();
//        String s = intent.getExtras().getString("mReceiver");
//        System.out.println(s);

    }
}
