package mine.adapter;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private BaseCursorAdapter baseCursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("**********  Main.onStart  ***********");
        setContentView(R.layout.activity_list_view);
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
        System.out.println("~~add~~");

        addWithCR();

    }

    public void notify(View view) {
        System.out.println("~~notify~~");

        getContentResolver().notifyChange(Uri.parse("content://TNT/person"), null);



    }

    public void query(View view) {
        System.out.println("~~query~~");

        queryWithCR();


    }


    public void start(View view) {
        System.out.println("~~start~~");




        ViewGroup viewGroup = findViewById(android.R.id.content);


//        viewGroup.add();



    }

    public void stop(View view) {
        System.out.println("~~stop~~");

    }


    private void addWithCR() {

        String table = "person";
        ContentValues contentValues = new ContentValues();
        contentValues.put("person_name", "jack" + new Random().nextInt(256));
        contentValues.put("person_gender", new Random().nextInt(2));
        contentValues.put("person_age", new Random().nextInt(120));

        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("content://TNT/person");
        uri = contentResolver.insert(uri, contentValues);
        contentResolver.notifyChange(uri, null);


    }


    private void queryWithCR() {

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



        System.out.println("Position is " + cursor.getPosition());
        System.out.println("count is " + cursor.getCount());



//        while (cursor.moveToNext()) {
//            for (String name : cursor.getColumnNames()) {
//                System.out.println(name + " is " + cursor.getString(cursor.getColumnIndex(name)));
//            }
//            System.out.println("--------");
//        }

        cursor.setNotificationUri(contentResolver, uri);
        baseCursorAdapter = new BaseCursorAdapter(this, cursor, true);

        ListView listView = findViewById(R.id.myLV);
        listView.setAdapter(baseCursorAdapter);




    }


}
