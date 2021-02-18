package mine.hilt.component;

import java.util.Set;

import dagger.Component;
import mine.hilt.data.Box;
import mine.hilt.data.Openable;
import mine.hilt.module.DaggerGModule;

/**
 * Created by Administrator on 2021/2/11.
 */
@Component(modules = DaggerGModule.class)
public interface TenComponent {

    Set<Openable> openables();
}
