package mine.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/4/14.
 */

public class ActionModeActivity extends AppCompatActivity {

    ActionMode mActionMode = null;
    private ActionMode.Callback mActionCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            System.out.println("*******  onCreateActionMode  *********");
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_opts, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            System.out.println("*******  onPrepareActionMode  *********");
            mode.setTitle("title");
            mode.setSubtitle("subTitle");

//        ImageView imageView = new ImageView(this);
//        imageView.setImageResource(R.drawable.w1);
//        mode.setCustomView(imageView);
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            System.out.println("*******  onActionItemClicked  *********");
            System.out.println("item is " + item.getItemId());

            switch (item.getItemId()) {
                case R.id.itemOne:
                    System.out.println("one is " +R.id.itemOne);
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            System.out.println("*******  onDestroyActionMode  *********");
        }
    };



    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("**********  ContextMenuActivity  onCreate  ***********");

        setContentView(R.layout.activity_task);

//        registerForContextMenu(findViewById(R.id.textView));


    }


    @Override
    protected void onStop() {
        System.out.println("**********  ContextMenuActivity  onCreate  ***********");
        super.onStop();
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        System.out.println("*******Menu*********");
//        getMenuInflater().inflate(R.menu.menu_opts, menu);
//
//
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        System.out.println("******Prepare*Menu********");
//
//        System.out.println(menu.size());
////        System.out.println(menu.getItem(0).setTitle("DD"));
////        System.out.println(menu.findItem(R.id.itemOne).setTitle("One"));
//
//
//        return super.onPrepareOptionsMenu(menu);
//    }

//    @Override
//    public void onOptionsMenuClosed(Menu menu) {
//        super.onOptionsMenuClosed(menu);
//    }


    public void initLoad(View view) {
        System.out.println("~~button.initLoad~~");

        startActionMode(mActionCallback);
//        view.setSelected(true);


    }


    public void destroyLoad(View view) {
        System.out.println("~~button.destroyLoad~~");
        ListView listView = (ListView) findViewById(R.id.lv);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"aa", "bb"}));
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                System.out.println("*******  onItemCheckedStateChanged  *********");

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                System.out.println("*******  onCreateActionMode  *********");
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu_actionmove, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                System.out.println("*******  onPrepareActionMode  *********");
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                System.out.println("*******  onActionItemClicked  *********");
                System.out.println("item is " + item.getItemId());

                switch (item.getItemId()) {
                    case R.id.itemOne:
                        System.out.println("one is " +R.id.itemOne);
                        mode.finish(); // Action picked, so close the CAB
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                System.out.println("*******  onDestroyActionMode  *********");

            }
        });
    }

}

