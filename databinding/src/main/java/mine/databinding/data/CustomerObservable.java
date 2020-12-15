package mine.databinding.data;

import androidx.databinding.BaseObservable;

import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2020/11/15.
 */
public class CustomerObservable extends BaseObservable {
    public String name;
    public Date birthDay;

    public CustomerObservable(String name) {
        this.name = name;
        birthDay = new Date();
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
        notifyChange();
    }

    public Date getBirthDay() {
        return birthDay;
    }
}
