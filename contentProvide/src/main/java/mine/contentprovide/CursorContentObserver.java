package mine.contentprovide;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;


/**
 * Created by Administrator on 2018/7/10.
 */

public class CursorContentObserver extends ContentObserver {
    public CursorContentObserver(Handler handler) {
        super(handler);
        System.out.println("--- " + getClass().getSimpleName() + ".constructor ---");
//        handler.post()

    }

    @Override
    public boolean deliverSelfNotifications() {
        System.out.println("... " + getClass().getSimpleName() + ".deliverSelfNotifications ...");
        return true;
//        return super.deliverSelfNotifications();

    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        System.out.println("... " + getClass().getSimpleName() + ".onChange1 ...");

    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
        System.out.println("... " + getClass().getSimpleName() + ".onChange2 ...");

    }
}
