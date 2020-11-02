package mine.databinding.data;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.Random;

/**
 * Created by Administrator on 2020/11/1.
 */
public class Cat extends BaseObservable {
    String name;
    int age;

    public Cat(String name) {
        this.name = name;
        age = new Random().nextInt(120);
    }

    @Bindable
    public String getName() {
        return name;
    }
    @Bindable
    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
        notifyChange();
    }

    public void setAge(int age) {
        this.age = age;
        notifyChange();
    }
}
