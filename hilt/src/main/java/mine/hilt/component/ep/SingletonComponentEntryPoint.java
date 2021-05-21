package mine.hilt.component.ep;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import mine.hilt.data.Basketball;

/**
 * Created by Administrator on 2021/5/15.
 */
@EntryPoint
@InstallIn(SingletonComponent.class)
public interface SingletonComponentEntryPoint {
    Basketball getBasketball();
}