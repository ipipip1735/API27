package mine.databinding.data;

import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by Administrator on 2020/11/4.
 */
public class TheViewModel extends ViewModel{

    private MutableLiveData<User> user;

    public TheViewModel() {
        System.out.println("---- " + getClass().getSimpleName() + ".Constructor ----");
    }

    public MutableLiveData<User> getUser() {
        System.out.println("~~~ " + getClass().getSimpleName() + ".getUser ~~~");
        return user;
    }

    public void setUser(MutableLiveData<User> user) {
        System.out.println("~~~ " + getClass().getSimpleName() + ".setUser ~~~");
        this.user = user;
    }
}
