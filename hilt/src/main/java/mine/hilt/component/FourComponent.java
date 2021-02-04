package mine.hilt.component;

import dagger.Component;
import mine.hilt.NineActivity;
import mine.hilt.SevenActivity;
import mine.hilt.module.AAModule;
import mine.hilt.module.BBModule;
import mine.hilt.module.DaggerModule;

/**
 * Created by Administrator on 2021/2/2 18:37.
 */
@Component(modules = AAModule.class)
public interface FourComponent {
    void inject(NineActivity nineActivity);
}