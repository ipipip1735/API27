package mine.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.Random;


/**
 * Created by Administrator on 2018/9/12.
 */
public class StringViewModel extends ViewModel {
    private String name;

    public String getName() {
        if (name == null) {

            name = "Bob-" + new Random().nextInt(100);
        }
        return name;
    }

    @Override
    protected void onCleared() {
        System.out.println("~~onCleared~~");
        super.onCleared();
    }
}
