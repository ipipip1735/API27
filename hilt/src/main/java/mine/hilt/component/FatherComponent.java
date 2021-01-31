package mine.hilt.component;

import dagger.Component;
import mine.hilt.module.SubcomponentsModule;

/**
 * Created by Administrator on 2021/1/21 8:33.
 */
@Component(modules = SubcomponentsModule.class)
public interface FatherComponent {

    SonComponent.Factory sonComponent();
}
