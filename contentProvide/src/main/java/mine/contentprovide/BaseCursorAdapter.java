package mine.contentprovide;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.Random;

public class BaseCursorAdapter extends CursorAdapter {

    public BaseCursorAdapter(Context context, Cursor c, boolean flags) {
        super(context, c, flags);
        System.out.println("--- " + getClass().getSimpleName() + ".constructor ---");

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        System.out.println("--- " + getClass().getSimpleName() + ".newView ---");
//        System.out.println(Thread.currentThread());

        //方式一：手动创建View
        TextView textView = new TextView(context);
        return textView;


        //方法二：使用布局文件
//        return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        System.out.println("--- " + getClass().getSimpleName() + ".bindView ---");
//        System.out.println(Thread.currentThread());

        TextView textView = (TextView) view;

        StringBuilder item = new StringBuilder();
        item.append("id is ").append(cursor.getString(cursor.getColumnIndex("_id"))).append(", ");
        item.append("name is ").append(cursor.getString(cursor.getColumnIndex("name")));
        item.append("age is ").append(cursor.getString(cursor.getColumnIndex("age")));

        textView.setText(item.toString());

    }
}
