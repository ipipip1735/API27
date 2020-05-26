package mine.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

/**
 * Created by Administrator on 2017/4/14.
 */

public class ActionBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("**********  ActionBarActivity  onCreate  ***********");


        ActionBar actionBar = getSupportActionBar();
//        System.out.println(actionBar);
//        actionBar.setDisplayShowCustomEnabled(true);
//        View v = View.inflate(this, R.layout.menu_header, null);
//        actionBar.setCustomView(v);




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


//        MenuItem actionMenuItem = menu.findItem(R.id.action_search);
//        MenuItemCompat.setOnActionExpandListener(actionMenuItem, expandListener);


        return super.onCreateOptionsMenu(menu);
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

        //窗口变色
        getWindow().setBackgroundDrawableResource(R.color.MediumOrchid);


        //ActionBar变色，默认的状态条颜色太深
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(
                getResources().getDrawable(R.color.BurlyWood, null));


        System.out.println("getTitle is " + actionBar.getTitle());
        System.out.println("getSubtitle is " + actionBar.getSubtitle());


    }

    public void del(View view) {
        System.out.println("~~button.del~~");

        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_launcher_foreground);

        //增加自定义View
        actionBar.setDisplayShowCustomEnabled(true);//启用自定义View
        View v = View.inflate(this, R.layout.menu_header, null);
        actionBar.setCustomView(v);

//        v = actionBar.getCustomView();
//        System.out.println("v is " + v);


//        System.out.println(v.getParent());
//        System.out.println(v.getParent().getParent());
//        System.out.println(v.getParent().getParent().getParent());


        View view1 = (View) v.getParent();//获取父View
        walk(view1, "-");//父View


    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

        walk(getWindow().getDecorView(), "");

    }

    private void walk(View view, String split) {

        System.out.println(split + "->" + view);
        if (view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) view;
            for (int i = 0; i < vg.getChildCount(); i++) {
                walk(vg.getChildAt(i), split + "-");
            }
            return;
        }
    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

        ViewGroup vg = (ViewGroup) getWindow().getDecorView();
        System.out.println("vg is " + vg);
        vg.setBackground(getResources().getDrawable(R.color.BlueViolet, null));

        ViewGroup vg2 = (ViewGroup) vg.getChildAt(0);
        System.out.println("vg2 is " + vg2);
        vg2.setBackground(getResources().getDrawable(R.color.fuchsia, null));

        View vg21 = vg.getChildAt(1);
        System.out.println("vg2-1 is " + vg21);
        vg21.setBackground(getResources().getDrawable(R.color.Gold, null));

        View vg22 = vg.getChildAt(2);
        System.out.println("vg2-2 is " + vg22);
        vg22.setBackground(getResources().getDrawable(R.color.DeepPink, null));


        ViewGroup vg3 = (ViewGroup) vg2.getChildAt(1);
        System.out.println("vg3 is " + vg3);
        vg3.setBackground(getResources().getDrawable(R.color.aqua, null));


        ViewGroup vg4 = (ViewGroup) vg3.getChildAt(0);
        System.out.println("vg4 is " + vg4);
        vg4.setBackground(getResources().getDrawable(R.color.lime, null));


        ViewGroup vg5 = (ViewGroup) vg4.getChildAt(1);
        System.out.println("vg5 is " + vg5);
        vg5.setBackground(getResources().getDrawable(R.color.Orange, null));


        ViewGroup vg6 = (ViewGroup) vg5.getChildAt(0);
        System.out.println("vg6 is " + vg6);
        vg6.setBackground(getResources().getDrawable(R.color.BlueViolet, null));


        ViewGroup vg7 = (ViewGroup) vg6.getChildAt(1);
        System.out.println("vg7 is " + vg7);
        vg7.setBackground(getResources().getDrawable(R.color.LightCoral, null));


    }


    public void unbind(View view) {
        System.out.println("~~button.unbind~~");
        View v = getWindow().getDecorView().findViewById(android.R.id.content);
//        View v = getWindow().getDecorView().findViewById(R.id.action_bar);
//        View v = getWindow().getDecorView().findViewById(android.R.id.statusBarBackground);
//        View v = getWindow().getDecorView().findViewById(R.id.action_mode_bar_stub);//此ID无法使用
//        View v = getWindow().getDecorView().findViewById(android.R.id.navigationBarBackground);
//        View v = getWindow().getDecorView().findViewById(R.id.decor_content_parent);
        v.setBackground(getResources().getDrawable(R.color.BlueViolet, null));


    }

}

