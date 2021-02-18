package mine.hilt.module;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.migration.DisableInstallInCheck;
import dagger.multibindings.IntoSet;
import mine.hilt.data.Box;
import mine.hilt.data.Openable;

/**
 * Created by Administrator on 2021/2/12.
 */
@Module
@DisableInstallInCheck
public class CCModule {

    @Provides
    @IntoSet
    Openable box(Box box) {
        return box;
    }
}
