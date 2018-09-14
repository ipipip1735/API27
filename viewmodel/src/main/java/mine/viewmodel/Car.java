package mine.viewmodel;

import java.util.Random;

/**
 * Created by Administrator on 2018/9/12.
 */
public class Car {
    private int cid;
    private String carName;
    private int carPrice;

    public Car(String carName) {
        this.carName = carName;
        this.carPrice = new Random().nextInt(100);
    }

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

    @Override
    public String toString() {
        return "Car{" +
                "cid=" + cid +
                ", carName='" + carName + '\'' +
                ", carPrice=" + carPrice +
                '}';
    }
}
