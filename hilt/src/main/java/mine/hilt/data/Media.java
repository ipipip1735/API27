package mine.hilt.data;

import javax.inject.Singleton;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

/**
 * Created by Administrator on 2020/12/29 19:03.
 */
@EntryPoint
@InstallIn(Singleton.class)
public interface Media {
    void play();
}
