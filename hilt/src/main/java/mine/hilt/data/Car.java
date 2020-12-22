package mine.hilt.data;

import javax.inject.Inject;

/**
 * Created by Administrator on 2020/12/22.
 */
public class Car {
    int price;
    String name;

//    @Inject
//    public Engine engine;

    @Inject
    public Car(Engine engine) {
        System.out.println("~~Car.Car~~");
        this.price = 0;
        this.name = "";
        System.out.println("engine = " + engine);
    }
}
