package mine.hilt.data;

import javax.inject.Inject;

import dagger.hilt.android.scopes.ActivityRetainedScoped;
import dagger.hilt.android.scopes.ActivityScoped;

/**
 * Created by Administrator on 2021/1/4 17:52.
 */
//@ActivityScoped
//@ActivityRetainedScoped
public class Milk {

    @Inject
    public Milk() {
        System.out.println("~~Milk.Milk~~");
    }
}
