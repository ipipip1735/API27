package mine.hilt.component;

import javax.inject.Provider;

import dagger.Component;
import mine.hilt.EightActivity;
import mine.hilt.module.EightModule;
import mine.hilt.module.FatherModule;
import mine.hilt.module.SonModule;
import mine.hilt.module.SubcomponentsModule;
import mine.hilt.module.ZeroModule;

/**
 * Created by Administrator on 2021/2/14 15:18.
 */
@Component(modules = FatherModule.class)
//@Component(modules = {SubcomponentsModule.class, FatherModule.class})
public interface FatherComponent {

    //直接获取工厂
    SonComponent.Factory sonComponent();

    //直接获取构建器
//    SonComponent.Builder builder();


    //工厂提供器
//    Provider<SonComponent.Factory> provider();

    //构建器提供器
//    Provider<SonComponent.Builder> provider();


//    SonComponent sonComponent(SonModule sonModule);

}