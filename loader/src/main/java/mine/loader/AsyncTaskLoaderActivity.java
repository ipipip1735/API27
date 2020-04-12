package mine.loader;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AsyncTaskLoaderActivity extends AppCompatActivity {
    BaseCursorAdapter baseCursorAdapter;

    LoaderManager.LoaderCallbacks callback = new LoaderManager.LoaderCallbacks() {

        @Override
        public Loader onCreateLoader(int id, Bundle args) {
            System.out.println("--- " + getClass().getSimpleName() + ".onCreateLoader ---");
            BaseAsyncTaskLoader baseAsyncTaskLoader = new BaseAsyncTaskLoader(AsyncTaskLoaderActivity.this);
            return baseAsyncTaskLoader;
        }

        @Override
        public void onLoadFinished(Loader loader, Object data) {
            System.out.println("--- " + getClass().getSimpleName() + ".onLoadFinished ---");

            System.out.println("loader is " + loader);
            System.out.println(loader.dataToString(data) + " is " + data);
        }

        @Override
        public void onLoaderReset(Loader loader) {
            System.out.println("--- " + getClass().getSimpleName() + ".onLoaderReset ---");

            System.out.println("loader is " + loader);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("**********  Main.onStart  ***********");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
    }


    @Override
    protected void onStart() {
        System.out.println("**********  Main.onStart  ***********");
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("**********  Main.onRestoreInstanceState  ***********");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        System.out.println("**********  Main.onRestart  ***********");
        super.onRestart();
        getLoaderManager().enableDebugLogging(true);
    }

    @Override
    protected void onResume() {
        System.out.println("**********  Main.onResume  ***********");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("**********  Main.onPause  ***********");
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        System.out.println("**********  Main.onBackPressed  ***********");
        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("**********  Main.onStop  ***********");
        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("**********  Main.onSaveInstanceState  ***********");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("**********  Main.onDestroy  ***********");
        super.onDestroy();
    }


    public void loading(View view) {
        System.out.println("~~loading~~");
        getLoaderManager().initLoader(0, null, callback);

    }

    public void cancelling(View view) {
        System.out.println("~~cancelling~~");
        getLoaderManager().destroyLoader(0);
    }

    public void reloading(View view) {
        System.out.println("~~reloading~~");

        getLoaderManager().restartLoader(0, null, callback);

    }


    public void del(View view) {
        System.out.println("~~del~~");
        getLoaderManager().destroyLoader(0);
    }



    public void query(View view) {
        System.out.println("~~query~~");

    }
}
