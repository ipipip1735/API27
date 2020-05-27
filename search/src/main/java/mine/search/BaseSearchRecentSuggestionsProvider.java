package mine.search;

import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import java.util.Arrays;
import java.util.Random;

import static android.app.SearchManager.SUGGEST_COLUMN_INTENT_DATA;

public class BaseSearchRecentSuggestionsProvider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "xxx";
    //    public final static int MODE = DATABASE_MODE_QUERIES;
    public final static int MODE = DATABASE_MODE_QUERIES | DATABASE_MODE_2LINES; //启用附加详细信息

    public BaseSearchRecentSuggestionsProvider() {
        System.out.println("+++ " + this.getClass().getSimpleName() + ".Constructor +++");
        setupSuggestions(AUTHORITY, MODE);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        System.out.println("~~~ " + this.getClass().getSimpleName() + ".query ~~~");

        System.out.println("uri is " + uri);
        System.out.println("projection is " + projection);
        System.out.println("selection is " + selection);
        System.out.println("selectionArgs is " + Arrays.asList(selectionArgs));
        System.out.println("sortOrder is " + sortOrder);


        //方式一：查询数据库


        uri = Uri.parse("content://xxx/suggestions/1");
        selection = "display1 = ?";
        selectionArgs = new String[]{"52"};
        Cursor cursor = super.query(uri, null, selection, selectionArgs, null);


//        Cursor cursor = super.query(uri, projection, selection, selectionArgs, sortOrder);
        System.out.println("count is " + cursor.getCount());
        while (cursor.moveToNext()) {
            for (String name : cursor.getColumnNames())
                System.out.println(name + " is " + cursor.getString(cursor.getColumnIndex(name)));
        }
        return cursor;


        //方式二：杜撰游标
//        return getCursor();

    }


    private Cursor getCursor() {


        int n = 0;
        Random random = new Random();
        String[] colums = {"_id", "suggest_format", "suggest_icon_1", "suggest_text_1", "suggest_text_2", "suggest_intent_query"};
        MatrixCursor matrixCursor = new MatrixCursor(colums);


        for (int i = 2; i > 0; i--) {
            matrixCursor.newRow().add(n++)
                    .add(i)
                    .add("android.resource://system/17301578")
                    .add("OOO" + random.nextInt(999))
                    .add("AAAAA")
                    .add("OOO" + random.nextInt(999));
        }

        Cursor cursor = matrixCursor;


//        while (cursor.moveToNext()) {
//            for (String name : cursor.getColumnNames())
//                System.out.println(name + " is " + cursor.getString(cursor.getColumnIndex(name)));
//        }
//        cursor.moveToFirst();


        return cursor;
    }
}
