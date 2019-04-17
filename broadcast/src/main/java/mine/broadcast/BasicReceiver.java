package mine.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2018/7/23.
 */

public class BasicReceiver extends BroadcastReceiver {


    public BasicReceiver() {
        super();
        System.out.println("-=-=-=-=-=-=-=-=  BasicReceiver'Constructor  =-=-=-=-=-=-=-=-");

        System.out.println(this);
        ToolClass.showThread();
    }

//    @Override
//    public IBinder peekService(Context myContext, Intent service) {
//        System.out.println("-=-=-=-=-=-=-=-=  peekService  =-=-=-=-=-=-=-=-");
//        return super.peekService(myContext, service);
//
//    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("-=-=-=-=-=-=-=-=  onReceive  =-=-=-=-=-=-=-=-");
        System.out.println("action is " + intent.getAction());

        System.out.println("getAbortBroadcast is " + getAbortBroadcast());
//        System.out.println(intent.getExtras().get("mReceiver"));



//        setResultCode(5);
        System.out.println("getResultCode is " + getResultCode());


        if (getResultCode() > 5) {
            abortBroadcast();
        }


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
