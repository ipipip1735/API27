package mine.hilt.component;

import dagger.Component;
import mine.hilt.SevenActivity;

/**
 * Created by Administrator on 2021/2/2 18:37.
 */
@Component(modules = {})
public interface FourComponent {
    void inject(SevenActivity sevenActivity);

}
