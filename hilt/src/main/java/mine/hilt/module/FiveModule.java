package mine.hilt.module;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.scopes.ActivityRetainedScoped;
import dagger.hilt.android.scopes.ActivityScoped;
import mine.hilt.data.Circle;
import mine.hilt.data.Milk;

/**
 * Created by Administrator on 2021/1/4 18:48.
 */
//使用ActivityRetainedComponent组件和ActivityRetainedScoped作用域
//@InstallIn(ActivityRetainedComponent.class)
//@Module
//public class FiveModule {
//    public FiveModule() {
//        System.out.println("~~FiveModule.FiveModule~~");
//    }
//
//    @ActivityRetainedScoped
//    @Provides
//    public static Milk provideMilk() {
//        System.out.println("~~FiveModule.provideMilk~~");
//        return new Milk();
//    }
//}



//使用ActivityComponent组件和ActivityScoped作用域
@InstallIn(ActivityComponent.class)
@Module
public class FiveModule {
    public FiveModule() {
        System.out.println("~~FiveModule.FiveModule~~");
    }

    @ActivityScoped
    @Provides
    public static Milk provideMilk() {
        System.out.println("~~FiveModule.provideMilk~~");
        return new Milk();
    }
}
