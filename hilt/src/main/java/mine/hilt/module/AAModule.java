package mine.hilt.module;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.migration.DisableInstallInCheck;
import mine.hilt.data.Room;

/**
 * Created by Administrator on 2021/2/2 18:43.
 */
@Module(includes = BBModule.class)
@DisableInstallInCheck
public class AAModule {

    @Provides
    public Room room(){
     return new Room();
    }
}
