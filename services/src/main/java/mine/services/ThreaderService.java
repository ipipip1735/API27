package mine.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2018/1/9.
 */
public class ThreaderService extends IntentService {
    public ThreaderService() {
        super("ThreaderService");
        System.out.println("+++ " + getClass().getSimpleName() + ".BaseService +++");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        System.out.println("---- " + getClass().getSimpleName() + ".onHandleIntent ----");

        System.out.println(Thread.currentThread() + "|id=" + Thread.currentThread().getId());
    }
}
