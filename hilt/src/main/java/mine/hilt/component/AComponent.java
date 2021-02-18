package mine.hilt.component;

import java.util.Set;

import dagger.Component;
import mine.hilt.data.Openable;
import mine.hilt.module.CCModule;

/**
 * Created by Administrator on 2021/2/12.
 */
@Component(modules = CCModule.class)
public interface AComponent {

    Set<Openable> openable();
    BComponent bComponent();
}