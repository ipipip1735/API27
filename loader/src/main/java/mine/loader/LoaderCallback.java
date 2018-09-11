package mine.loader;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
/**
 * Created by Administrator on 2017/4/15.
 */

public class LoaderCallback implements LoaderManager.LoaderCallbacks {

    public Context context = null;

    public LoaderCallback(Context context) {
        this.context = context;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        System.out.println("--------  onCreateLoader  --------");




        String sortOrder = "_id";
        String[] projection = new String[]{
                "_id",
                "person_name",
                "person_age"
        };
        Uri uri = Uri.parse("content://ABC");

        Loader<Cursor> cursorLoader = new CursorLoader(context, uri, projection, null, null, sortOrder);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(android.content.Loader loader, Object data) {
        System.out.println("--------  onLoadFinished  --------");

        System.out.println(loader);
        System.out.println(data);
    }

    @Override
    public void onLoaderReset(android.content.Loader loader) {
        System.out.println("--------  onLoaderReset  --------");



        System.out.println(loader);

    }





}
