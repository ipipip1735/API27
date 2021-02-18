package mine.hilt.annotation;

import dagger.MapKey;
import mine.hilt.data.Color;

/**
 * Created by Administrator on 2021/2/9.
 */
@MapKey
public @interface ColorKey {
    Color value();
}
