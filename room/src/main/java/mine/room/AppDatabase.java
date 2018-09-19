package mine.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

/**
 * Created by Administrator on 2018/9/6.
 */
@Database(entities = {User.class, Car.class, Teacher.class, CarUser.class, Person.class},
        version = 1,
        exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract CarDao carDao();
    public abstract PersonDao personDao();
    public abstract CustomDao customDao();
    public abstract TeacherDao teacherDao();
}
