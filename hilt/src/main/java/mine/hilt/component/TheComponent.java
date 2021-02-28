package mine.hilt.component;

import android.app.Activity;

import dagger.Component;
import dagger.MembersInjector;
import mine.hilt.SixActivity;
import mine.hilt.data.Bottle;
import mine.hilt.data.Cloud;
import mine.hilt.data.Wind;

/**
 * Created by Administrator on 2021/1/2.
 */
@Component
public interface TheComponent {

    void inject(SixActivity sixActivity);

//    @Component.Builder
//    interface Builder {
//        Builder appDependencies(Bottle bottle);
//        TheComponent build();
//    }


    MembersInjector<Wind> getCloud();

}
