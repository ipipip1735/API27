package mine.connectivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2018/12/20.
 */
public class DownloadReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("~~onReceive~~");
        System.out.println("intent action is " + intent.getAction());
    }
}