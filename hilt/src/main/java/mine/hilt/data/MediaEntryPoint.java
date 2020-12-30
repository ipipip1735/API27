package mine.hilt.data;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * Created by Administrator on 2020/12/29 19:03.
 */
@EntryPoint
@InstallIn(ActivityComponent.class)
public interface MediaEntryPoint {
    Media mediaImplement();
}
