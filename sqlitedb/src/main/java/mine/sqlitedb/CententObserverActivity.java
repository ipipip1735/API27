package mine.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.AbstractCursor;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class CententObserverActivity extends AppCompatActivity {

//    public PersonContentProvide pcp = new PersonContentProvide(this);
    public ContentObserver contentObserver = new MyContentObserver(null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursor);
    }

    public void cursorModify(View view) {

//
//        ContentValues contentValues = new ContentValues(2);
//        contentValues.put("person_name", "bob");
//        contentValues.put("person_age", new Random().nextInt(100));
//        pcp.insert(uri, contentValues);

        Uri uri = Uri.parse("content://A/B/C/D");
        getContentResolver().notifyChange(uri, null);

    }



    public void cursorQuery(View view) {
//        String sortOrder = "_id";
//        String[] projection = new String[]{
//                "_id",
//                "person_name",
//                "person_age"
//        };
//        Uri uri = Uri.parse("content://A/B");
//        Cursor cursor = pcp.query(uri, projection, null, null, sortOrder);

        Uri uri = Uri.parse("content://A/B/C");
        getContentResolver().registerContentObserver(uri, false, this.contentObserver);

    }


}