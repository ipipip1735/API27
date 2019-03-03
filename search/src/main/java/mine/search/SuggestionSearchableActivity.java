package mine.search;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Objects;

public class SuggestionSearchableActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_main);

        System.out.println("getAction is " + getIntent().getAction());
        System.out.println("getData is " + getIntent().getData());
        System.out.println("getDataString is " + getIntent().getDataString());


        //获取查询数据
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            System.out.println("query is " + query);
            //获取额外数据
            Bundle bundle = intent.getBundleExtra(SearchManager.APP_DATA);
            if (Objects.nonNull(bundle)) {

                int n = bundle.getInt("one");
                System.out.println(n);
            }

            if (Objects.nonNull(query)) {


                //方式二：保存查询内容
//                SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
//                        BaseSearchRecentSuggestionsProvider.AUTHORITY, BaseSearchRecentSuggestionsProvider.MODE);
//                suggestions.saveRecentQuery(query, null);

                //方式二：保存查询内容，并附加详情
                SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                        BaseSearchRecentSuggestionsProvider.AUTHORITY, BaseSearchRecentSuggestionsProvider.MODE);
                suggestions.saveRecentQuery(query, "AAAAAAAAAAA");

            }
        }





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
        System.out.println("*********  " + getClass().getSimpleName() + ".onNewIntent  *********");
        super.onNewIntent(intent);
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
        boolean b = onSearchRequested();
        System.out.println(b);
    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

    }

    public void add(View view) {
        System.out.println("~~button.add~~");

    }

    public void del(View view) {
        System.out.println("~~button.del~~");

    }


    public void replace(View view) {
        System.out.println("~~button.replace~~");

    }


    public void query(View view) {
        System.out.println("~~button.query~~");

    }
}
