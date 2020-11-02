package mine.databinding.data;

/**
 * Created by Administrator on 2020/10/31.
 */
public class Car {
    int num;
    String brand;

    public Car(int num) {
        this.num = num;
        brand = "OXO";
    }

    public int getNum() {
        return num;
    }

    public String getBrand() {
        return brand;
    }
}
