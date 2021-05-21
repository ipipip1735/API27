package mine.hilt.module;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.migration.DisableInstallInCheck;
import mine.hilt.data.Mars;
import mine.hilt.data.Sun;

/**
 * Created by Administrator on 2021/5/6.
 */
@DisableInstallInCheck
@Module
public class ChildModule {
    @Provides
    static Mars mars() {
        return new Mars();
    }
}
