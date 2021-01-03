package mine.hilt.module;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.components.SingletonComponent;
import mine.hilt.data.Circle;
import mine.hilt.data.Point;

/**
 * Created by Administrator on 2021/1/2.
 */
@Module
@InstallIn(SingletonComponent.class)
public class ZeroModule {

    @Provides
    public static Point providePoint() {
        System.out.println("~~ZeroModule.providePoint~~");
        return new Point();
    }
}
