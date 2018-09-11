package mine.sqlitedb;


import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Random;

/**
 * Created by Administrator on 2017/4/10.
 */

public class PersonSQLiteOpenHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "Person.db"; //数据库名称
    private static final int VERSION = 1; //数据库版本


    public PersonSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        System.out.println("PersonSQLiteOpenHelper Constructor");
    }


    //======================================================

    @Override
    public void onCreate(SQLiteDatabase db) {

        System.out.println("************on create***********");

        String sql = "create table IF NOT EXISTS " +
                "Person(" +
                "_id integer primary key," +
                "person_name char(20)," +
                "person_age integer" +
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("************on Upgrade**start**********");

        System.out.println(oldVersion);
        System.out.println(newVersion);
        String sql = "drop table person;";
        System.out.println(sql);
        db.execSQL(sql);

        sql = "create table IF NOT EXISTS " +
                "Person(" +
                "_id integer primary key," +
                "person_name char(20)," +
                "person_age integer" +
                ");";
        System.out.println(sql);
        db.execSQL(sql);
        System.out.println("************on Upgrade**end**********");


    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        System.out.println("*********on open*************");
        ToolClass.showThread();

        int age = new Random().nextInt(100);
        System.out.println("age is " + age);
        String sql = "insert into Person(person_name, person_age) values('jack', " + age + ")";
        System.out.println(sql);
        db.execSQL(sql);
        Cursor cursor = db.query("Person", null, null, null, null, null, null);
        System.out.println("table person contain'set is " + cursor.getCount());
    }
}
