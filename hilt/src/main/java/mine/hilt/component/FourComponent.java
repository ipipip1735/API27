package mine.hilt.component;

import dagger.Component;
import mine.hilt.NineActivity;
import mine.hilt.SevenActivity;
import mine.hilt.module.AAModule;
import mine.hilt.module.BBModule;
import mine.hilt.module.DaggerModule;
import mine.hilt.module.OneModule;
import mine.hilt.module.TwoModule;

/**
 * Created by Administrator on 2021/2/2 18:37.
 */
//@Component(modules = AAModule.class)
@Component(modules = {OneModule.class, TwoModule.class})
public interface FourComponent {
    void inject(NineActivity nineActivity);
}