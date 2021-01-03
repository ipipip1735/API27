package mine.hilt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import mine.hilt.data.Circle;
import mine.hilt.data.Point;

/**
 * Created by Administrator on 2021/1/2.
 */
@AndroidEntryPoint
public class TheReceiver extends BroadcastReceiver {

    @Inject
    public Point point;

    public TheReceiver() {
        System.out.println("~~TheReceiver.TheReceiver~~");

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("~~TheReceiver.onReceive~~");
        System.out.println("context = " + context + ", intent = " + intent);

        System.out.println("point = " + point);
    }
}
