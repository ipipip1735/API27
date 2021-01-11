package mine.navigation;

import androidx.lifecycle.ViewModel;

import java.util.Random;


/**
 * Created by Administrator on 2021/1/11.
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
