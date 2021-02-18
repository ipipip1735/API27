package mine.hilt.component;

import java.util.Map;

import dagger.Component;
import mine.hilt.TenActivity;
import mine.hilt.data.Color;
import mine.hilt.data.Openable;
import mine.hilt.module.DaggerDModule;
import mine.hilt.module.DaggerEModule;

/**
 * Created by Administrator on 2021/2/9 18:30.
 */
//@Component(modules = DaggerEModule.class)
public interface EightComponent {
    void inject(TenActivity tenActivity);

    Map<Color, Openable> openableByEnum();
    Map<Class<? extends Integer>, Openable> openablesByClass();
}