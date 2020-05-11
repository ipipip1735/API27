package mine.room;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Created by Administrator on 2018/9/6.
 */
public class RelationActivity extends AppCompatActivity {
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
        insertRelation();

    }

    private void insertRelation() {

        if (Objects.isNull(db))
            db = Room.databaseBuilder(this,
                    AppDatabase.class, "userDB").build();

        new Thread() {
            @Override
            public void run() {
                long id;

                Company company = new Company();
                company.setCompany_name("Company" + new Random().nextInt(100));
                id = db.companyDao().insert1(company);
                System.out.println("company'id is " + id);

                for (int i = new Random().nextInt(5); i > 0; i--) {
                    Employee employee = new Employee();
                    employee.setCname(company.getCompany_name());
                    id = db.employeeDao().insert1(employee);
                    System.out.println("employee'id is " + id);
                }
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

    public void liveData(View view) {
        System.out.println("~~button.liveData~~");
    }

    public void query(View view) {
        System.out.println("~~button.query~~");
//        separateQuery();
        relationQuery();
    }

    private void separateQuery() {
        if (Objects.isNull(db))
            db = Room.databaseBuilder(this,
                    AppDatabase.class, "userDB").build();

        new Thread() {
            @Override
            public void run() {
                List<Company> companies = db.companyDao().getAll();
                System.out.println("companies'size is " + companies.size());
                System.out.println(companies);

                List<Employee> employees = db.employeeDao().getAll();
                System.out.println("employee'size is " + employees.size());
                System.out.println(employees);
            }
        }.start();
    }


    private void relationQuery() {
        if (Objects.isNull(db))
            db = Room.databaseBuilder(this,
                    AppDatabase.class, "userDB").build();

        new Thread() {
            @Override
            public void run() {
                List<CompanyEmployee> companyEmployees = db.companyDao().getCompanyEmployee();
                System.out.println(companyEmployees.size());
                for (CompanyEmployee companyEmployee : companyEmployees) {
                    System.out.println(companyEmployee);
                }
            }
        }.start();

    }

}
