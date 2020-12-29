package mine.hilt.module;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import mine.hilt.data.Grass;
import mine.hilt.data.Water;

/**
 * Created by Administrator on 2020/12/29 16:52.
 */
@InstallIn(ActivityComponent.class)
@Module
public class SixModule {

    @Provides
    public static Grass provideGrass() {
        return new Grass();
    }
}
