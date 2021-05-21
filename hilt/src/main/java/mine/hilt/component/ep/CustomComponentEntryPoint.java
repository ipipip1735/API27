package mine.hilt.component.ep;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import mine.hilt.component.CustomComponent;
import mine.hilt.data.Person;
import mine.hilt.data.Volleyball;

/**
 * Created by Administrator on 2021/5/15.
 */
@EntryPoint
@InstallIn(CustomComponent.class)
public interface CustomComponentEntryPoint {
    Volleyball getVolleyball();
    Person getPerson();
}
