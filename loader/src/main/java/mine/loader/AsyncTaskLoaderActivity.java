package mine.loader;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AsyncTaskLoaderActivity extends AppCompatActivity {
    BaseCursorAdapter baseCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("**********  Main.onStart  ***********");
        setContentView(R.layout.activity_loader);
    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("**********  Main.onStart  ***********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("**********  Main.onRestoreInstanceState  ***********");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("**********  Main.onRestart  ***********");
        getLoaderManager().enableDebugLogging(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("**********  Main.onResume  ***********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("**********  Main.onPause  ***********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("**********  Main.onBackPressed  ***********");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("**********  Main.onStop  ***********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("**********  Main.onSaveInstanceState  ***********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("**********  Main.onDestroy  ***********");
    }


    public void loading(View view) {
        System.out.println("~~loading~~");
        getLoaderManager().initLoader(0, null, new AsyncTaskLoaderCallback(this));

    }

    public void cancelling(View view) {
        System.out.println("~~cancelling~~");
        getLoaderManager().destroyLoader(0);
    }

    public void reloading(View view) {
        System.out.println("~~reloading~~");

        getLoaderManager().restartLoader(0, null, new AsyncTaskLoaderCallback(this));

    }


    public void del(View view) {
        System.out.println("~~del~~");
        getLoaderManager().destroyLoader(0);
    }



    public void query(View view) {
        System.out.println("~~query~~");

    }
}



class AsyncTaskLoaderCallback implements LoaderManager.LoaderCallbacks {

    Context context;

    public AsyncTaskLoaderCallback(Context context) {
        this.context = context;
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        System.out.println("--- " + getClass().getSimpleName() + ".onCreateLoader ---");

        BaseAsyncTaskLoader baseAsyncTaskLoader = new BaseAsyncTaskLoader(context);
        return baseAsyncTaskLoader;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        System.out.println("--- " + getClass().getSimpleName() + ".onLoadFinished ---");
        System.out.println(loader.dataToString(data) + " is " + data);


    }

    @Override
    public void onLoaderReset(Loader loader) {
        System.out.println("--- " + getClass().getSimpleName() + ".onLoaderReset ---");

    }
}
