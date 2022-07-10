package mine.databinding.data;

import java.util.Random;

import mine.databinding.R;

/**
 * Created by Administrator on 2020/11/1.
 */
public class Dog {
    String name;
    boolean gender;
    int age;
    int resourceId;



    public Dog(String name) {
        System.out.println("--- " + getClass().getSimpleName() + ".Constructor ---");
        this.name = name;
        gender = false;
        age = new Random().nextInt(100);
        resourceId = 0;
    }

    public void setName(String name) {
        System.out.println("~~~ " + getClass().getSimpleName() + ".setName ~~~");
        this.name = name;
    }

    public String getName() {
        System.out.println("~~~ " + getClass().getSimpleName() + ".getName ~~~");
        return name;
    }

    public boolean isGender() {
        System.out.println("~~~ " + getClass().getSimpleName() + ".setName ~~~");
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
