package mine.databinding.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by Administrator on 2020/11/4.
 */
public class UserViewModel extends ViewModel {
    public MutableLiveData<User> getUser() {
        System.out.println("~~~ " + getClass().getSimpleName() + ".getUser ~~~");
        return user;
    }

    private MutableLiveData<User> user;

    public void setUser(MutableLiveData<User> user) {
        System.out.println("~~~ " + getClass().getSimpleName() + ".setUser ~~~");
        this.user = user;
    }

    public UserViewModel() {
        System.out.println("---- " + getClass().getSimpleName() + ".Constructor ----");
    }
}
