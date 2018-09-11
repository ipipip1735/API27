package mine.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("... " + this.getClass().getSimpleName() + ".onCreate ...");

        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_customView);
        setContentView(R.layout.activity_menu);
        registerForContextMenu(findViewById(R.id.textView));
    }

    @Override
    protected void onStop() {
        System.out.println("... " + this.getClass().getSimpleName() + ".onStop ...");

        super.onStop();
        unregisterForContextMenu(findViewById(R.id.textView));
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        System.out.println("... " + this.getClass().getSimpleName() + ".onCreateContextMenu ...");

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        System.out.println("... " + this.getClass().getSimpleName() + ".onPrepareOptionsMenu ...");


        System.out.println(menu.size());
//        System.out.println(menu.getItem(0).setTitle("DD"));
//        System.out.println(menu.findItem(R.id.itemOne).setTitle("One"));


        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onContextMenuClosed(Menu menu) {
        System.out.println("*******  ContextMenuActivity  onContextMenuClosed  *********");
        super.onContextMenuClosed(menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        System.out.println("*******  ContextMenuActivity  onContextItemSelected  *********");
        return super.onContextItemSelected(item);
//        return true;
    }



    public void start(View view) {
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
