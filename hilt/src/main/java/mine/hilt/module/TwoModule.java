package mine.hilt.module;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.scopes.ActivityScoped;
import mine.hilt.annotation.CatPet;
import mine.hilt.annotation.DogPet;
import mine.hilt.data.Animal;
import mine.hilt.data.Cat;
import mine.hilt.data.Child;
import mine.hilt.data.Company;
import mine.hilt.data.Dog;

/**
 * Created by Administrator on 2020/12/24.
 */
@InstallIn(ActivityComponent.class)
@Module
public abstract class TwoModule {

    @ActivityScoped
    @Provides
    public static Company provideCompany(){
        return new Company();
    }
}
