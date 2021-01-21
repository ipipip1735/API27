package mine.hilt.component;

import dagger.Component;
import dagger.hilt.migration.DisableInstallInCheck;
import mine.hilt.SevenActivity;
import mine.hilt.SixActivity;
import mine.hilt.module.DaggerModule;

/**
 * Created by Administrator on 2021/1/21 17:52.
 */
@Component(modules = DaggerModule.class)
public interface ThreeComponent {
    void inject(SevenActivity sevenActivity);
}
