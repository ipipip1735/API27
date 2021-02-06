package mine.hilt.data;

import javax.inject.Inject;

/**
 * Created by Administrator on 2021/2/6.
 */
public class Window implements Openable {

    @Inject
    public Window() {
        System.out.println("~~" + getClass().getSimpleName() + ".Window~~");
    }

    @Override
    public void open() {
        System.out.println("~~" + getClass().getSimpleName() + ".open~~");
    }
}
