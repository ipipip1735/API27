package mine.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Random;

/**
 * Created by Administrator on 2018/9/18.
 */
@Entity
public class Person {
    @PrimaryKey(autoGenerate = true)
    private int pId;
    private String pName;
    private int pAge;


    public Person() {
        System.out.println("+++  " + getClass().getSimpleName() + "  +++");

        this.pName = "chris" + new Random().nextInt(10);
        this.pAge = new Random().nextInt(100);
    }


    public int getPId() {
        return pId;
    }

    public void setPId(int pId) {
        this.pId = pId;
    }

    public String getPName() {
        return pName;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    public int getPAge() {
        return pAge;
    }

    public void setPAge(int pAge) {
        this.pAge = pAge;
    }

    @Override
    public String toString() {
        return "Person{" +
                "pId=" + pId +
                ", pName='" + pName + '\'' +
                ", pAge=" + pAge +
                '}';
    }
}
