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
    Integer age;

    public Dog(String name) {
        System.out.println("--- " + getClass().getSimpleName() + ".Constructor ---");
        this.name = name;
        age = 10;
    }

    public void setName(String name) {
        System.out.println("~~~ " + getClass().getSimpleName() + ".setName ~~~");
        this.name = name;
    }

    public void setAge(Integer age) {
        System.out.println("~~~ " + getClass().getSimpleName() + ".setAge ~~~");
        this.age = age;
    }

    public String getName() {
        System.out.println("~~~ " + getClass().getSimpleName() + ".getName ~~~");
        return name;
    }

    public Integer getAge() {
        System.out.println("~~~ " + getClass().getSimpleName() + ".getAge ~~~");
        return age;
    }

}
