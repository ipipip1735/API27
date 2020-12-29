package mine.hilt.module;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import mine.hilt.data.Company;
import mine.hilt.data.Employee;
import mine.hilt.data.Horse;
import mine.hilt.data.Water;

/**
 * Created by Administrator on 2020/12/29 16:52.
 */
@InstallIn(ActivityComponent.class)
@Module
public class ThreeModule {


    @Provides
    public static Horse provideHorse(Water water) {
        System.out.println("~~ThreeModule.provideHorse~~");
        System.out.println("water = " + water);
        return new Horse(water);
    }
}
