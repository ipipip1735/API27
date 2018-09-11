package mine.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Administrator on 2017/4/14.
 */

public class ToolbarActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println("*******  ToolbarActivity  onCreate  *********");
        setContentView(R.layout.activity_toolbar);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);

//        getSupportActionBar().setTitle("Title");
//        getSupportActionBar().setSubtitle("subTitle");
//        getSupportActionBar().setLogo(R.drawable.w1);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        System.out.println("*******  ToolbarActivity  onCreateMenu!  *********");

        getMenuInflater().inflate(R.menu.menu_opts, menu);
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);


        MenuItem actionMenuItem = menu.findItem(R.id.action_search);

        MenuItemCompat.OnActionExpandListener expandListener = new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                System.out.println("----- onMenuItemActionExpand ----");
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                System.out.println("----- onMenuItemActionCollapse ----");
                return true;
            }
        };

        MenuItemCompat.setOnActionExpandListener(actionMenuItem, expandListener);

        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        System.out.println("*******  ToolbarActivity  onPrepareOptionsMenu  *********");
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("*******  ToolbarActivity  onOptionsItemSelected  *********");
        return super.onOptionsItemSelected(item);
//        return true;
    }

    @Override
    public void openOptionsMenu() {
        System.out.println("*******  ToolbarActivity  openOptionsMenu  *********");
        super.openOptionsMenu();
    }

    @Override
    public void closeOptionsMenu() {
        System.out.println("*******  ToolbarActivity  closeOptionsMenu  *********");
        super.closeOptionsMenu();
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
        System.out.println("*******  ToolbarActivity  onOptionsMenuClosed  *********");
    }

    public void initLoad(View view) {
        System.out.println(" ------- initLoad -------");
        openOptionsMenu();
    }

    public void destroyLoad(View view) {
        System.out.println(" ------- destroyLoad -------");
        closeOptionsMenu();
    }

}

