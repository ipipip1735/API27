package mine.services;

import android.os.Binder;
import android.os.Handler;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        System.out.println("~~ " + getClass().getSimpleName() + ".onTransact ~~");

        System.out.println("code is " + code);
        System.out.println("data is " + data);
        System.out.println("reply is " + reply);
        System.out.println("flags is " + flags);
        return super.onTransact(code, data, reply, flags);
    }
}
