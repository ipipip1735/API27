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
 * Created by Administrator on 2021/2/7 04:57.
 */
@Module
@DisableInstallInCheck
public class DaggerBModule {

//    @Binds
//    @IntoSet
//    public abstract Openable box(Box box);

    @Provides
    @IntoSet
    public Openable window(Window window){
        return window;
    }
}