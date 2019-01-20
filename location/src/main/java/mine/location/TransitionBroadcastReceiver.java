package mine.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.location.ActivityTransitionEvent;
import com.google.android.gms.location.ActivityTransitionResult;

/**
 * Created by Administrator on 2019/1/19.
 */
public class TransitionBroadcastReceiver extends BroadcastReceiver {
    public TransitionBroadcastReceiver() {
        System.out.println("~~TransitionBroadcastReceiver.Constructor~~");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("~~onReceive~~");

        if (ActivityTransitionResult.hasResult(intent)) {
            ActivityTransitionResult result = ActivityTransitionResult.extractResult(intent);
            for (ActivityTransitionEvent event : result.getTransitionEvents()) {
                System.out.println(event);
            }
        }
    }
}
