package mine.hilt.module;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.migration.DisableInstallInCheck;
import dagger.multibindings.IntoSet;
import mine.hilt.data.Moon;
import mine.hilt.data.Openable;
import mine.hilt.data.Snow;
import mine.hilt.data.Window;

/**
 * Created by Administrator on 2021/5/6.
 */
@Module
@DisableInstallInCheck
public abstract class DependencyModule {
    @Provides
    public static Snow snow(){
        return new Snow();
    }

    @Provides
    public static Moon moon(){
        return new Moon();
    }
}
