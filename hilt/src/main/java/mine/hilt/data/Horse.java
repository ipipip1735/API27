package mine.hilt.data;

import javax.inject.Inject;

/**
 * Created by Administrator on 2020/12/29 16:52.
 */
public class Horse {
    public Water water;

    public Horse(Water water) {
        System.out.println("~~Horse.Horse~~");
        this.water = water;
    }
}
