package mine.hilt.data;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Administrator on 2021/1/19 17:46.
 */
//@Singleton
//@TheScope
public class Cloud {
    @Inject
    public Cloud() {
        System.out.println("~~Cloud.Cloud~~");
    }
}
