package mine.hilt.data;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * Created by Administrator on 2020/12/22.
 */
@InstallIn(ActivityComponent.class)
@Module
public abstract class TheModule {

    @Provides
    public static Owner provideOwner(Pet pet) {
        System.out.println("~~TheModule.provideOwner~~");
        return new Owner(pet);
    }

//    @Binds
//    abstract Sense bindPerson(Person person);

}
