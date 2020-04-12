package mine.loader;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


/**
 * Created by Administrator on 2017/4/14.
 */

public class LoaderActivity extends AppCompatActivity {

    LoaderManager.LoaderCallbacks callbacks =  new LoaderManager.LoaderCallbacks<String>() {
        @Override
        public Loader<String> onCreateLoader(int id, Bundle args) {
            System.out.println("******* LoaderManager.onCreateLoader() *********");
            System.out.println(args.get("name"));
        MyLoader<String> loader = new MyLoader<String>(LoaderActivity.this);
//            MyAsyncTaskLoader<String> loader = new MyAsyncTaskLoader<String>(LoaderActivity.this);
            return loader;
        }

        @Override
        public void onLoadFinished(Loader<String> loader, String data) {
            System.out.println("******* LoaderManager.onLoadFinished() *********");

        }

        @Override
        public void onLoaderReset(Loader<String> loader) {
            System.out.println("******* LoaderManager.onLoaderReset() *********");

        }
    };


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("**********  Example  onCreate  ***********");

        setContentView(R.layout.activity_loader);
        TextView textView = (TextView) findViewById(R.id.textView);


    }


    @Override
    protected void onNewIntent(Intent intent) {
        System.out.println("****  Example  onNewIntent  *****");
        super.onNewIntent(intent);
    }


    @Override
    protected void onStart() {
        System.out.println("****   Example  onStart  *****");
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("****  Example  onRestoreInstanceState  *****");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        System.out.println("****   Example  onRestart  *****");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        System.out.println("****   Example  onResume  *****");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("****   Example  onPause  *****");
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        System.out.println("*****   Example  onBackPressed  *****");
        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("*****   Example**onStop  *****");
        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("****  Example  onSaveInstanceState  *****");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("****   Example  onDestroy*****");
        super.onDestroy();
    }


//**************按钮事件******************

    public void addView(View view) {




    }


    public void restartService(View view) {

        Bundle bundle = new Bundle();
        bundle.putString("name", "abc");
        getLoaderManager().restartLoader(0, bundle, callbacks);


    }


    public void stopService(View view) {
        System.out.println("button stop");
        Bundle bundle = new Bundle();
        getLoaderManager().destroyLoader(0);

    }

}