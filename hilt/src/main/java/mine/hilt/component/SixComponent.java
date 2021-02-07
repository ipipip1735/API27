package mine.hilt.component;

import java.util.Set;

import dagger.Component;
import mine.hilt.TenActivity;
import mine.hilt.data.Box;
import mine.hilt.data.Openable;
import mine.hilt.data.Window;
import mine.hilt.module.DaggerAModule;
import mine.hilt.module.DaggerBModule;
import mine.hilt.module.DaggerCModule;

/**
 * Created by Administrator on 2021/2/2 18:37.
 */
@Component(modules = {DaggerBModule.class, DaggerCModule.class})
public interface SixComponent {
    void inject(TenActivity tenActivity);

    Set<Openable> openables();
}