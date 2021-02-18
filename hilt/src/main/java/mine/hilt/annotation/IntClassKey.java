package mine.hilt.annotation;

import dagger.MapKey;

/**
 * Created by Administrator on 2021/2/9.
 */
@MapKey
public @interface IntClassKey {
    Class<? extends Integer> value();
}
