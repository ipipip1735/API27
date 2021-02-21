package mine.hilt.component;

import javax.inject.Inject;

import dagger.Binds;
import dagger.Component;
import mine.hilt.data.Moon;
import mine.hilt.data.Sun;
import mine.hilt.module.ChildComponentsModule;
import mine.hilt.module.SubcomponentsModule;

/**
 * Created by Administrator on 2021/2/14 15:18.
 */
@Component(modules = ChildComponentsModule.class)
public interface MotherComponent {
    ChildComponent.Builder newBuilder();
    Sun sun();
}