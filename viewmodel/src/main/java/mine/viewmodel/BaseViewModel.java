package mine.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;


/**
 * Created by Administrator on 2018/9/12.
 */
public class BaseViewModel extends ViewModel {
    private MutableLiveData<List<Car>> users;
    public LiveData<List<Car>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<List<Car>>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        // Do an asynchronous operation to fetch users.
    }

}
