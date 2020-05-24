package mine.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

/**
 * Created by Administrator on 2017/4/14.
 */

public class ExampleActivity extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        System.out.println("~~~~ " + getClass().getSimpleName() + ".onCreateOptionsMenu ~~~~");

        getMenuInflater().inflate(R.menu.menu_opts, menu);
//        boolean b = super.onCreateOptionsMenu(menu);
//        System.out.println(b);
//        return b;
        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        System.out.println("~~~~ " + getClass().getSimpleName() + ".onPrepareOptionsMenu ~~~~");
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    protected void onCreate(Bundle bundle) {
        System.out.println("~~~~ " + getClass().getSimpleName() + ".onCreate ~~~~");
        super.onCreate(bundle);

        setContentView(R.layout.activity_option_menu);


//        if (bundle == null) {
//            System.out.println("Example bundle is empty");
//        } else {
//            System.out.println(bundle.size());
//            Set<String> keySet = bundle.keySet();
//            for (String i : keySet) {
//                System.out.println(i);
//            }
//        }


//        System.out.println(getWindow());

    }


    @Override
    protected void onStart() {
        System.out.println("~~~~ " + getClass().getSimpleName() + ".onStart ~~~~");
        super.onStart();

    }

    @Override
    protected void onRestart() {
        System.out.println("~~~~ " + getClass().getSimpleName() + ".onRestart ~~~~");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        System.out.println("~~~~ " + getClass().getSimpleName() + ".onResume ~~~~");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("~~~~ " + getClass().getSimpleName() + ".onPause ~~~~");
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        System.out.println("~~~~ " + getClass().getSimpleName() + ".onBackPressed ~~~~");
        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("~~~~ " + getClass().getSimpleName() + ".onStop ~~~~");
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        System.out.println("~~~~ " + getClass().getSimpleName() + ".onDestroy ~~~~");
        super.onDestroy();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("~~~~ " + getClass().getSimpleName() + ".onSaveInstanceState ~~~~");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        System.out.println("~~~~ " + getClass().getSimpleName() + ".onNewIntent ~~~~");
        super.onNewIntent(intent);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("~~~~ " + getClass().getSimpleName() + ".onRestoreInstanceState ~~~~");
        super.onRestoreInstanceState(savedInstanceState);
    }


}

