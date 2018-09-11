package mine.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Random;

/**
 * Created by Administrator on 2018/9/9.
 */
@Entity
public class Car {
    public Car(String carName) {
        this.carName = carName;
        this.carPrice = new Random(100).nextInt();
    }

    @PrimaryKey(autoGenerate = true)
    private int cid;
    private String carName;
    private int carPrice;


    public int getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(int carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
