package mine.hilt.data;

import javax.inject.Inject;

/**
 * Created by Administrator on 2020/12/22.
 */
public class Cat implements Animal {
    String name;

    @Inject
    public Cat() {
        System.out.println("~~Cat.Cat~~");
        this.name = "";
    }

    @Override
    public void eat() {
        System.out.println("~~Cat.eat~~");
    }
}
