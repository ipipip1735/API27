package mine.hilt.component;

import dagger.Component;
import mine.hilt.NineActivity;
import mine.hilt.TenActivity;
import mine.hilt.module.AAModule;
import mine.hilt.module.DaggerWithBindsModule;

/**
 * Created by Administrator on 2021/2/2 18:37.
 */
@Component(modules = DaggerWithBindsModule.class)
public interface FiveComponent {
    void inject(TenActivity tenActivity);
}