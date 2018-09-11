package mine.services;

import android.app.Service;
import android.os.Binder;
import android.os.Handler;

/**
 * Created by Administrator on 2018/7/24.
 */

public class BaseBinder extends Binder{
    Handler serviceHandler;

    public BaseBinder(Handler serviceHandler) {
        System.out.println("~~ " + getClass().getSimpleName() + ".BaseBinder ~~");
        System.out.println(Thread.currentThread());

        this.serviceHandler = serviceHandler;
    }

    public Handler getServiceHandler() {
        System.out.println("~~ " + getClass().getSimpleName() + ".getService ~~");
        System.out.println(Thread.currentThread());
        System.out.println("get'serviceHandler is " + serviceHandler);


        return serviceHandler;
    }
}
