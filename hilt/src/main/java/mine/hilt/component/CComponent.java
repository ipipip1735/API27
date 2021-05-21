package mine.hilt.component;

import java.util.Set;

import dagger.BindsInstance;
import dagger.Component;
import mine.hilt.data.Box;
import mine.hilt.module.EEModule;

/**
 * Created by Administrator on 2021/2/12.
 */
@Component
public interface CComponent {
    @Component.Builder
    interface Go{
        @BindsInstance
        Go foo(Box box);
        CComponent ccc();
    }

//    @Component.Factory
//    interface Go{
//        CComponent sk(@BindsInstance Box box);
//    }
}