package mine.hilt.component;

import dagger.Component;
import mine.hilt.EightActivity;
import mine.hilt.data.Moon;

/**
 * Created by Administrator on 2021/2/21.
 */
@Component
public interface DFatherComponent {
    Moon moon();
}
