package mine.hilt.data;

import javax.inject.Inject;

/**
 * Created by Administrator on 2020/12/21.
 */
public class Person {
    int age;
    String name;

    @Inject
    public Person() {
        System.out.println("~~Person.Person~~");
        this.age = 0;
        this.name = "";
    }
}
