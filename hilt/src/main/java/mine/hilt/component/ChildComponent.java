package mine.hilt.component;

import dagger.Binds;
import dagger.Provides;
import dagger.Subcomponent;
import mine.hilt.data.Earth;
import mine.hilt.data.Moon;
import mine.hilt.data.Sun;
import mine.hilt.module.CCModule;
import mine.hilt.module.ChildModule;

/**
 * Created by Administrator on 2021/2/14 15:18.
 */
//方式一：使用构建器（子组件不带模块）
//@Subcomponent
//public abstract class ChildComponent {
//    @Subcomponent.Builder
//    public interface Builder{
//        ChildComponent build();
//    }
//}


//方式二：使用构建器（子组件不带模块）
//@Subcomponent(modules = SonModule.class)
//public interface SonComponent {
//    @Subcomponent.Builder
//    interface Builder{
//        Builder module(SonModule sonModule);
//        SonComponent build();
//    }
//
//}


//@Subcomponent
@Subcomponent(modules = ChildModule.class)
public interface ChildComponent {
    Earth earth();
}