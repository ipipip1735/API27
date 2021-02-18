package mine.hilt.component;

import java.util.Set;

import dagger.Subcomponent;
import mine.hilt.data.Openable;
import mine.hilt.module.DDModule;

/**
 * Created by Administrator on 2021/2/12.
 */
@Subcomponent(modules = DDModule.class)
public interface BComponent {

    Set<Openable> openable();
}