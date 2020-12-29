package mine.hilt.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.FragmentComponent;
import mine.hilt.data.Grass;
import mine.hilt.data.Mouse;
import mine.hilt.data.Sheep;

/**
 * Created by Administrator on 2020/12/29 16:52.
 */
@InstallIn(FragmentComponent.class)
@Module
public class EightModule {
    @Provides
    public static Mouse provideMouse(Activity activity) {
        System.out.println("~~SevenModule.provideSheep~~");
        System.out.println("activity = " + activity);//使用Hilt提供的默认绑定作为依赖
        return new Mouse();
    }
}
