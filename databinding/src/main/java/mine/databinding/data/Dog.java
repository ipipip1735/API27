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
        System.out.println("--Dog.Constructor--");
        this.name = name;
        age = 10;
    }

    public void setName(String name) {
        System.out.println("~~Dog.setName~~");
        this.name = name;
    }

    public void setAge(Integer age) {
        System.out.println("~~Dog.setAge~~");
        this.age = age;
    }

    public String getName() {
        System.out.println("~~Dog.getName~~");
        return name;
    }

    public Integer getAge() {
        System.out.println("~~Dog.getAge~~");
        return age;
    }

}
