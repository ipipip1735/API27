package mine.sqlitedb;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017/4/10.
 */

public class PersonContentProvide extends ContentProvider implements BaseColumns{

    PersonSQLiteOpenHelper personSQLiteOpenHelper = null;
    Context context = null;

    public PersonContentProvide() {
        System.out.println("PersonContentProvide Constructor");
    }

    public PersonContentProvide(Context context) {
        this();
        this.context = context;
        if (personSQLiteOpenHelper == null) {
            personSQLiteOpenHelper = new PersonSQLiteOpenHelper(this.context);
        }
    }


    //    ContentProvider配置
//    static final String PROVIDER_NAME = "mine.testbtn.dao.PersonContentProvide";
    static final String PROVIDER_NAME = "xxxPerson";


    //URI匹配值
    static final int TESTS_SEARCH = 1;
    static final int TESTS_MODIFY = 2;
    static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "Person", TESTS_SEARCH); //search
        uriMatcher.addURI(PROVIDER_NAME, "Person/#", TESTS_MODIFY); //modify
    }


    public static final String DATA_NAME = "Person.db";
    public static final String DATA_VERSION = "1";
    public static final String DATA_TABLE_NAME = "Person";


    // 数据库配置
    private SQLiteDatabase db = null;


    @Override
    public boolean onCreate() {
        System.out.println("******CP*create*begin******");

        if (personSQLiteOpenHelper == null) {
            Context context = getContext();
            personSQLiteOpenHelper = new PersonSQLiteOpenHelper(context);
        }

        System.out.println("******CP*create*over******");
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        System.out.println("person content provide query");
        ToolClass.showThread();
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(DATA_TABLE_NAME);
        String sql = sqLiteQueryBuilder.buildQuery(projection, selection, null, null, sortOrder, null);
        System.out.println("SQL Log :" + sql);

        this.db = this.personSQLiteOpenHelper.getReadableDatabase();
        System.out.println("db is " + this.db);
        Cursor cursor = sqLiteQueryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);


//        cursor.setNotificationUri(this.context.getContentResolver(), uri);
        System.out.println("The returned set is " + cursor.getCount());

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {

        System.out.println("get Type");
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        System.out.println("*********insert*start**********");
        ToolClass.showThread();
        System.out.println("person content provide insert");

//        System.out.println(values.size());
        this.db = this.personSQLiteOpenHelper.getWritableDatabase();
        long rowId = db.insert("Person", "", values);
        uri = ContentUris.withAppendedId(uri, rowId);
        System.out.println(uri.toString());

        this.context.getContentResolver().notifyChange(uri, null);

        System.out.println("*********insert*end**********");
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        System.out.println("person content provide delete");
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        System.out.println("person content provide update");
        return 0;
    }


    @Nullable
    @Override
    public Bundle call(String method, String arg, Bundle extras) {
        return super.call(method, arg, extras);
    }
}
