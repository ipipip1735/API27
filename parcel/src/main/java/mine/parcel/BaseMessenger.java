package mine.myparcel;

import android.os.Handler;

/**
 * Created by Administrator on 2017/4/21.
 */

public class BaseMessenger {

    mine.myparcel.BaseMessenger messenger = null;

    public BaseMessenger(Handler handler) {
        System.out.println(" my BaseMessenger");
        messenger = new mine.myparcel.BaseMessenger(handler);
    }

    public void go() {


//        messenger.send();


    }
}
