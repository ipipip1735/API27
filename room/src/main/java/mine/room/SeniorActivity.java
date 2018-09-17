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
public class SeniorActivity extends AppCompatActivity {
    AppDatabase db;
    LiveData<List<Teacher>> teatcherLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        setContentView(R.layout.activity_main);

//        textView = new TextView(this);
//        textView.setText("go go go");
//        ViewGroup viewGroup = findViewById(R.id.fl);
//        viewGroup.addView(textView);
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
        if (Objects.isNull(db))
            db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "teacherDB").allowMainThreadQueries().build();

        Teacher teacher = new Teacher();
        long id = db.teacherDao().insert1(teacher);
        System.out.println("id is " + id);

    }

    public void update(View view) {
        System.out.println("~~button.update~~");
        Migration MIGRATION_1_2 = new Migration(1, 2) {
            @Override
            public void migrate(SupportSQLiteDatabase database) {
                System.out.println("database is " + database);
            }
        };

        Migration MIGRATION_2_3 = new Migration(2, 3) {
            @Override
            public void migrate(SupportSQLiteDatabase database) {
                System.out.println("database is " + database);
            }
        };

        if (Objects.isNull(db))
            db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "teacherDB")
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3).allowMainThreadQueries().build();

        List<TeacherRelation> teacherRelations = db.teacherDao().getAll();
        System.out.println("size is " + teacherRelations.size());
        for (TeacherRelation teacherRelation : teacherRelations) {
            System.out.println("teacherRelation is " + teacherRelation.getTeachers());
//            for (Teacher teacher : teacherRelation.getTeachers()) {
//
//                System.out.println(teacher.toString());
//            }
        }

    }

    public void delete(View view) {
        System.out.println("~~button.delete~~");
    }

    public void load(View view) {
        System.out.println("~~button.load~~");
    }

    public void query(View view) {
        System.out.println("~~button.query~~");
//        relationQuery();
        liveDateQuery();


    }

    private void liveDateQuery() {

        if (Objects.isNull(db))
            db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "userDB").allowMainThreadQueries().build();
        teatcherLiveData = db.teacherDao().getLiveData();
        teatcherLiveData.observe(this, data->{
            System.out.println("~~teacher.observer~~");
            for (Teacher teacher : data) {
                System.out.println(teacher);
            }
        });



    }

    private void relationQuery() {
        if (Objects.isNull(db))
            db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "userDB").allowMainThreadQueries().build();


//        List<Teacher> teachers = db.teacherDao().getAll();
//        System.out.println(teachers.size());

//        TeacherRelation teacherRelation = db.teacherDao().get1();
//        System.out.println(teacherRelation);

        List<TeacherRelation> teacherRelations = db.teacherDao().getAll();
        System.out.println("size is " + teacherRelations.size());
        for (TeacherRelation teacherRelation : teacherRelations) {
            System.out.println("teacherRelation is " + teacherRelation.getTeachers());
//            for (Teacher teacher : teacherRelation.getTeachers()) {
//
//                System.out.println(teacher.toString());
//            }
        }
    }


}
