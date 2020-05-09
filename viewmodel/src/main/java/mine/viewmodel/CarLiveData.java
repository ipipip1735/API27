package mine.viewmodel;

import android.arch.lifecycle.MutableLiveData;


/**
 * Created by Administrator on 2018/9/13.
 */
public class CarLiveData extends MutableLiveData<Car> {
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

    @Override
    protected void onActive() {
        System.out.println("~~ onActive ~~");
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        System.out.println("~~ onInactive ~~");
    }

}
