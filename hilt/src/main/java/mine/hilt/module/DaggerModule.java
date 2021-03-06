package mine.hilt.module;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.migration.DisableInstallInCheck;
import mine.hilt.component.ThreeComponent;
import mine.hilt.data.Box;
import mine.hilt.data.Openable;
import mine.hilt.data.Pet;
import mine.hilt.data.Snow;

/**
 * Created by Administrator on 2021/1/21 17:48.
 */
@Module
@DisableInstallInCheck
public abstract class DaggerModule {

//    @Provides
//    public Snow snow() {
//        System.out.println("~~DaggerModule.snow~~");
//        Snow snow = new Snow();
//        System.out.println("DaggerModule|snow = " + snow);
//        return snow;
//    }

    @Binds
    public abstract Openable openable(Box box);

}