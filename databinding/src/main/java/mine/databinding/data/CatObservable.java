package mine.databinding.data;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import java.util.Random;

/**
 * Created by Administrator on 2020/11/1.
 */
public class CatObservable extends BaseObservable {
    String name;
    int age;

    public CatObservable(String name) {
        System.out.println("~~CatObservable~~");
        this.name = name;
        age = new Random().nextInt(120);
    }

//    @Bindable
    public String getName() {
        System.out.println("~~getName~~");
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
//        notifyChange();
        notifyPropertyChanged(BR.age);
    }
}
