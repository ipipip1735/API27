package mine.menu;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/4/14.
 */

public class ContextMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("**********  ContextMenuActivity  onCreate  ***********");
        setContentView(R.layout.activity_context_menu);
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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        System.out.println("~~~~  " + getClass().getSimpleName() + ".onCreateContextMenu  ~~~~");
        super.onCreateContextMenu(menu, v, menuInfo);



        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);


//        menu.setHeaderTitle("ttt");
//        menu.setHeaderIcon(R.drawable.w2);//不起作用


        //使用了View就无法再使用Title和ICON，不能并存
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.activity_action_provider, null);
        menu.setHeaderView(view);//测试不成功





    }

    @Override
    public void onContextMenuClosed(Menu menu) {
        System.out.println("~~~~  " + getClass().getSimpleName() + ".onContextMenuClosed  ~~~~");
        super.onContextMenuClosed(menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        System.out.println("~~~~  " + getClass().getSimpleName() + ".onContextItemSelected  ~~~~");
//        return super.onContextItemSelected(item);
        return true;
    }



    public void itemOne(MenuItem item) {
        System.out.println(" click itemone ");
    }

    public void start(View view) {
        System.out.println("~~button.start~~");

        registerForContextMenu(findViewById(R.id.textView));//注册

    }

    public void stop(View view) {
        System.out.println("~~button.stop~~");
    }

    public void add(View view) {
        System.out.println("~~button.add~~");
        openContextMenu(findViewById(R.id.textView));//先注册，才能使用本方法启动上下文菜单
    }

    public void del(View view) {
        System.out.println("~~button.del~~");
        closeContextMenu();

    }
}

