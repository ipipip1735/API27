package mine.hilt.data;

import javax.inject.Inject;

/**
 * Created by Administrator on 2021/2/20.
 */
public class Moon {

    @Inject
    public Moon() {
        System.out.println("~~" + getClass().getSimpleName() + ".Moon~~");
    }
}
