package mine.hilt.module;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.migration.DisableInstallInCheck;
import dagger.multibindings.IntoSet;
import mine.hilt.data.Box;
import mine.hilt.data.Openable;
import mine.hilt.data.Snow;
import mine.hilt.data.Window;

/**
 * Created by Administrator on 2021/1/21 17:48.
 */
@Module
@DisableInstallInCheck
public abstract class DaggerAModule {

    @Binds
    @IntoSet
    public abstract Openable box(Box box);

//    @Binds
//    @IntoSet
//    public abstract Openable window(Window window);
}