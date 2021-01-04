package mine.hilt.module;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.scopes.ActivityRetainedScoped;
import mine.hilt.data.Circle;
import mine.hilt.data.Milk;

/**
 * Created by Administrator on 2021/1/4 18:48.
 */
@InstallIn(ActivityRetainedComponent.class)
@Module
public class TenModule {
    public TenModule() {
        System.out.println("~~TenModule.TenModule~~");
    }

//    @ActivityRetainedScoped
    @Provides
    public static Milk provideMilk() {
        System.out.println("~~TenModule.provideMilk~~");
        return new Milk();
    }
}
