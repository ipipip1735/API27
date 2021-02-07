package mine.hilt.component;

import dagger.Component;
import mine.hilt.TenActivity;
import mine.hilt.module.DaggerAModule;
import mine.hilt.module.DaggerBModule;

/**
 * Created by Administrator on 2021/2/2 18:37.
 */
@Component(modules = {DaggerAModule.class, DaggerBModule.class})
public interface FiveComponent {
    void inject(TenActivity tenActivity);

}