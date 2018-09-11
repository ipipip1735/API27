package mine.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.List;

/**
 * Created by Administrator on 2018/9/6.
 */
@Dao
public interface TeacherDao {
    @Query("SELECT * FROM teacher")
    List<TeacherRelation> getAll();

    @Query("SELECT * FROM teacher ORDER BY tId DESC LIMIT 1")
    TeacherRelation get1();

    @Insert
    long insert1(Teacher teacher);

    @Delete
    void delete(Teacher teacher);

}
