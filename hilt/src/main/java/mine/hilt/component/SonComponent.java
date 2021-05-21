package mine.hilt.component;

import dagger.Subcomponent;
import mine.hilt.EightActivity;
import mine.hilt.data.Milk;
import mine.hilt.data.Sun;
import mine.hilt.module.SonModule;

/**
 * Created by Administrator on 2021/1/21 8:34.
 */
//方式一：使用工厂方法（子组件不带模块）
//@Subcomponent
//public interface SonComponent {
//    //方式一：使用工厂
////    @Subcomponent.Factory
////    interface Factory {
////        SonComponent create();
////    }
//
//    //方式二：使用构建器
////    @Subcomponent.Builder
////    interface Builder {
////        SonComponent build();
////    }
//
//    void inject(EightActivity eightActivity);
//}

//方式二：使用工厂方法（子组件带模块）
@Subcomponent(modules = SonModule.class)
public interface SonComponent {
    //方式一：使用工厂
    @Subcomponent.Factory
    interface Factory {
        SonComponent create(SonModule sonModule);
    }

    //方式二：使用构建器
//    @Subcomponent.Builder
//    interface Builder {
//        Builder sonModule(SonModule sonModule);
//        SonComponent build();
//    }

    Sun sun();

    void inject(EightActivity eightActivity);
}