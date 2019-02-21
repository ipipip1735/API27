package mine.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Administrator on 2017/4/14.
 */

public class ActionBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("**********  ActionBarActivity  onCreate  ***********");

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateOptionsMenu  *********");

        getMenuInflater().inflate(R.menu.menu_opts, menu);

        MenuItemCompat.OnActionExpandListener expandListener = new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                System.out.println("~~onMenuItemActionCollapse~~");
                return true;
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                System.out.println("~~onMenuItemActionExpand~~");
                return true;
            }
        };


        MenuItem actionMenuItem = menu.findItem(R.id.action_search);
        MenuItemCompat.setOnActionExpandListener(actionMenuItem, expandListener);


        return super.onPrepareOptionsMenu(menu);
//        return false;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onPrepareOptionsMenu  *********");

        System.out.println("menu' count is " + menu.size());
        System.out.println(menu.getItem(2).setTitle("Start Activity"));
        return super.onPrepareOptionsMenu(menu);
//        return false;
    }


    @Override
    public void onOptionsMenuClosed(Menu menu) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onOptionsMenuClosed  *********");
        super.onOptionsMenuClosed(menu);

        System.out.println("menu is " + menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onOptionsItemSelected  *********");
        System.out.println("item is " + item);
        System.out.println("getItemId is " + item.getItemId());


        switch (item.getItemId()) {
            case R.id.itemTwo:
                startActivity(new Intent(this, MainActivity.class));
//                item.setActionProvider();
                break;
            default:
        }


        return true;
    }


    public void itemOne(MenuItem item) {
        System.out.println(".. customButton ..");
    }


    public void start(View view) {
        System.out.println("~~button.start~~");

    }

    public void stop(View view) {
        System.out.println("~~button.stop~~");
    }
}

