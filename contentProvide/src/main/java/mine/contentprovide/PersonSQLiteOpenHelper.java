package mine.contentprovide;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class PersonSQLiteOpenHelper extends SQLiteOpenHelper implements BaseColumns {

    private static final String DB_NAME = "person.db";
    private static final String TABLE_NAME = "person";
    private static final int VERSION = 1;

    public PersonSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }


    @Override
    public void onConfigure(SQLiteDatabase db) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onConfigure  *********");
        super.onConfigure(db);
        //方法一
//        db.execSQL("PRAGMA user_version=0;");
        //方法二
         db.setVersion(1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        String sql = "CREATE TABLE " + TABLE_NAME + "(\n" +
                _ID + " integer primary key autoincrement,\n" +
                "person_name char(20) not null default ('anonymous'),\n" +
                "person_gender int(1) not null default (0),\n" +
                "person_age int(3) not null default(0));";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onUpgrade  *********");

        System.out.println("db is " + db.getPath());
        System.out.println("version is " + db.getVersion());
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onDowngrade  *********");
//        super.onDowngrade(db, oldVersion, newVersion);

        System.out.println("db is " + db.getPath());
        System.out.println("version is " + db.getVersion());
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onOpen  *********");
        super.onOpen(db);

        System.out.println("db is " + db.getPath());
        System.out.println("version is " + db.getVersion());
    }

}
