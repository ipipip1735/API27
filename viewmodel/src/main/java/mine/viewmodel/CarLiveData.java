package mine.viewmodel;

import android.arch.lifecycle.LiveData;


/**
 * Created by Administrator on 2018/9/13.
 */
public class CarLiveData extends LiveData<Car> {
    private static CarLiveData sInstance;

    public static CarLiveData get() {
        if (sInstance == null) {
            sInstance = new CarLiveData();
        }
        return sInstance;
    }

    private CarLiveData() {
    }

//    public void setValue(Car car) {
//        super.setValue(car);
//    }
}
