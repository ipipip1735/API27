package mine.room;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Created by Administrator on 2018/9/15.
 */
public class ViewModelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        setContentView(R.layout.activity_main);


        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getUserInfo().observe(this, (List<String> data) -> {
            System.out.println("~~update ViewModel~~");
            System.out.println(data);

            //update UI
            ArrayAdapter<String> arrayAdapter =
                    new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
            ListView listView = findViewById(R.id.lv);
            listView.setAdapter(arrayAdapter);

        });
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
        insertInWork();
    }


    private void insertInWork() {
        UserViewModel userViewModel =
                ViewModelProviders.of(this).get(UserViewModel.class);
        AppDatabase db = userViewModel.getDb();

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


    public void update(View view) {
        System.out.println("~~button.stop~~");
    }

    public void delete(View view) {
        System.out.println("~~button.delete~~");
    }

    public void load(View view) {
        System.out.println("~~button.load~~");
    }


    public void query(View view) {
        System.out.println("~~button.query~~");
//        queryLiveDate();
        queryViewModel();

    }

    private void queryLiveDate() {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "userDB").build();

        //LiveData简单使用
        LiveData<List<User>> users = db.userDao().queryUserLiveData();

        //LiveData是异步返回结果集，只能在观察者中接收查询结果
        users.observe(this, data -> {
            System.out.println(data);
        });
    }

    private void queryViewModel() {
        //获取ViewModel中的DB
        UserViewModel userViewModel =
                ViewModelProviders.of(this).get(UserViewModel.class);
        AppDatabase db = userViewModel.getDb();

        new AsyncTask<User, String, List<User>>() {
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
                    System.out.println(user);
                }
            }
        }.execute(new User("chris", "lee", 12, 1));

    }


}
