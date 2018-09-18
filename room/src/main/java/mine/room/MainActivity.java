package mine.room;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.TypeConverter;
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
public class MainActivity extends AppCompatActivity {
    AppDatabase db;
    LiveData<List<User>> userLiveData;

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
        System.out.println("~~button.start~~");
        insertInUI();
//        insertInWork();

    }



    private void insertInWork() {

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "userDB").build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = new User("chris" + new Random().nextInt(100),
                        "lee",
                        new Random().nextInt(100),
                        1);
                long id = db.userDao().insert1(user);
                System.out.println("age is " + new Random().nextInt(100));
                System.out.println("id is " + id);
            }
        }).start();
    }


    private void insertInUI() {

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "userDB").allowMainThreadQueries().build();

        User user = new User("chris" + new Random().nextInt(100),
                "lee",
                new Random().nextInt(100),
                1);

        long id = db.userDao().insert1(user);
        System.out.println("id is " + id);
    }


    public void update(View view) {
        System.out.println("~~button.update~~");

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "userDB").allowMainThreadQueries().build();

        Random random = new Random();
        User user = new User("chris",
                "lee",
                random.nextInt(100),
                random.nextInt(10000));

        user.setUid(1);
        int count = db.userDao().update(user);
        System.out.println("update count is " + count);
    }

    public void delete(View view) {
        System.out.println("~~button.delete~~");
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "userDB").allowMainThreadQueries().build();

        Random random = new Random();
        User user = new User("chris",
                "lee",
                random.nextInt(100),
                random.nextInt(10000));
        user.setUid(1);

        int count = db.userDao().delete(user);
        System.out.println("count is " + count);
    }

    public void load(View view) {
        System.out.println("~~button.load~~");
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "userDB").allowMainThreadQueries().build();

        Cursor cursor = db.userDao().loadUser();
        while (cursor.moveToNext()) {
            for (String name : cursor.getColumnNames()) {
                System.out.println(name + " is " + cursor.getString(cursor.getColumnIndex(name)));
            }
        }

    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void liveData(View view) {
        System.out.println("~~button.liveData~~");

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "userDB").allowMainThreadQueries().build();

        userLiveData = db.userDao().queryUserLiveData();
        System.out.println(userLiveData);
        userLiveData.observe(this, (List<User> data) -> {
            System.out.println("~~observer~~");
            for (User user : data) {
                System.out.println(user);
            }
        });


    }


    public void query(View view) {
        System.out.println("~~button.query~~");
//        queryInUI();
        queryInWork();

    }

    private void queryInUI() {

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "userDB").allowMainThreadQueries().build();
        List<User> users = db.userDao().getAll();
        System.out.println("size is " + users.size());
    }

    private void queryInWork() {

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "userDB").build();

        AsyncTask<User, String, List<User>> asyncTask = new AsyncTask<User, String, List<User>>() {
            @Override
            protected List<User> doInBackground(User... users) {
                User user = users[0];
                return db.userDao().queryUser(user.getFirstName(), user.getLastName());
            }

            @Override
            protected void onPostExecute(List<User> users) {
                super.onPostExecute(users);
                System.out.println("count is " + users.size());
                for (User user : users) {
                    System.out.print("uid is " + user.getUid());
                    System.out.print(", firstName is " + user.getFirstName());
                    System.out.print(", lastName is " + user.getLastName());
                    System.out.print(", age is " + user.getAge());
                    System.out.print("\n");
                }
            }
        };

        asyncTask.execute(new User("chris", "lee", 12, 1));

    }


}
