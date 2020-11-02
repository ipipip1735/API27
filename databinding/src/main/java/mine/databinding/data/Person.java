package mine.databinding.data;

import androidx.databinding.ObservableField;

/**
 * Created by Administrator on 2020/11/1.
 */
public class Person {
    public final ObservableField<String> name = new ObservableField<>();

    public Person(String name) {
        this.name.set(name);
    }
}
