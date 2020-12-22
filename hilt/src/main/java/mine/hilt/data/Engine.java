package mine.hilt.data;

import javax.inject.Inject;

/**
 * Created by Administrator on 2020/12/22.
 */
public class Engine {

    @Inject
    public Engine() {
        System.out.println("~~Engine.Engine~~");
    }
}
