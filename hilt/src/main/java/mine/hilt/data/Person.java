package mine.hilt.data;

import java.util.Date;

import javax.inject.Inject;

/**
 * Created by Administrator on 2020/12/21.
 */
public class Person implements Sense {
    int age;
    String name;


    public  Person() {
        System.out.println("~~Person.Person~~");
        this.age = 0;
        this.name = "";
    }

    @Override
    public void see() {
        System.out.println("~~Person.see~~");
    }
}
