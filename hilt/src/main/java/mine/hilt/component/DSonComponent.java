package mine.hilt.component;

import dagger.Component;
import mine.hilt.EightActivity;

/**
 * Created by Administrator on 2021/2/21.
 */
@Component(dependencies = DFatherComponent.class)
public interface DSonComponent {
    @Component.Builder
    interface Builder {
        Builder setFather(DFatherComponent dFatherComponent);
        DSonComponent build();
    }

    void inject(EightActivity eightActivity);

}

