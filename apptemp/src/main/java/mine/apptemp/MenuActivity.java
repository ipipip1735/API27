package mine.apptemp;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Administrator on 2018/8/25.
 */
public class MenuActivity extends AppCompatActivity {

    String[] nameArray = {"aa", "bb"};
    ActionMode mActionMode = null;
    private ActionMode.Callback mActionCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            System.out.println("~~ActionMode.onCreateActionMode~~");
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_action, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            System.out.println("~~ActionMode.onPrepareActionMode~~");
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            System.out.println("~~ActionMode.onActionItemClicked~~");
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
            System.out.println("~~ActionMode.onDestroyActionMode~~");

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");

//        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_client);


        ListView listView = (ListView) findViewById(R.id.lv);
        ArrayAdapter<String> nameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.nameArray);
        listView.setAdapter(nameAdapter);

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

//    ----------------------------------

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateContextMenu  *********");
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_choices, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onContextItemSelected  *********");

        return super.onContextItemSelected(item);
    }

    @Override
    public void onContextMenuClosed(Menu menu) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onContextMenuClosed  *********");

        super.onContextMenuClosed(menu);
    }


//    ----------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateOptionsMenu  *********");
        getMenuInflater().inflate(R.menu.menu_choices, menu);
//        return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onPrepareOptionsMenu  *********");

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onOptionsItemSelected  *********");
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onOptionsMenuClosed  *********");

        super.onOptionsMenuClosed(menu);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onSaveInstanceState  *********");
    }

    @Override
    public void onContentChanged() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onContentChanged  *********");

        super.onContentChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");
    }


    public void start(View view) {
        System.out.println("~~button.start~~");
        registerForContextMenu(findViewById(R.id.lv));

    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");
//        View fl = findViewById(R.id.fl);
//        System.out.println(fl);
//
//        fl.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                System.out.println("....onLongClick....");
//                if (mActionMode != null) {
//                    return false;
//                }
//
//                mActionMode = startActionMode(mActionCallback);
//                fl.setSelected(true);
//                return true;
//            }
//        });
//
//        fl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("....onClick....");
//
//            }
//        });

        if (mActionMode == null) {
            mActionMode = startActionMode(mActionCallback);
        }

//        fl.setSelected(true);

    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");
        Window window = getWindow();
        TextView textView = new TextView(this);
        textView.setText("oooooooooooooo");

//        ViewGroup.LayoutParams ll = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        window.addContentView(textView, window.getAttributes());

    }



    public void unbind(View view) {
        System.out.println("~~button.unbind~~");
//        getWindow().closeAllPanels();
//        getWindow().setStatusBarColor(getResources().getColor(R.color.BlueViolet, null));
//        getWindow().setNavigationBarColor(getResources().getColor(R.color.MediumTurquoise, null));

        getSupportActionBar().hide();
//        getWindow().invalidatePanelMenu(Window.FEATURE_ACTION_BAR_OVERLAY);
    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void del(View view) {
        System.out.println("~~button.del~~");

    }


    public void query(View view) {
        System.out.println("~~button.query~~");

    }

}
