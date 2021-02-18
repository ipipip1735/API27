package mine.hilt.component;

import java.util.Map;

import dagger.Component;
import mine.hilt.annotation.MulKey;
import mine.hilt.data.Openable;
import mine.hilt.module.DaggerFModule;

/**
 * Created by Administrator on 2021/2/10 16:05.
 */
@Component(modules = DaggerFModule.class)
public interface NineComponent {

//    Map<MulKey, Openable> openables();
}