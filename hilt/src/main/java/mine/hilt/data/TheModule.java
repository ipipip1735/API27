package mine.hilt.data;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * Created by Administrator on 2020/12/22.
 */
@Module
@InstallIn(ActivityComponent.class)
public class TheModule {

    @Provides
    public static Person providePerson() {
        System.out.println("~~TheModule.providePerson~~");
        return new Person();
    }
}
