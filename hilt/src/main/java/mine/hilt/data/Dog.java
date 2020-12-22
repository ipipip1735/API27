package mine.hilt.data;

import javax.inject.Inject;

/**
 * Created by Administrator on 2020/12/22.
 */
public class Dog {
    String name;

    @Inject
    public Dog() {
        System.out.println("~~Dog.Dog~~");
        this.name = "";
    }
}
