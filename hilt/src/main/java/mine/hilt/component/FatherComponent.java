package mine.hilt.component;

import dagger.Component;
import mine.hilt.module.SubcomponentsModule;

/**
 * Created by Administrator on 2021/2/14 15:18.
 */
@Component(modules = SubcomponentsModule.class)
public interface FatherComponent {

    SonComponent.Factory sonComponent();
}