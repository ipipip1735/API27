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
public interface CompanyDao {
    @Query("SELECT * FROM company")
    List<Company> getAll();

    @Query("SELECT * FROM company")
    List<CompanyEmployee> getCompanyEmployee();


    @Insert(onConflict = OnConflictStrategy.ABORT)
    long[] insert(Company... companys);

    @Insert
    long insert1(Company company);

    @Delete
    int delete(Company company);
}
