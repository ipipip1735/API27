package mine.hilt.component;

import dagger.Component;
import mine.hilt.SixActivity;
import mine.hilt.data.Wind;

/**
 * Created by Administrator on 2021/1/21 8:34.
 */
@Component
public interface TwoComponent {
    void inject(SixActivity sixActivity);
}
