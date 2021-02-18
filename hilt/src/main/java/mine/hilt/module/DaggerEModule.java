package mine.hilt.module;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.migration.DisableInstallInCheck;
import dagger.multibindings.IntoMap;
import mine.hilt.data.Box;
import mine.hilt.data.Color;
import mine.hilt.annotation.ColorKey;
import mine.hilt.annotation.IntClassKey;
import mine.hilt.data.Openable;
import mine.hilt.data.Window;

/**
 * Created by Administrator on 2021/2/8 19:48.
 */
@Module
@DisableInstallInCheck
public class DaggerEModule {

    @Provides @IntoMap
    @ColorKey(Color.RED)
    public Openable box(Box box) {
        return box;
    }

    @Provides @IntoMap
    @IntClassKey(Integer.class)
    public Openable window(Window window) {
        return window;
    }
}