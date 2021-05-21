package mine.hilt.module;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.migration.DisableInstallInCheck;

/**
 * Created by Administrator on 2021/5/8.
 */
@DisableInstallInCheck
@Module
public class ExecutorModule {
    @Provides
    static public Executor executor() {
        return Executors.newSingleThreadExecutor();
    }
}