package mine.room;


import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SimpleSQLiteQuery;
import android.arch.persistence.room.Room;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Created by Administrator on 2018/9/6.
 */
public class RawQueryActivity extends AppCompatActivity {
    AppDatabase db;
    LiveData<List<User>> usersLiveData;

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
//        insertInUI();
        rawInsert();

    }

    private void rawInsert() {

        if (Objects.isNull(db))
            db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "userDB").build();

        String s = "INSERT INTO User(first_name, last_name, age, car_id)" +
                " VALUES(chris" + new Random().nextInt(100) + ", " +
                "lee, " +
                new Random().nextInt(100) + ", " +
                1 + ")";
        System.out.println(s);
        SimpleSQLiteQuery sql = new SimpleSQLiteQuery(s);

        new Thread() {
            @Override
            public void run() {
                db.userDao().liveDateRawInsert(sql);
//                System.out.println("id is " + id);
            }
        }.start();

    }


    private void insertInUI() {

        if (Objects.isNull(db))
            db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "userDB").build();

        new Thread() {
            @Override
            public void run() {
                User user = new User("chris" + new Random().nextInt(100),
                        "lee",
                        new Random().nextInt(100),
                        1);

                long id = db.userDao().insert1(user);
                System.out.println("id is " + id);
            }
        }.start();
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

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void liveData(View view) {
        System.out.println("~~button.liveData~~");
    }


    public void query(View view) {
        System.out.println("~~button.query~~");
        rawQueryInWork();
//        liveDataRawQueryInWork();

    }

    private void liveDataRawQueryInWork() {

        if (Objects.isNull(db))
            db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "userDB").build();

        String sql = "SELECT * FROM User";
        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sql);


//        System.out.println(query);
//        System.out.println("ArgCount is " + query.getArgCount());
//        System.out.println("sql is " + query.getSql());

        if (Objects.isNull(usersLiveData)) {
            usersLiveData = db.userDao().liveDateRawQuery(query);
            usersLiveData.observe(this, data -> {
                System.out.println("~~observer~~");
                System.out.println(data);
            });
        }

    }

    private void rawQueryInWork() {

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "userDB").build();
        String sql = "SELECT * FROM User WHERE uid = ? LIMIT 1";
        Object[] objects = {1};
        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sql, objects);


        System.out.println(query);
        System.out.println("ArgCount is " + query.getArgCount());
        System.out.println("sql is " + query.getSql());


        new Thread() {
            @Override
            public void run() {
                User user = db.userDao().rawQuery(query);
                System.out.println(user);
            }
        }.start();


    }


}
