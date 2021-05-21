package mine.hilt.component;

import javax.inject.Inject;

import dagger.Binds;
import dagger.Component;
import mine.hilt.EightActivity;
import mine.hilt.data.Earth;
import mine.hilt.data.Mars;
import mine.hilt.data.Moon;
import mine.hilt.data.Sun;
import mine.hilt.module.ChildComponentsModule;
import mine.hilt.module.ChildModule;
import mine.hilt.module.MotherModule;
import mine.hilt.module.SubcomponentsModule;

/**
 * Created by Administrator on 2021/2/14 15:18.
 */
@Component(modules = MotherModule.class)
public interface MotherComponent {
//    ChildComponent childComponent();
    ChildComponent childComponent(ChildModule childModule);
    void inject(EightActivity eightActivity);
}