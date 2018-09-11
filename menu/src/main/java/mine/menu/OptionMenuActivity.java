package mine.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Administrator on 2017/4/14.
 */

public class OptionMenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("**********  FloatMenuActivity  onCreate  ***********");

        setContentView(R.layout.activity_task);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        System.out.println("*******Menu*********");
        getMenuInflater().inflate(R.menu.menu_opts, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        System.out.println("******Prepare*Menu********");

        System.out.println(menu.size());
//        System.out.println(menu.getItem(0).setTitle("DD"));
//        System.out.println(menu.findItem(R.id.itemOne).setTitle("One"));


        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        System.out.println("*******   onOptionsMenuClosed  *********");
        super.onOptionsMenuClosed(menu);
    }

    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        System.out.println("*******   onPanelClosed  *********");
        featureId = 0;
        super.onPanelClosed(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("*******   onOptionsItemSelected  *********");
//        return super.onOptionsItemSelected(item);
        return true;
    }





    public void itemOne(MenuItem item) {
        System.out.println(" click itemone ");
    }

}

