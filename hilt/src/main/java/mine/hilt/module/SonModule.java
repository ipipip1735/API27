package mine.hilt.module;

/**
 * Created by Administrator on 2021/2/14.
 */

import dagger.Module;
import dagger.Provides;
import dagger.hilt.migration.DisableInstallInCheck;
import mine.hilt.data.Sun;

@Module
@DisableInstallInCheck
public class SonModule {

    public SonModule(String name) {
        System.out.println("~~" + getClass().getSimpleName() + ".SonModule~~");
        System.out.println("name = " + name);
    }

    @Provides
    Sun sun() {
        return new Sun();
    }
}
