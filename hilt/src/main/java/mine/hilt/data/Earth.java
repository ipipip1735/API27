package mine.hilt.data;

import javax.inject.Inject;

/**
 * Created by Administrator on 2021/5/6.
 */
public class Earth {
    @Inject
    public Earth(Sun sun) {
        System.out.println("Earth.Earth");
        System.out.println("sun = " + sun);
    }
}
