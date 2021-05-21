package mine.hilt.component;

import dagger.Component;
import mine.hilt.EightActivity;
import mine.hilt.data.Moon;
import mine.hilt.module.DDModule;
import mine.hilt.module.DependencyModule;

/**
 * Created by Administrator on 2021/2/21.
 */
@Component(modules = DependencyModule.class)
public interface DFatherComponent {
    Moon moon();
}
