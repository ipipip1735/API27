package mine.sensors;

import android.app.IntentService;
import android.content.Intent;

import org.jetbrains.annotations.Nullable;

/**
 * Created by Administrator on 2018/12/25.
 */
public class FetchAddressIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public FetchAddressIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
