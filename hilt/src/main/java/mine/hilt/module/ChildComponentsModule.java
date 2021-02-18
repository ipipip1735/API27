package mine.hilt.module;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.migration.DisableInstallInCheck;
import mine.hilt.component.ChildComponent;
import mine.hilt.component.SonComponent;
import mine.hilt.data.Sun;

/**
 * Created by Administrator on 2021/2/14.
 */
//@DisableInstallInCheck
//@Module(subcomponents = ChildComponent.class)
//public class ChildComponentsModule {
//
////    @Provides
////    Sun mSun(ChildComponent.Builder builder) {
////        System.out.println("builder = " + builder);
////        return new Sun();
////    }
//}

@DisableInstallInCheck
@Module
public class ChildComponentsModule {

    @Provides
    Sun mSun() {
        return new Sun();
    }
}