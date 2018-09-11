package mine.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Random;

/**
 * Created by Administrator on 2018/9/11.
 */
@Entity
public class Teacher {
    @PrimaryKey(autoGenerate = true)
    private int tId;
    private String tName;
    private int tAge;
    private int tSalary;


    public Teacher() {
        this.tName = "chris" + new Random().nextInt(1);
        this.tAge = new Random().nextInt(100);
        this.tSalary = new Random().nextInt(5000);;
    }



    public void setTId(int tId) {
        this.tId = tId;
    }

    public void setTName(String tName) {
        this.tName = tName;
    }

    public void setTAge(int tAge) {
        this.tAge = tAge;
    }

    public void setTSalary(int tSalary) {
        this.tSalary = tSalary;
    }

    public int getTId() {
        return tId;
    }

    public String getTName() {
        return tName;
    }

    public int getTAge() {
        return tAge;
    }

    public int getTSalary() {
        return tSalary;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tId=" + tId +
                ", tName='" + tName + '\'' +
                ", tAge=" + tAge +
                ", tSalary=" + tSalary +
                '}';
    }
}
