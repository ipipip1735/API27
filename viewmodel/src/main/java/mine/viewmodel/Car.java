package mine.viewmodel;

import java.util.Random;

/**
 * Created by Administrator on 2018/9/12.
 */
public class Car {
    public Car(String carName) {
        this.carName = carName;
        this.carPrice = new Random(100).nextInt();
    }

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
