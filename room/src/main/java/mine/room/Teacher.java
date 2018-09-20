package mine.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Random;

/**
 * Created by Administrator on 2018/9/11.
 */
@Entity
public class Teacher {
    @PrimaryKey(autoGenerate = true)
    private int tId;
    private int tSalary;
    @TypeConverters({Converters.class})
    private Person person;


    public Teacher() {
        System.out.println("+++  " + getClass().getSimpleName() + "  +++");
        this.person = new Person();
        this.tSalary = new Random().nextInt(5000);;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setTId(int tId) {
        this.tId = tId;
    }


    public void setTSalary(int tSalary) {
        this.tSalary = tSalary;
    }

    public int getTId() {
        return tId;
    }


    public int getTSalary() {
        return tSalary;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tId=" + tId +
                ", tSalary=" + tSalary +
                ", person=" + person +
                '}';
    }
}
