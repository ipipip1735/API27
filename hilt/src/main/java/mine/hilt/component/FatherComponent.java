package mine.hilt.component;

import javax.inject.Provider;

import dagger.Component;
import mine.hilt.module.EightModule;
import mine.hilt.module.FatherModule;
import mine.hilt.module.SonModule;
import mine.hilt.module.SubcomponentsModule;
import mine.hilt.module.ZeroModule;

/**
 * Created by Administrator on 2021/2/14 15:18.
 */
@Component(modules = {SubcomponentsModule.class, FatherModule.class})
public interface FatherComponent {

//    SonComponent.Factory sonComponent();
//    SonComponent sonComponent(SonModule sonModule);

    Provider<SonComponent.Factory> provider();

}