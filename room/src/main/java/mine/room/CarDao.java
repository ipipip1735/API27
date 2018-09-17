package mine.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Administrator on 2018/9/6.
 */
@Dao
public interface CarDao {
    @Query("SELECT * FROM car")
    List<Car> getAll();

//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    List<User> loadAllByIds(int[] userIds);
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first OR last_name LIKE :last")
//    List<User> queryUser(String first, String last);
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first OR last_name LIKE :last LIMIT 1")
//    User findByName(String first, String last);
//
//    @Insert
//    void insertAll(User... users);

    @Update(onConflict = OnConflictStrategy.FAIL)



    @Insert
    long[] insert(Car... cars);

    @Insert
    long insert1(Car car);

    @Delete
    void delete(User user);
}
