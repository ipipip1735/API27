package mine.hilt.component;

import dagger.Component;
import mine.hilt.TenActivity;
import mine.hilt.module.DaggerAModule;
import mine.hilt.module.DaggerBModule;
import mine.hilt.module.DaggerCModule;

/**
 * Created by Administrator on 2021/2/2 18:37.
 */
@Component(modules = DaggerCModule.class)
public interface SixComponent {
    void inject(TenActivity tenActivity);
}