package mine.search;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.net.Uri;

import static android.content.SearchRecentSuggestionsProvider.DATABASE_MODE_2LINES;
import static android.content.SearchRecentSuggestionsProvider.DATABASE_MODE_QUERIES;

public class CustomSearchRecentSuggestionsProvider extends ContentProvider {
    public final static String AUTHORITY = "xxx";
//    public final static int MODE = DATABASE_MODE_QUERIES;
    public final static int MODE = DATABASE_MODE_QUERIES | DATABASE_MODE_2LINES; //启用附加详细信息

    public CustomSearchRecentSuggestionsProvider() {
        System.out.println("+++ " + this.getClass().getSimpleName() + ".Constructor +++");
    }

    @Override
    public boolean onCreate() {
        return false;
    }
    
    @Override
    public Cursor query( Uri uri,  String[] projection,  String selection,  String[] selectionArgs,  String sortOrder) {
        return null;
    }
    
    @Override
    public String getType( Uri uri) {
        return null;
    }
    
    @Override
    public Uri insert( Uri uri,  ContentValues values) {
        return null;
    }

    @Override
    public int delete( Uri uri,  String selection,  String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update( Uri uri,  ContentValues values,  String selection,  String[] selectionArgs) {
        return 0;
    }


}
