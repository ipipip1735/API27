package mine.databinding.data;

import java.util.Random;

/**
 * Created by Administrator on 2020/10/31.
 */
public class User {
    String name;
    int age;

    public User(String name) {
        this.name = name;
        age = new Random().nextInt(120);
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
}
