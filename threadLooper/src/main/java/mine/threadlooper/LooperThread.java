package mine.threadlooper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class LooperThread extends Thread {


    Looper looper;
    Handler handler;

    public Looper getLooper() {
        return looper;
    }

    public void setLooper(Looper looper) {
        this.looper = looper;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread());

        Looper.prepare();
        looper = Looper.myLooper();

//        handler = new Handler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
//                Bundle bundle = msg.getData();
//                System.out.println(bundle.getInt("one"));
//                System.out.println(Thread.currentThread());
//                return false;
//            }
//        });


        looper.loop();

    }
}

