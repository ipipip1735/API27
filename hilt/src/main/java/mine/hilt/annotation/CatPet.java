package mine.hilt.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Administrator on 2020/12/24.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface CatPet {}
