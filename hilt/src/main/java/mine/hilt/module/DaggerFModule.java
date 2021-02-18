package mine.hilt.module;

import com.google.auto.value.AutoAnnotation;

import java.lang.annotation.Annotation;

import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.migration.DisableInstallInCheck;
import dagger.multibindings.IntoMap;
import mine.hilt.annotation.MulKey;
import mine.hilt.data.Box;
import mine.hilt.data.Openable;
import mine.hilt.data.Window;

/**
 * Created by Administrator on 2021/2/10 16:05.
 */
@Module
@DisableInstallInCheck
public class DaggerFModule {

//    @Provides @IntoMap
//    @MulKey(value = "XX", age = 1)
//    public Openable box(Box box) {
//        return box;
//    }

//    @Provides @IntoMap
//    @MulKey(value = "YY", age = 2)
//    public Openable window(Window window) {
//        return window;
//    }

}