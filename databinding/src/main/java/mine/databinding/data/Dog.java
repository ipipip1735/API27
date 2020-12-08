package mine.databinding.data;

import android.widget.TextView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;

import java.util.Random;

/**
 * Created by Administrator on 2020/11/1.
 */
public class Dog {
    String name;
    boolean gender;
    int age;



    public Dog(String name) {
        System.out.println("--- " + getClass().getSimpleName() + ".Constructor ---");
        this.name = name;
        gender = false;
    }

    public void setName(String name) {
        System.out.println("~~~ " + getClass().getSimpleName() + ".setName ~~~");
        this.name = name;
    }

    public String getName() {
        System.out.println("~~~ " + getClass().getSimpleName() + ".getName ~~~");
        return name;
    }

    public boolean isGender() {
        System.out.println("~~~ " + getClass().getSimpleName() + ".setName ~~~");
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
