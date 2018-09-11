package mine.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.database.Cursor;

import java.net.URI;
import java.util.List;

/**
 * Created by Administrator on 2018/9/6.
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first OR last_name LIKE :last")
    List<User> queryUser(String first, String last);

    @Query("SELECT * FROM user WHERE first_name LIKE :first OR last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Query("SELECT * FROM user")
    Cursor loadUser();

    @Insert
    void insertAll(User... users);

    @Insert
    long[] insert(User... users);

    @Insert
    long insert1(User user);

    @Delete
    void delete(User user);

}
