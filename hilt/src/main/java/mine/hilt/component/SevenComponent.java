package mine.hilt.component;

import java.util.Map;
import java.util.Set;

import dagger.Component;
import mine.hilt.TenActivity;
import mine.hilt.data.Openable;
import mine.hilt.module.DaggerBModule;
import mine.hilt.module.DaggerCModule;
import mine.hilt.module.DaggerDModule;

/**
 * Created by Administrator on 2021/2/8 19:48.
 */
//@Component(modules = DaggerDModule.class)
public interface SevenComponent {
    void inject(TenActivity tenActivity);

    Map<String, Openable> OpenableByString();
    Map<Class<?>, Openable> openablesByClass();
}