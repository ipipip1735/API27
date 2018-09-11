package mine.contentprovide;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase sqLiteDatabase;
    PersonSQLiteOpenHelper personSQLiteOpenHelper;

    private CursorContentObserver cursorContentObserver;
    private BaseCursorAdapter baseCursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        setContentView(R.layout.activity_main);

        cursorContentObserver = new CursorContentObserver(new Handler(getMainLooper()));
        personSQLiteOpenHelper = new PersonSQLiteOpenHelper(this);

    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestoreInstanceState  *********");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestart  *********");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("*********  " + getClass().getSimpleName() + ".onResume  *********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onSaveInstanceState  *********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");
    }


    public void add(View view) {
        System.out.println("~~button.add~~");
//        matchURI();
//        addRecord();
        addWithCR();

    }

    public void del(View view) {
        System.out.println("~~button.del~~");

        cursorNotify();


    }

    public void query(View view) {
        System.out.println("~~button.query~~");

//        queryWithSelectionArgs();

//        queryProject();
//        queryWithPCP();
        queryWithCR();


    }


    public void start(View view) {
        System.out.println("~~button.start~~");
        createDB();
    }

    public void stop(View view) {
        System.out.println("~~button.stop~~");

    }


    private void addWithCR() {

        String table = "person";
        ContentValues contentValues = new ContentValues();
        contentValues.put("person_name", "jack" + new Random().nextInt(256));
        contentValues.put("person_gender", new Random().nextInt(2));
        contentValues.put("person_age", new Random().nextInt(120));

        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("content://TNT/person");
        uri = contentResolver.insert(uri, contentValues);
        contentResolver.notifyChange(uri, null);

    }

    private void matchURI() {
        Uri uri = Uri.parse("content://AA/bb/2323/cc?q1=1&q2=2#ff");
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("AA", "bb/#/*", 13);
        System.out.println(uriMatcher.match(uri));

        //获取path部分
        System.out.println(uri.getPath());

        //使用JDK获取某个path部分
        Path path = Paths.get(uri.getPath());
        System.out.println(path.getName(2)); //cc
        System.out.println(path.getName(path.getNameCount() - 1)); //等价的，也是cc


    }

    private void addRecord() {
        if (Objects.isNull(sqLiteDatabase))
            sqLiteDatabase = personSQLiteOpenHelper.getWritableDatabase();

        String table = "person";
        ContentValues contentValues = new ContentValues();
        contentValues.put("person_name", "jack" + new Random().nextInt(256));
        contentValues.put("person_gender", new Random().nextInt(2));
        contentValues.put("person_age", new Random().nextInt(120));
        long n = sqLiteDatabase.insert(table, null, contentValues);
        System.out.println("recordID is " + n);

    }


    private void cursorNotify() {

//        Uri uri = Uri.parse("content://TNT/AA/BB/1/t");
//        Handler handler = new Handler(getMainLooper());
//        CursorContentObserver cursorContentObserver = new CursorContentObserver(handler);
        getContentResolver().registerContentObserver(Uri.parse("content://TNT/person"), true, cursorContentObserver);
        getContentResolver().notifyChange(Uri.parse("content://TNT/person"), null);
    }


    private void queryWithCR() {

        Uri uri = Uri.parse("content://TNT/person");

        String[] projection = {
                "_id",
                "name",
                "age",
                "gender",
        };

        String selection = "_id > ?";
        String[] selectionArgs = {"0"};
        String sortOrder = "_id DESC";

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(uri, projection, selection, selectionArgs, sortOrder);

        System.out.println("Position is " + cursor.getPosition());
        System.out.println("count is " + cursor.getCount());


//        while (cursor.moveToNext()) {
//            for (String name : cursor.getColumnNames()) {
//                System.out.println(name + " is " + cursor.getString(cursor.getColumnIndex(name)));
//            }
//            System.out.println("--------");
//        }

        cursor.setNotificationUri(contentResolver, uri);
        baseCursorAdapter = new BaseCursorAdapter(this, cursor, true);

        ListView listView = findViewById(R.id.myLV);
        listView.setAdapter(baseCursorAdapter);


    }

    private void queryWithPCP() {
        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("content://AA/BB/1");
        contentResolver.query(uri, null, null, null, null);

    }

    private void queryProject() {
        if (Objects.isNull(sqLiteDatabase))
            sqLiteDatabase = personSQLiteOpenHelper.getWritableDatabase();

        //数据实体字段
        String[] projection = {
                "man_id",
                "man_name",
                "man_age",
                "man_gender",
        };

        //映射为数据库字段
        HashMap<String, String> userMap = new HashMap<String, String>();
        userMap.put("man_id", "_id AS person_id");
        userMap.put("man_name", "person_name");
        userMap.put("man_age", "person_age");
        userMap.put("man_gender", "person_gender");

        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();

        String selection = "person_id > ? and person_gender = ?";
        String[] selectionArgs = {"0", "0"};
        String orderBy = "person_id DESC";
        String limit = "10";

        sqLiteQueryBuilder.setTables("person");
        sqLiteQueryBuilder.setProjectionMap(userMap);

        String sql = sqLiteQueryBuilder.buildQuery(projection, selection, null, null, orderBy, limit);
        System.out.println("[SQL]" + sql);

        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase, projection, selection, selectionArgs, null, null, orderBy, limit);


        //遍历字段名方法一
        for (String field : cursor.getColumnNames()) {
            System.out.println("field is " + field);
        }

        //遍历字段名方法二
