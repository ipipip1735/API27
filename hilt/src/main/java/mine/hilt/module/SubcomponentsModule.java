package mine.hilt.module;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.migration.DisableInstallInCheck;
import mine.hilt.component.FatherComponent;
import mine.hilt.component.SonComponent;

/**
 * Created by Administrator on 2021/1/31.
 */
@DisableInstallInCheck
@Module(subcomponents = SonComponent.class)
public class SubcomponentsModule {
}
