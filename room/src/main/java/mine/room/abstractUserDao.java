package mine.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RawQuery;
import android.arch.persistence.room.Transaction;

import java.util.List;

/**
 * Created by Administrator on 2018/9/10.
 */
@Dao
public abstract class abstractUserDao {
    @Insert
    public abstract void insertAll(User... users);

    @Delete
    public abstract void delete(User user);
    @RawQuery

    @Transaction
    public void insertAndDeleteInTransaction(User ...users) {
        insertAll(users[0]);
        delete(users[1]);
    }
}

