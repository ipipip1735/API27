package mine.utilities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LruCache;
import android.view.View;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    LruCache<String, Integer> cache;
    Map map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        setContentView(R.layout.activity_main);

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


    public void init(View view) {
        System.out.println("~~button.init~~");


//        int cacheSize = 4 * 1024 * 1024; // 4MiB
//        LruCache<String, Integer> bitmapCache = new LruCache<String, Integer>(cacheSize) {
//            protected int sizeOf(String key,  Integer value) {
//                return 4;
//            }
//        };

        cache = new LruCache<String, Integer>(5) {
            @Override
            protected Integer create(String key) {
                System.out.println("~~create~~");
                System.out.println("key is " + key);
//                return super.create(key);
                return Integer.valueOf(55);
            }

            @Override
            protected void entryRemoved(boolean evicted, String key, Integer oldValue, Integer newValue) {
                System.out.println("~~entryRemoved~~");
                System.out.println("evicted is " + evicted);
                System.out.println("key is " + key);
                System.out.println("oldValue is " + oldValue);
                System.out.println("newValue is " + newValue);

                super.entryRemoved(evicted, key, oldValue, newValue);
            }
        };
        cache.put("zero", 0);
        cache.put("one", 1);
        cache.put("two", 2);
        cache.put("three", 3);
        cache.put("four", 4);


    }


    public void get(View view) {
        System.out.println("~~button.get~~");

        Integer integer = cache.get("one");
        System.out.println(integer);

    }


    public void add(View view) {
        System.out.println("~~button.add~~");

        cache.put("five", 12);
        System.out.println(cache.get("five"));

    }

    public void del(View view) {
        System.out.println("~~button.del~~");


        cache.remove("one");


//        cache.entryRemoved();

    }

    public void info(View view) {
        System.out.println("~~button.info~~");

        System.out.println(cache);

    }

    public void resize(View view) {
        System.out.println("~~button.resize~~");

        cache.resize(3);
//        cache.trimToSize(25);

    }


    public void snapshot(View view) {
        System.out.println("~~button.snapshot~~");

        System.out.println(map);
        map = cache.snapshot();
        System.out.println(map);

    }
}
