package mine.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
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

        setContentView(R.layout.activity_task);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        System.out.println("*******  ActionBarActivity  onCreateOptionsMenu  *********");
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        System.out.println("*******  ActionBarActivity  onPrepareOptionsMenu  *********");
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("*******  ActionBarActivity  onOptionsItemSelected  *********");
//        return super.onOptionsItemSelected(item);
        startActivity(new Intent(this,ToolbarActivity.class));
        return true;
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
        System.out.println("*******  ActionBarActivity  onOptionsMenuClosed  *********");
    }

    public void initLoad(View view) {
        System.out.println("-------- initLoad ---------");
//        openOptionsMenu();
        startActivity(new Intent(this,ToolbarActivity.class));
    }
}

