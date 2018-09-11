package mine.loader;

import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

import java.util.Set;

/**
 * Created by Administrator on 2017/4/14.
 */

public class ExampleActivity extends AppCompatActivity {

    public LoaderCallback loaderCallback = new LoaderCallback(this);


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        System.out.println("*******  Example  on  Create  Menu!  *********");
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return super.onCreateOptionsMenu(menu);
        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        System.out.println("*******  Example  on  Prepare  Menu!!!  *********");
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("**********  Example  onCreate  ***********");

        setContentView(R.layout.activity_task);
        if (bundle == null) {
            System.out.println("Example bundle is empty");
        } else {
            System.out.println(bundle.size());
            Set<String> keySet = bundle.keySet();
            for (String i : keySet) {
                System.out.println(i);
            }
        }


    }

    @Override
    protected void onNewIntent(Intent intent) {
        System.out.println("****  Example  onNewIntent  *****");
        super.onNewIntent(intent);
    }


    @Override
    protected void onStart() {
        System.out.println("****Example**onStart*****");
        super.onStart();

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("****  Example  onRestoreInstanceState  *****");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        System.out.println("****Example**onRestart*****");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        System.out.println("****Example**onResume*****");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("****Example**onPause*****");
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        System.out.println("********Example***onBackPressed**********");
        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("****Example**onStop*****");
        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("****  Example   onSaveInstanceState  *****");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("****Example**onDestroy*****");
        super.onDestroy();
    }


    public void initLoad(View view) {

        Uri uri = Uri.parse("content://ABC/Person/*");
        String[] projection = new String[]{
                "_id",
                "person_name",
                "person_age"
        };
        String selection = "person_name = ? and person_age = ?";
        String[] selectionArgs = new String[]{
                "jack",
                "11"
        };
        String sortOrder = "_id";

        Loader loader = getLoaderManager().initLoader(0, null, loaderCallback);
        System.out.println(loader);



    }

    public void restartLoader(View view) {

        Loader loader = getLoaderManager().restartLoader(0, null, loaderCallback);
        System.out.println(loader);
    }


    public void destroyLoad(View view) {

        getLoaderManager().destroyLoader(0);

    }



}

