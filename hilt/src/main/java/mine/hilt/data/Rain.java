package mine.hilt.data;

import javax.inject.Inject;

/**
 * Created by Administrator on 2021/1/31.
 */
public class Rain {
    int age;

    @Inject
    public Rain() {
        System.out.println("~~" + getClass().getSimpleName() + ".Rain~~");
    }
}
