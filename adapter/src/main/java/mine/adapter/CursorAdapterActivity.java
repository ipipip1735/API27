package mine.adapter;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Administrator on 2019/2/19.
 */
public class CursorAdapterActivity extends AppCompatActivity {
    ListView listView;
    CursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("**********  Main.onStart  ***********");
        setContentView(R.layout.activity_list_view);

        listView = findViewById(R.id.myLV);

    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("**********  Main.onStart  ***********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("**********  Main.onRestoreInstanceState  ***********");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("**********  Main.onRestart  ***********");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("**********  Main.onResume  ***********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("**********  Main.onPause  ***********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("**********  Main.onBackPressed  ***********");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("**********  Main.onStop  ***********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("**********  Main.onSaveInstanceState  ***********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("**********  Main.onDestroy  ***********");
    }


    public void add(View view) {
        System.out.println("~~button.add~~");

        String table = "person";
        ContentValues contentValues = new ContentValues();
        contentValues.put("person_name", "jack" + new Random().nextInt(256));
        contentValues.put("person_gender", new Random().nextInt(2));
        contentValues.put("person_age", new Random().nextInt(120));

        ContentResolver contentResolver = getContentResolver();
        Uri uri = contentResolver.insert(Uri.parse("content://TNT/person"), contentValues);
        contentResolver.notifyChange(uri, null);


    }

    public void del(View view) {
        System.out.println("~~button.del~~");


    }

    public void query(View view) {
        System.out.println("~~button.query~~");


        Uri uri = Uri.parse("content://TNT/person");

        String[] projection = {
                "_id",
                "name",
                "age",
                "gender",
        };

        String selection = "_id > ?";
        String[] selectionArgs = {"0"};
        String sortOrder = "_id DESC";

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(uri, projection, selection, selectionArgs, sortOrder);
        cursor.setNotificationUri(contentResolver, uri);
        System.out.println("Position is " + cursor.getPosition());
        System.out.println("count is " + cursor.getCount());


        cursorAdapter = new CursorAdapter(this, cursor, true) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                System.out.println("..CursorAdapter.newView..");
                System.out.println("context is " + context);
                System.out.println("cursor is " + cursor);
                System.out.println("parent is " + parent);
                return new TextView(context);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                System.out.println("..CursorAdapter.bindView..");
                System.out.println("view is " + view);
                System.out.println("context is " + context);
                System.out.println("cursor is " + cursor);

                ((TextView) view).setText(cursor.getString(cursor.getColumnIndex("name")));

            }
        };

        listView.setAdapter(cursorAdapter);

    }


    public void start(View view) {
        System.out.println("~~button.start~~");


    }

    public void stop(View view) {
        System.out.println("~~button.stop~~");

    }
}