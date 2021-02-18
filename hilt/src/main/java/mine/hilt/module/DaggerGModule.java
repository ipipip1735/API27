package mine.hilt.module;

import java.util.Set;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.migration.DisableInstallInCheck;
import dagger.multibindings.IntoSet;
import dagger.multibindings.Multibinds;
import mine.hilt.data.Box;
import mine.hilt.data.Openable;

/**
 * Created by Administrator on 2021/2/11.
 */
@Module
@DisableInstallInCheck
public abstract class DaggerGModule {

    @Multibinds abstract Set<Openable> openableSet();

//    @Binds
//    @IntoSet
//    abstract Openable box(Box box);

}
