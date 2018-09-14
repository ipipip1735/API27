package mine.viewmodel;

import java.util.Random;

/**
 * Created by Administrator on 2018/9/14.
 */
public class User {

    private String firstName;
    private String lastName;
    private int age;
    private int uid;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = new Random().nextInt(100);
    }







    public void setAge(int age) {
        this.age = age;
    }

    public int getUid() {
        return uid;
    }

    public int getAge() {
        return age;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", uid=" + uid +
                '}';
    }
}
