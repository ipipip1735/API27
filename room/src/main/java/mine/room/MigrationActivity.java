package mine.room;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Created by Administrator on 2018/9/6.
 */
public class MigrationActivity extends AppCompatActivity {

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
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "userDB").allowMainThreadQueries().build();

        long id = db.employeeDao().insert1(new Employee());
        System.out.println("id is " + id);

    }

    public void update(View view) {
        System.out.println("~~button.update~~");
        Migration MIGRATION_1_2 = new Migration(1, 2) {
            @Override
            public void migrate(SupportSQLiteDatabase database) {
                System.out.println("database is " + database.getVersion());
            }
        };

        Migration MIGRATION_2_3 = new Migration(2, 3) {
            @Override
            public void migrate(SupportSQLiteDatabase database) {
                System.out.println("database is " + database.getVersion());
            }
        };
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
                AppDatabase.class, "userDB").allowMainThreadQueries().build();

        List<Employee> employees = db.employeeDao().getAll();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

}
