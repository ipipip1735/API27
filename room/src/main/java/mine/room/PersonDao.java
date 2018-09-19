package mine.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

/**
 * Created by Administrator on 2018/9/18.
 */
@Dao
public interface PersonDao {

    @Query("SELECT * FROM person")
    List<Person> getAll();


    @Query("SELECT * FROM person WHERE pId = :pId")
    Person get1(int pId);

    @Insert()
    long  insert1(Person person);

}
