package mine.databinding.data;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.sql.Time;
import java.time.Instant;
import java.util.Random;

import mine.databinding.BR;

/**
 * Created by Administrator on 2020/12/14.
 */
public class UserObservable extends BaseObservable {
    String name;
    Time age;

    public Time getAge() {
        return age;
    }

    @Bindable
    public void setAge(Time age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
//        notifyChange();
    }

    public UserObservable(String name) {
        System.out.println("--UserObservable--");
        this.name = name;
        age = new Time(Instant.now().toEpochMilli());
    }

    public String getName() {
        System.out.println("~~UserObservable.getName~~");
        return name;
    }

    public void setName(String name) {
        System.out.println("~~UserObservable.setName~~");
        this.name = name;
    }

}
