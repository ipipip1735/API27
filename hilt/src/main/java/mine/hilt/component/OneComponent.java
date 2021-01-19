package mine.hilt.component;

import javax.inject.Singleton;

import dagger.Component;
import mine.hilt.data.Cloud;
import mine.hilt.data.TheScope;

/**
 * Created by Administrator on 2021/1/19 17:44.
 */
//@Singleton
@TheScope
@Component
public interface OneComponent {
    Cloud cloud();
}
