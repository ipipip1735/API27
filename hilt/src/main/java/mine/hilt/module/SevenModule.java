package mine.hilt.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.FragmentComponent;
import mine.hilt.data.Grass;
import mine.hilt.data.Horse;
import mine.hilt.data.Sheep;
import mine.hilt.data.Water;

/**
 * Created by Administrator on 2020/12/29 16:52.
 */
@InstallIn(FragmentComponent.class)
@Module
public class SevenModule {
    @Provides
    public static Sheep provideSheep(Grass grass) {
        System.out.println("~~SevenModule.provideSheep~~");
        System.out.println("grass = " + grass);
        return new Sheep(grass);
    }
}
