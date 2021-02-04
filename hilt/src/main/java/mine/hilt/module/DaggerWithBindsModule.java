package mine.hilt.module;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.migration.DisableInstallInCheck;
import mine.hilt.data.Box;
import mine.hilt.data.Openable;
import mine.hilt.data.Snow;

/**
 * Created by Administrator on 2021/1/21 17:48.
 */
@Module
@DisableInstallInCheck
public abstract class DaggerWithBindsModule {

    @Binds
    public abstract Openable openable(Box box);
}