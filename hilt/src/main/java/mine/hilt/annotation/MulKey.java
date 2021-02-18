package mine.hilt.annotation;

import dagger.MapKey;

/**
 * Created by Administrator on 2021/2/10.
 */
//@MapKey(unwrapValue = false)
public @interface MulKey {
    String value();
    int age();
}