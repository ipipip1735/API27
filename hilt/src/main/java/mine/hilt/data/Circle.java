package mine.hilt.data;

import javax.inject.Inject;

/**
 * Created by Administrator on 2021/1/1.
 */
public class Circle implements Shape {
    @Inject
    public Circle() {
        System.out.println("~~Circle.Circle~~");
    }


    @Override
    public void area() {
        System.out.println("~~Circle.area~~");
    }
}
