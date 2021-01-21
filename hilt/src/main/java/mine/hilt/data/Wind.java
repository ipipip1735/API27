package mine.hilt.data;

import javax.inject.Inject;

import mine.hilt.SixActivity;

/**
 * Created by Administrator on 2021/1/21 15:18.
 */
public class Wind {

    @Inject
    public Wind() {
        System.out.println("~~Wind.Wind~~");
    }
}
