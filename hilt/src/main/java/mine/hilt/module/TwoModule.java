package mine.hilt.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.scopes.ActivityScoped;
import dagger.hilt.components.SingletonComponent;
import mine.hilt.annotation.CatPet;
import mine.hilt.annotation.DogPet;
import mine.hilt.data.Animal;
import mine.hilt.data.Cat;
import mine.hilt.data.Child;
import mine.hilt.data.Company;
import mine.hilt.data.Dog;
import mine.hilt.data.Employee;

/**
 * Created by Administrator on 2020/12/24.
 */
@InstallIn(ActivityComponent.class)
@Module
public abstract class TwoModule {

//    @ActivityScoped
//    @Provides
//    public static Company provideCompany(){
//        System.out.println("~~TwoModule.provideCompany~~");
//        Company company = new Company();
//        System.out.println("company = " + company);
//        return company;
//    }


    @Provides
    public static Employee provideEmployee(Company company){
        System.out.println("~~TwoModule.provideCompany~~");
        System.out.println("company = " + company);

        Employee employee = new Employee(company);
        System.out.println("employee = " + employee);

        return employee;
    }


//    @Singleton
//    @Provides
//    public static Company provideCompany(){
//        System.out.println("~~TwoModule.provideCompany~~");
//        Company company = new Company();
//        System.out.println("company = " + company);
//        return company;
//    }

}
