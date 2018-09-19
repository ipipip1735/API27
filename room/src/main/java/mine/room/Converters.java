package mine.room;

import android.arch.persistence.room.TypeConverter;

/**
 * Created by Administrator on 2018/9/18.
 */
public class Converters {
    @TypeConverter
    public String fromPerson(Person person) {
        System.out.println("*********  " + getClass().getSimpleName() + ".fromPerson  *********");
        System.out.println(person);

        return "kkkk";
    }

    @TypeConverter
    public Person toPerson(String name) {
        System.out.println("*********  " + getClass().getSimpleName() + ".toPerson  *********");
        System.out.println("name is " + name);

        Person person = new Person();
        person.setPName("oooo");
        return person;
    }
}
