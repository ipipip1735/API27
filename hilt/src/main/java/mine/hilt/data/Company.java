package mine.hilt.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.scopes.ActivityScoped;

/**
 * Created by Administrator on 2020/12/24 19:07.
 */
@ActivityScoped
//@Singleton
public class Company {
    @Inject
    public Company() {
        System.out.println("~~Company.Company~~");
        System.out.println(this);
    }
}
