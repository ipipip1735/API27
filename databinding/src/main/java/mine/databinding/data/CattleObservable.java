package mine.databinding.data;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.Random;

import mine.databinding.BR;

/**
 * Created by Administrator on 2020/11/1.
 */
public class CattleObservable extends BaseObservable {
    String name;
    int age;

    public CattleObservable(String name) {
        System.out.println("--CatObservable--");
        this.name = name;
        age = new Random().nextInt(120);
    }

    @Bindable
    public String getName() {
        System.out.println("~~CattleObservable.getName~~");
        return name;
    }

    public void setName(String name) {
        System.out.println("~~CattleObservable.setName~~");
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
