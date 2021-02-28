package mine.hilt.module;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.scopes.ActivityScoped;
import dagger.hilt.migration.DisableInstallInCheck;
import mine.hilt.data.Milk;

/**
 * Created by Administrator on 2021/2/25 15:10.
 */
@DisableInstallInCheck
@Module
public class FatherModule {
    public FatherModule() {
        System.out.println("~~FatherModule~~");
    }

    @Provides
    public static Milk provideMilk() {
        System.out.println("~~FiveModule.provideMilk~~");
        return new Milk();
    }
}
