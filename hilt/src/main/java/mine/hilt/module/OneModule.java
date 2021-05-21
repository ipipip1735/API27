package mine.hilt.module;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import mine.hilt.annotation.CatPet;
import mine.hilt.annotation.DogPet;
import mine.hilt.data.Cat;
import mine.hilt.data.Child;
import mine.hilt.data.Dog;

/**
 * Created by Administrator on 2020/12/24.
 */
@Module
@InstallIn(ActivityComponent.class)
public abstract class OneModule {

    //限定提供器
    @DogPet
    @Provides
    public static Child bindDog(Dog dog){
        Child child = new Child(dog);
        return child;
    }

    @CatPet
    @Provides
    public static Child bindCat(Cat cat){
        Child child = new Child(cat);
        return child;
    }



    //限定绑定器
//    @DogPet
//    @Binds
//    abstract Animal bindDog(Dog dog);
//
//    @CatPet
//    @Binds
//    abstract Animal bindCat(Cat cat);



}
