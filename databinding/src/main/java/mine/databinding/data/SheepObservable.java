package mine.databinding.data;

import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.Random;

import mine.databinding.BR;

/**
 * Created by Administrator on 2022/3/30.
 */
public class SheepObservable extends BaseObservable {
    String name;

    public SheepObservable(String name) {
        System.out.println("--SheepObservable--");
        this.name = name;
    }

    @Bindable
    public String getName() {
        System.out.println("~~getName~~");
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        System.out.println("~~" + getClass().getSimpleName() + ".equals~~");
        return super.equals(obj);
    }
}
