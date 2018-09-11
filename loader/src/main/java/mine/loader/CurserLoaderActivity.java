package mine.loader;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class CurserLoaderActivity extends AppCompatActivity {

    BaseCursorAdapter baseCursorAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("**********" + getClass().getSimpleName() + ".onCreate  ***********");
        setContentView(R.layout.activity_curser_loader);
        listView = findViewById(R.id.myLV);
    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("**********" + getClass().getSimpleName() + ".onStart  ***********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("**********" + getClass().getSimpleName() + ".onRestoreInstanceState  ***********");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("**********" + getClass().getSimpleName() + ".onRestart  ***********");
        getLoaderManager().enableDebugLogging(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("**********" + getClass().getSimpleName() + ".onResume  ***********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("**********" + getClass().getSimpleName() + ".onPause  ***********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("**********" + getClass().getSimpleName() + ".onBackPressed  ***********");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("**********" + getClass().getSimpleName() + ".onStop  ***********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("**********" + getClass().getSimpleName() + ".onSaveInstanceState  ***********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("**********" + getClass().getSimpleName() + ".onDestroy  ***********");
    }


    public void loading(View view) {
        System.out.println("~~button.loading~~");
        getLoaderManager().initLoader(0, null, new CursorLoaderCallback(this, baseCursorAdapter, listView));

    }

    public void cancelling(View view) {
        System.out.println("~~button.cancelling~~");
    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

        getLoaderManager().restartLoader(0, null, new CursorLoaderCallback(this, baseCursorAdapter, listView));

    }


    public void del(View view) {
        System.out.println("~~button.del~~");
        getLoaderManager().destroyLoader(0);
    }



    public void notify(View view) {
        System.out.println("~~button.notify~~");

        getContentResolver().notifyChange(Uri.parse("content://TNT/person"), null);



//        getLoaderManager().getLoader(0).onContentChanged();


    }
}


class CursorLoaderCallback implements LoaderManager.LoaderCallbacks {

    Context context;
    BaseCursorAdapter baseCursorAdapter;
    ListView listView;

    public CursorLoaderCallback(Context context, BaseCursorAdapter baseCursorAdapter, ListView listView) {
        this.context = context;
        this.baseCursorAdapter = baseCursorAdapter;
        this.listView = listView;
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        System.out.println("--- " + getClass().getSimpleName() + ".onCreateLoader ---");

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
        CursorLoader cursorLoader = new CursorLoader(context, uri, projection, selection, selectionArgs, sortOrder);


        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        System.out.println("--- " + getClass().getSimpleName() + ".onLoadFinished ---");
        System.out.println(loader.dataToString(data) + " is " + data);

        CursorLoader cursorLoader = (CursorLoader) loader;
        Cursor cursor = (Cursor) data;
        cursor.setNotificationUri(context.getContentResolver(), cursorLoader.getUri());


        baseCursorAdapter = new BaseCursorAdapter(context, cursor, true);
        listView.setAdapter(baseCursorAdapter);


    }

    @Override
    public void onLoaderReset(Loader loader) {
        System.out.println("--- " + getClass().getSimpleName() + ".onLoaderReset ---");

        baseCursorAdapter.changeCursor(null);

    }
}