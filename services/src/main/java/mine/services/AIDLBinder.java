package mine.services;

import android.os.Parcel;
import android.os.RemoteException;



/**
 * Created by Administrator on 2018/7/28.
 */

public class AIDLBinder extends IRemoteService.Stub {
    @Override
    public int basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
        System.out.println("Service|" + getClass().getSimpleName() + ".basicTypes");
        System.out.println(Thread.currentThread());
        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 777;
    }

    @Override
    public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        System.out.println("code is " + code);
        System.out.println("data is " + data);
        System.out.println("code is " + code);
        System.out.println("reply is " + reply);
        System.out.println("flags is " + flags);
        return super.onTransact(code, data, reply, flags);
    }
}
