package mine.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;


/**
 * Created by Administrator on 2018/9/12.
 */
public class BaseViewModel extends ViewModel {
    private MutableLiveData<String> users;

    public MutableLiveData<String> getUsers() {
        if (users == null) {
            users = new MutableLiveData<String>(){
                @Override
                protected void onActive() {
                    System.out.println("~~ onActive ~~");

                }

                @Override
                protected void onInactive() {
                    super.onInactive();
                    System.out.println("~~ onInactive ~~");
                }
            };
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        System.out.println(Thread.currentThread());
        // Do an asynchronous operation to fetch users.
    }

}
