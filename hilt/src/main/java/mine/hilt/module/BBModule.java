package mine.hilt.module;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.migration.DisableInstallInCheck;
import mine.hilt.data.Door;

/**
 * Created by Administrator on 2021/2/2 18:43.
 */
@Module
@DisableInstallInCheck
public class BBModule {

    @Provides
    public Door door() {
        return new Door();
    }
}
