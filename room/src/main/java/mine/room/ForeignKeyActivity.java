package mine.room;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2018/9/6.
 */
public class ForeignKeyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        setContentView(R.layout.activity_main);
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


    public void insert(View view) {
        System.out.println("~~button.insert~~");
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "userDB")
                .allowMainThreadQueries()
                .build();

        CarUser carUser = new CarUser("Bob");
        db.customDao().insert1(carUser);

    }

    public void update(View view) {
        System.out.println("~~button.update~~");
    }

    public void delete(View view) {
        System.out.println("~~button.delete~~");
    }

    public void load(View view) {
        System.out.println("~~button.load~~");
    }

    public void query(View view) {
        System.out.println("~~button.query~~");
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "userDB")
                .allowMainThreadQueries()
                .build();

        Cursor cursor = db.customDao().getAll();
        System.out.println("size is " + cursor.getCount());
        for (String feild : cursor.getColumnNames()) {
            System.out.print(feild+ ", ");
        }
        System.out.println("");

    }

}
