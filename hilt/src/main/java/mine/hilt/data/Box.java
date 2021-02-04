package mine.hilt.data;

import javax.inject.Inject;

/**
 * Created by Administrator on 2021/2/4 18:40.
 */
public class Box implements Openable {

    @Inject
    public Box() {
    }

    @Override
    public void open() {
        System.out.println("~~" + getClass().getSimpleName() + ".open~~");
    }
}
