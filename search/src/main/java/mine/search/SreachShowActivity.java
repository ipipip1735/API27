package mine.search;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import java.util.Random;

public class SreachShowActivity extends AppCompatActivity {

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_main);

        SearchManager searchManager = getSystemService(SearchManager.class);
        searchManager.setOnCancelListener(new SearchManager.OnCancelListener() {
            @Override
            public void onCancel() {
                System.out.println("~~onCancel~~");
            }
        });
        searchManager.setOnDismissListener(new SearchManager.OnDismissListener() {
            @Override
            public void onDismiss() {
                System.out.println("~~onDismiss~~");
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestoreInstanceState  *********");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        System.out.println("*********  " + getClass().getSimpleName() + ".onNewIntent  *********");

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            System.out.println("query is " + query);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestart  *********");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("*********  " + getClass().getSimpleName() + ".onResume  *********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("*********  " + getClass().getSimpleName() + ".onBackPressed  *********");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateOptionsMenu  *********");

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);

        SearchManager searchManager = getSystemService(SearchManager.class);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); //显示输入框，而不是图标
        searchView.setSubmitButtonEnabled(true); //显示提交按钮
        searchView.setQueryRefinementEnabled(true); //在每个查询提示的item尾部增加精化按钮
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onPrepareOptionsMenu  *********");

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onOptionsItemSelected  *********");

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onSaveInstanceState  *********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");
    }


    public void start(View view) {
        System.out.println("~~button.start~~");
        //方法一
        boolean b = onSearchRequested();
        System.out.println(b);

        //方法二
//        Bundle bundle = new Bundle();
//        bundle.putInt("one", 1111);
//        startSearch("AAAAA", true, bundle, false);

        //方法三
//        Bundle bundle = new Bundle();
//        bundle.putInt("one", 1111);
//        triggerSearch("one", bundle);


    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

        SearchManager searchManager = getSystemService(SearchManager.class);
        searchManager.stopSearch(); //停止语言搜索


    }

    public void trigger(View view) {
        System.out.println("~~button.trigger~~");


    }

    public void del(View view) {
        System.out.println("~~button.del~~");


    }


    public void replace(View view) {
        System.out.println("~~button.replace~~");

    }


    public void query(View view) {
        System.out.println("~~button.query~~");


        int n=0;
        Random random = new Random();
        String[] colums = {"_id", "name", "age"};
        MatrixCursor matrixCursor = new MatrixCursor(colums);
        matrixCursor.newRow().add(n++).add("name" + random.nextInt(100)).add(random.nextInt(100));
        matrixCursor.newRow().add(n++).add("name" + random.nextInt(100)).add(random.nextInt(100));
        matrixCursor.newRow().add(n++).add("name" + random.nextInt(100)).add(random.nextInt(100));
        matrixCursor.newRow().add(n++).add("name" + random.nextInt(100)).add(random.nextInt(100));
        matrixCursor.newRow().add(n++).add("name" + random.nextInt(100)).add(random.nextInt(100));
        matrixCursor.newRow().add(n++).add("name" + random.nextInt(100)).add(random.nextInt(100));


        Cursor cursor = matrixCursor;


        while (cursor.moveToNext()) {
            for (String name : cursor.getColumnNames())
                System.out.println(name + " is " + cursor.getString(cursor.getColumnIndex(name)));
        }

    }
}
