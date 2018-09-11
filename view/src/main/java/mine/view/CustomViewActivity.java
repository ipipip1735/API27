package mine.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

public class CustomViewActivity extends AppCompatActivity {
    CustomView customView1, customView2;
    float degrees = 0f, x = 0, y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("... " + this.getClass().getSimpleName() + ".onCreate ...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        customView1 = findViewById(R.id.imageView1);
        customView2 = findViewById(R.id.imageView2);

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


    public void rotate(View view) {
        degrees += 10f;
        customView1.setDegree(degrees);
        customView2.setDegree(degrees);
    }

    public void translate(View view) {
//        x += 10f;
//        customView1.setXY(x, y);

//        customView1.requestLayout();
                customView2.invalidate();
    }
}
