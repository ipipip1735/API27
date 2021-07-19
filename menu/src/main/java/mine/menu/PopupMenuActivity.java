package mine.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/4/14.
 */

public class PopupMenuActivity extends AppCompatActivity {



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



    public void start(View view) {
        System.out.println("~~button.start~~");
//        Context wrapper = new ContextThemeWrapper(this, R.style.YOURSTYLE);
//        PopupMenu popupMenu = new PopupMenu(wrapper, findViewById(R.id.textView));
        PopupMenu popupMenu = new PopupMenu(this, findViewById(R.id.textView));
        popupMenu.inflate(R.menu.menu_item);
        popupMenu.show();
//        popupMenu.getMenu().getItem(0).setTitle("");
//        System.out.println("popupMenu.getMenu().getItem(0) = " + popupMenu.getMenu().getItem(0).getClass());

//        popupMenu.getMenu().getItem(0).setTitleCondensed("XXX");


        //菜单item点击监听器
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                System.out.println("---- PopupMenu  onMenuItemClick() ----");
                return false;
            }
        });

        //菜单关闭监听器
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                System.out.println(" ---- PopupMenu  onDismiss() ----");

            }
        });
    }

    public void stop(View view) {
        System.out.println("~~button.stop~~");

        View menu = getLayoutInflater().inflate(R.layout.menu_popup, null);


        //获取子View，绑定监听器
        final TextView itema = (TextView) menu.findViewById(R.id.ItemA);
        final TextView itemb = (TextView) menu.findViewById(R.id.ItemB);



//        menu.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);


        PopupWindow mDropdown = new PopupWindow(menu, FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT,true);
        mDropdown.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.editbox_dropdown_dark_frame, null));
//        mDropdown.setBackgroundDrawable(getResources().getDrawable(R.drawable.vector_one, null));
        mDropdown.showAsDropDown(findViewById(R.id.textView), 50, 5);


    }

    public void add(View view) {
        System.out.println("~~button.add~~");

        MenuBuilder menuBuilder = new MenuBuilder(this);
        getMenuInflater().inflate(R.menu.menu_item, menuBuilder);
        menuBuilder.setCallback(new MenuBuilder.Callback() {
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                System.out.println("~~" + getClass().getSimpleName() + ".onMenuItemSelected~~");
                System.out.println("menu = " + menu + ", item = " + item);
                return false;
            }

            @Override
            public void onMenuModeChange(MenuBuilder menu) {

            }
        });

        MenuPopupHelper menuHelper  = new MenuPopupHelper(this, menuBuilder);
        menuHelper.setAnchorView(findViewById(R.id.textView));
        System.out.println("menuHelper.getListView() = " + menuHelper.getListView());
        menuHelper.show(500, -200);


    }

    public void end(View view) {
        System.out.println("~~button.end~~");

    }


}


