package mine.hilt.module;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.migration.DisableInstallInCheck;
import mine.hilt.data.Milk;
import mine.hilt.data.Sun;

/**
 * Created by Administrator on 2021/5/6.
 */
@DisableInstallInCheck
@Module
public class MotherModule {
    @Provides
    static Sun sun() {
        return new Sun();
    }
}
