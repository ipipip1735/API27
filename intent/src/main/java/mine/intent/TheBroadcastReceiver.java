package mine.intent;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2021/3/19.
 */
public class TheBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("TheBroadcastReceiver.onReceive");
        System.out.println("context = " + context + ", intent = " + intent);

        ComponentName componentName = (ComponentName) intent.getExtras().get(Intent.EXTRA_CHOSEN_COMPONENT);
        System.out.println("componentName = " + componentName);

    }
}
