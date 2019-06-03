package mine.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;
import java.util.Random;


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
        // Do an asynchronous operation to fetch users.
        System.out.println(Thread.currentThread());
        users.postValue("User" + new Random().nextInt(100));
    }

    @Override
    protected void onCleared() {
        System.out.println("~~onCleared~~");
        super.onCleared();
    }
}
