package mine.room;


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
public class MainActivity extends AppCompatActivity {
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        setContentView(R.layout.activity_main);
        insertForeign();
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
//        insertInUI();
        insertInWork();

    }

    private void insertForeign() {
        if (Objects.isNull(db))
            db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "userDB").allowMainThreadQueries().build();

        Car car = new Car("car" + new Random(100).nextInt());
        long id = db.carDao().insert1(car);
        System.out.println("id is " + id);
    }

    private void insertInWork() {
        if (Objects.isNull(db))
            db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "userDB").allowMainThreadQueries().build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = new User("chris" + new Random(100).nextInt(),
                        "lee",
                        new Random(100).nextInt(),
                        1);
                long id = db.userDao().insert1(user);
                System.out.println("age is " + new Random(100).nextInt());
                System.out.println("id is " + id);
            }
        }).start();
    }

    private void insertInUI() {
        if (Objects.isNull(db))
            db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "userDB").allowMainThreadQueries().build();

        User user = new User("chris" + new Random(100).nextInt(), "lee", new Random(100).nextInt(), 1);
        long id = db.userDao().insert1(user);
        System.out.println("id is " + id);
    }


    public void update(View view) {
        System.out.println("~~button.stop~~");
    }

    public void delete(View view) {
        System.out.println("~~button.delete~~");
//        if (Objects.isNull(db))
//            db = Room.databaseBuilder(getApplicationContext(),
//                    AppDatabase.class, "userDB").build();

//        List<UserRelation> listUserRelation = db.userDao().loadRelationUser();
//        System.out.println(listUserRelation.size());



    }

    public void load(View view) {
        System.out.println("~~button.load~~");
        if (Objects.isNull(db))
            db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "userDB").allowMainThreadQueries().build();

//        User user = new User("chris" + new Random(100).nextInt(), "lee", new Random(100).nextInt(), 1);
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
//        if (Objects.isNull(db))
//            db = Room.databaseBuilder(getApplicationContext(),
//                    AppDatabase.class, "userDB").allowMainThreadQueries().build();

//        LiveData<List<User>> listLiveData = db.userDao().loadUserThanId(1);
//        System.out.println(listLiveData);


    }


    public void query(View view) {
        System.out.println("~~button.query~~");
//        queryInUI();
        queryInWork();

    }

    private void queryInUI() {
        if (Objects.isNull(db))
            db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "userDB").allowMainThreadQueries().build();
        List<User> users = db.userDao().getAll();
        System.out.println("size is " + users.size());
    }

    private void queryInWork() {
        if (Objects.isNull(db))
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
                    System.out.println("firstName is "+ user.getFirstName());
                    System.out.println("lastName is "+ user.getLastName());
                    System.out.println("age is "+ user.getAge());
                }
            }
        };

        asyncTask.execute(new User("chris", "lee", 12, 1));

    }


}
