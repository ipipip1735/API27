package mine.sqlitedb;

import android.database.AbstractCursor;
import android.database.ContentObserver;
import android.os.Handler;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/4/14.
 */

public class MyContentObserver extends ContentObserver {
    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public MyContentObserver(Handler handler) {
        super(handler);
    }


    @Override
    public boolean deliverSelfNotifications() {
        System.out.println("-------   Self Notifications    -------");
        return false;
    }

    @Override
    public void onChange(boolean selfChange) {

        System.out.println("-------   MyContentObserver on change   -------");
    }



}


