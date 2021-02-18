package mine.hilt.module;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.migration.DisableInstallInCheck;
import dagger.multibindings.IntoSet;
import mine.hilt.data.Box;
import mine.hilt.data.Openable;
import mine.hilt.data.Window;

/**
 * Created by Administrator on 2021/2/12.
 */
@Module
@DisableInstallInCheck
public abstract class DDModule {
    @Binds
    @IntoSet
    abstract Openable window(Window window);
}
