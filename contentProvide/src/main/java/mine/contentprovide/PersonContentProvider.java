package mine.contentprovide;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Objects;

public class PersonContentProvider extends ContentProvider {

    PersonSQLiteOpenHelper personSQLiteOpenHelper = null;
    private SQLiteDatabase db = null;
    static final String PROVIDER_NAME = "TNT";


    //URI匹配值
    static final int TESTS_SEARCH = 1;
    static final int TESTS_MODIFY = 2;
    static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "person", TESTS_SEARCH); //search
        uriMatcher.addURI(PROVIDER_NAME, "person/#", TESTS_MODIFY); //modify
    }


    @Override
    public boolean onCreate() {
        System.out.println("... " + this.getClass().getSimpleName() + ".onCreate ...");

        if (Objects.isNull(personSQLiteOpenHelper))
            personSQLiteOpenHelper = new PersonSQLiteOpenHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        System.out.println("... " + this.getClass().getSimpleName() + ".query ...");

        String limit = "10";

        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables("person");

        HashMap<String, String> userMap = new HashMap<String, String>();
        userMap.put("_id", "_id");
        userMap.put("name", "person_name AS name");
        userMap.put("age", "person_age AS age");
        userMap.put("gender", "person_gender AS gender");
        sqLiteQueryBuilder.setProjectionMap(userMap);

        String sql = sqLiteQueryBuilder.buildQuery(projection, selection, null, null, sortOrder, limit);
        System.out.println("[SQL]" + sql);


        if (Objects.isNull(db)) db = personSQLiteOpenHelper.getReadableDatabase();
        Cursor cursor = sqLiteQueryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder, limit);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        System.out.println("... " + this.getClass().getSimpleName() + ".query ...");
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        System.out.println("... " + this.getClass().getSimpleName() + ".insert ...");


        if (Objects.isNull(db)) db = personSQLiteOpenHelper.getWritableDatabase();
        long n = db.insert("person", null, values);
        System.out.println("record inserted is " + n);

        return Uri.parse("content://TNT/person/" + n);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        System.out.println("... " + this.getClass().getSimpleName() + ".delete ...");
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        System.out.println("... " + this.getClass().getSimpleName() + ".update ...");
        return 0;
    }

}
