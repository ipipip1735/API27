package mine.hilt.component;

import dagger.Provides;
import dagger.Subcomponent;
import mine.hilt.data.Sun;

/**
 * Created by Administrator on 2021/2/14 15:18.
 */
//方式三：使用构建器（子组件不带模块）
@Subcomponent
public abstract class ChildComponent {
    @Subcomponent.Builder
    interface Builder{
        ChildComponent build();
    }

//    @Provides
//    Sun getSun(){ return new Sun();}
}


//方式三：使用构建器（子组件不带模块）
//@Subcomponent(modules = SonModule.class)
//public interface SonComponent {
//    @Subcomponent.Builder
//    interface Builder{
//        Builder module(SonModule sonModule);
//        SonComponent build();
//    }
//
//}