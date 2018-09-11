package mine.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public PersonContentProvide pcp = new PersonContentProvide(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    @Override
    protected void onStart() {
        super.onStart();

        String sortOrder = "_id";
        String[] projection = new String[]{
                "_id",
                "person_name",
                "person_age"
        };
        Uri uri = Uri.parse("content://A/B");



        Cursor cursor = pcp.query(uri, projection, null, null, sortOrder);
        CursorAdapter cursorAdapter = new CursorAdapter(this, cursor) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                System.out.println("=---=---=  new View =---=---=");

                TextView textView=new TextView(context);
                return  textView;
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                System.out.println("=---=---=   bind View =---=---=");
                TextView textView=(TextView)view;
                textView.setText(cursor.getString(cursor.getColumnIndex("_id")));


            }
        };

        ListView listView = (ListView) findViewById(R.id.listview1);
        listView.setAdapter(cursorAdapter);


    }




    public void insert(View view) {
        Uri uri = Uri.parse("content://A/B/C");

        ContentValues contentValues = new ContentValues(2);
        contentValues.put("person_name", "bob");
        contentValues.put("person_age", new Random().nextInt(100));
        pcp.insert(uri, contentValues);

    }

    public void batch(View view) {
        System.out.println("batch");
        manulQuery();
    }


    private void manulQuery() {

        PersonContentProvide pcp = new PersonContentProvide(this);
        String[] projection = new String[]{
                "_id",
                "person_name",
                "person_age"
        };
        String selection = "person_name = ? and person_age = ?";
        String[] selectionArgs = new String[]{
                "jack",
                "11"
        };
        String sortOrder = "_id";

        Uri uri = Uri.parse("content://mine.sqlitedb/Person/*");

//        Cursor cursor = pcp.query(uri, projection, selection, selectionArgs, sortOrder);
        Cursor cursor = pcp.query(uri, projection, null, null, sortOrder);
        System.out.println(cursor);


        System.out.println(cursor.getCount());

//        cursor.setNotificationUri(getContentResolver(), uri);


////       while (cursor.moveToNext())
//        System.out.println(cursor.getInt(1));
    }

    public void getData(View view) {



    }
}