//        System.out.println(cursor.getColumnNames()[0]);
//        System.out.println(cursor.getColumnNames()[1]);
//        System.out.println(cursor.getColumnNames()[2]);
//        System.out.println(cursor.getColumnNames()[3]);


    }


    private void queryWithSelectionArgs() {

        if (Objects.isNull(sqLiteDatabase))
            sqLiteDatabase = personSQLiteOpenHelper.getWritableDatabase();

        String table = "person";
        String[] columns = {"_id AS person_id, person_name, person_age, person_gender"};
        String selection = "person_id > ? and person_gender = ?";
        String[] selectionArgs = {"0", "0"};
        String groupBy = null;
        String having = null;
        String orderBy = "person_id DESC";
        String limit = "10";

        String sql = SQLiteQueryBuilder.buildQueryString(
                true, table, columns, selection, groupBy, having, orderBy, limit);
        System.out.println("[SQL_LOG]" + sql);

        Cursor cursor = sqLiteDatabase.query(table, columns, selection, selectionArgs, null, null, orderBy);

        System.out.println("Count is " + cursor.getCount());
        System.out.println("ColumnCount is " + cursor.getColumnCount());
        System.out.println("......");
        System.out.print("getColumnNames is ");
        for (String s : cursor.getColumnNames()) {
            System.out.print(s + ", ");
        }
        System.out.println("");
//        System.out.println("getColumnIndex is " + cursor.getColumnIndex("person_id"));
//        System.out.println("getColumnName is " + cursor.getColumnName(0));
        System.out.println("......");
//        System.out.println("Position is " + cursor.getPosition());
//        cursor.moveToNext();
//        System.out.println("Position is " + cursor.getPosition());
//        cursor.moveToFirst();
//        System.out.println("Position is " + cursor.getPosition());


//        cursor.move(0);
//        cursor.moveToPosition(0);
//        cursor.moveToFirst();
//        cursor.moveToLast();
//        cursor.moveToNext();
//        cursor.moveToPrevious();

        cursor.move(-1); //返回到初始位置，-1是游标初始位置
        while (cursor.moveToNext()) {
            for (String name : cursor.getColumnNames()) {
                System.out.println(name + " is " + cursor.getString(cursor.getColumnIndex(name)));
            }
        }

        System.out.println("~~~");
        System.out.println("......");
//        cursor.moveToFirst();
//        System.out.println("getType is " + cursor.getType(0));
//        System.out.println("getInt is " + cursor.getInt(1));
//        System.out.println("getShort is " + cursor.getShort(1));
//        System.out.println("getLong is " + cursor.getLong(1));
//        System.out.println("getFloat is " + cursor.getFloat(1));
//        System.out.println("getDouble is " + cursor.getDouble(1));
//        System.out.println("getString is " + cursor.getString(1));
        System.out.println("......");
//        Bundle bundle = new Bundle();
//        bundle.putInt("one", 1);
//        bundle.putInt("two", 2);
//        cursor.setExtras(bundle);
//
//        Bundle bundleResult = cursor.getExtras();
//        System.out.println(bundleResult.getInt("one"));
//        Bundle bundle1 = new Bundle();
//        cursor.respond(bundle1);
//        System.out.println(bundle1.getInt("one"));


    }


    private void createDB() {
        sqLiteDatabase = new PersonSQLiteOpenHelper(this).getWritableDatabase();

    }

}
