package mine.viewmodel;

import android.arch.lifecycle.MutableLiveData;


/**
 * Created by Administrator on 2018/9/13.
 */
public class UserLiveData extends MutableLiveData<Car> {
    private static UserLiveData sInstance;

    public static UserLiveData get() {
        if (sInstance == null) {
            sInstance = new UserLiveData();
        }
        return sInstance;
    }

    private UserLiveData() {
    }
}
