package mine.search;

import android.app.SearchManager;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class BaseSearchRecentSuggestionsProvider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "xxx";
    public final static int MODE = DATABASE_MODE_QUERIES;
//    public final static int MODE = DATABASE_MODE_QUERIES | DATABASE_MODE_2LINES; //启用附加详细信息

    public BaseSearchRecentSuggestionsProvider() {
        System.out.println("+++ " + this.getClass().getSimpleName() + ".Constructor +++");
        setupSuggestions(AUTHORITY, MODE);
    }
}
