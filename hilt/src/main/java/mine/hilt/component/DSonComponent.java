package mine.hilt.component;

import dagger.Component;
import mine.hilt.EightActivity;
import mine.hilt.data.Moon;
import mine.hilt.data.Snow;

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

    Moon mm();
//    Snow ss();

//    void inject(EightActivity eightActivity);

}

