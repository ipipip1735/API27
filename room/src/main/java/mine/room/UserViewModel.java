package mine.room;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;


/**
 * Created by Administrator on 2018/9/15.
 */
public class UserViewModel extends AndroidViewModel {
    private AppDatabase db;
    private LiveData<List<User>> users;
    private LiveData<List<String>> userInfo;


    public UserViewModel(@NonNull Application application) {
        super(application);
        System.out.println("++++++" + getClass().getSimpleName() + "++++++");

    }

    public AppDatabase getDb() {
        if (Objects.isNull(db))
            db = Room.databaseBuilder(getApplication().getApplicationContext(),
                    AppDatabase.class, "userDB").build();
        return db;
    }

    public LiveData<List<String>> getUserInfo() {

        if (userInfo == null) {
            if (users == null) loadUsers();
            userInfo = Transformations.map(users,
                    new Function<List<User>, List<String>>() {
                        @Override
                        public List<String> apply(List<User> input) {
                            System.out.println("~~Transformations~~");
                            List<String> info = new ArrayList<>();
                            for (User user : input) {
                                info.add(user.getFirstName());
                            }
                            return info;
                        }
                    });
        }
        return userInfo;
    }

    private void loadUsers() {
        System.out.println("Loading...");
        // Do an asynchronous operation to fetch users.
        System.out.println(Thread.currentThread());

        users = getDb().userDao().queryUserLiveData();
    }

}
