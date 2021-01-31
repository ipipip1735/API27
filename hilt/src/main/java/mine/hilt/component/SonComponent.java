package mine.hilt.component;

import dagger.Subcomponent;
import mine.hilt.EightActivity;

/**
 * Created by Administrator on 2021/1/21 8:34.
 */
@Subcomponent
public interface SonComponent {
    @Subcomponent.Factory
    interface Factory {
        SonComponent create();
    }

    void inject(EightActivity eightActivity);
}
