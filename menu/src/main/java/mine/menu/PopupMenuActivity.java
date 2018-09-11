package mine.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/4/14.
 */

public class PopupMenuActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("**********  ContextMenuActivity  onCreate  ***********");

        setContentView(R.layout.activity_task);


    }



    @Override
    protected void onStop() {
        System.out.println("**********  ContextMenuActivity  onStop  ***********");
        super.onStop();

    }


    public void textClick(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.menu_item);
        popupMenu.show();


        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                System.out.println(" ---- PopupMenu  onMenuItemClick() ----");
                return false;
            }
        });

        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                System.out.println(" ---- PopupMenu  onDismiss() ----");

            }
        });

    }
}


