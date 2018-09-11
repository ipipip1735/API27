package mine.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Administrator on 2018/9/6.
 */
@Database(entities = {User.class, Car.class, Teacher.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract CarDao carDao();
    public abstract TeacherDao teacherDao();
}
