package mine.room;

import android.arch.persistence.room.ColumnInfo;

/**
 * Created by Administrator on 2018/9/9.
 */
public class Address {
    public String street;
    public String state;
    public String city;

    @ColumnInfo(name = "post_code")
    public int postCode;


}

