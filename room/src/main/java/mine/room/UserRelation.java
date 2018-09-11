package mine.room;

import android.arch.persistence.room.Relation;

import java.util.List;

/**
 * Created by Administrator on 2018/9/10.
 */
public class UserRelation {
    int uid;
    @Relation(parentColumn = "uid", entityColumn = "uid")
    public List<User> users;
    
    
}
