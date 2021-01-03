package mine.hilt.module;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.components.FragmentComponent;
import mine.hilt.data.Circle;
import mine.hilt.data.Mouse;
import mine.hilt.data.Shape;

/**
 * Created by Administrator on 2020/12/29 16:52.
 */
@InstallIn(ActivityRetainedComponent.class)
@Module
public abstract class NineModule {

    public NineModule() {
        System.out.println("~~NineModule.NineModule~~");
        System.out.println(this);
    }

    @Provides
    public static Circle provideCircle() {
        System.out.println("~~NineModule.provideCircle~~");
        return new Circle();
    }

    @Binds
    public abstract Shape bindShape(Circle circle);
}
