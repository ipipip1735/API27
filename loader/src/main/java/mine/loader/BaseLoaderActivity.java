package mine.loader;

import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.Objects;
import java.util.Random;

public class BaseLoaderActivity extends AppCompatActivity {

    private int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("**********  Main.onStart  ***********");
        setContentView(R.layout.activity_loader);

        //初始化Loader
//        getLoaderManager().initLoader(++n, null, new MyLoaderCallback(this));
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
        getLoaderManager().initLoader(0, null, new MyLoaderCallback(this));
        getLoaderManager().enableDebugLogging(true);
    }

    public void cancelling(View view) {
        System.out.println("~~cancelling~~");

        Loader loader = getLoaderManager().getLoader(0);
        System.out.println(loader);

        if(Objects.nonNull(loader))
        loader.cancelLoad(); //一般不需要取消，这里是为了学习，LoaderManager摧毁和重启前将自动取消。LoaderManager也没有取消的API

    }

    public void reloading(View view) {
        System.out.println("~~reloading~~");

        getLoaderManager().restartLoader(0, null, new MyLoaderCallback(this));

    }


    public void del(View view) {
        System.out.println("~~del~~");
        getLoaderManager().destroyLoader(0);
    }



    public void query(View view) {
        System.out.println("~~query~~");

//        int id = 0;
//        while ()
        Loader loader;
        loader = getLoaderManager().getLoader(0);
        System.out.println(loader);


        loader = getLoaderManager().getLoader(1);
        System.out.println(loader);

        loader = getLoaderManager().getLoader(2);
        System.out.println(loader);



    }
}



class MyLoaderCallback implements LoaderManager.LoaderCallbacks {

    Context context;

    public MyLoaderCallback(Context context) {
        this.context = context;
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        System.out.println("--- " + getClass().getSimpleName() + ".onCreateLoader ---");
        System.out.println("loader'id is " + id);

        BaseLoader baseLoader = new BaseLoader(context);
        return baseLoader;
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
        loader.rollbackContentChanged();


    }
}
