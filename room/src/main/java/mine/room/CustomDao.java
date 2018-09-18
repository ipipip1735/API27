package mine.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import java.util.List;

/**
 * Created by Administrator on 2018/9/18.
 */
@Dao
public interface CustomDao {
    @Query("SELECT * FROM Customer")
    Cursor getAll();

    @Insert
    long insert1(CarUser carUser);

}
