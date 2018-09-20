package mine.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Administrator on 2018/9/20.
 */
@Dao
public interface EmployeeDao {
    @Query("SELECT * FROM employee")
    List<Employee> getAll();
    
    @Insert(onConflict = OnConflictStrategy.ABORT)
    long[] insert(Employee... employees);

    @Insert
    long insert1(Employee employee);

    @Delete
    int delete(Employee employee);
}
