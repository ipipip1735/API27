package mine.hilt.module;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.migration.DisableInstallInCheck;
import dagger.multibindings.ClassKey;
import dagger.multibindings.ElementsIntoSet;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import mine.hilt.data.Box;
import mine.hilt.data.Openable;
import mine.hilt.data.Window;

/**
 * Created by Administrator on 2021/2/8 19:48.
 */
@Module
@DisableInstallInCheck
public class DaggerDModule {

    @Provides
    @IntoMap @StringKey("box")
    public Openable box(Box box) {
        return box;
    }

    @Provides
    @IntoMap @ClassKey(Window.class)
    public Openable window() {
        return new Window();
    }
}