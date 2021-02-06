package mine.hilt.module;

import android.util.ArraySet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.migration.DisableInstallInCheck;
import dagger.multibindings.ElementsIntoSet;
import dagger.multibindings.IntoSet;
import mine.hilt.data.Box;
import mine.hilt.data.Openable;
import mine.hilt.data.Window;

/**
 * Created by Administrator on 2021/2/7 06:26
 */
@Module
@DisableInstallInCheck
public class DaggerCModule {

    @Provides
    @ElementsIntoSet
    public Set<Openable> openables(Box box, Window window) {
    return new HashSet<>(Arrays.asList(box, window));
    }
}