package mine.search;

import android.app.SearchManager;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Arrays;

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
        System.out.println(Arrays.asList(selectionArgs));
        System.out.println("sortOrder is " + sortOrder);

        return super.query(uri, projection, selection, selectionArgs, sortOrder);
    }
}
