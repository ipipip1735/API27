package mine.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2018/7/31.
 */

public class MBR extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("**********  " + getClass().getSimpleName() + ".onReceive  ***********");

    }
}
