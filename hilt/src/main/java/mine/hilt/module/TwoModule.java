package mine.hilt.module;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import mine.hilt.annotation.DogPet;
import mine.hilt.data.Child;
import mine.hilt.data.Person;

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


//    @Provides
//    public static Employee provideEmployee(Company company){
//        System.out.println("~~TwoModule.provideCompany~~");
//        System.out.println("company = " + company);
//
//        Employee employee = new Employee(company);
//        System.out.println("employee = " + employee);
//
//        return employee;
//    }

    @Provides
    public static Person providePerson(@DogPet Child child){
        System.out.println("child = " + child);
        return new Person();
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
