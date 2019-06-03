package mine.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

/**
 * Created by Administrator on 2018/9/14.
 */
public class LiveDataActivity extends AppCompatActivity {

    MutableLiveData<Car> carLiveData;
    LiveData<User> userLiveData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_client);

        if (carLiveData == null) {
            System.out.println("--carLiveData initial--");
            carLiveData = CarLiveData.get();
            carLiveData.observe(this, car -> {
                System.out.print("observer car|");
                System.out.println(car);
            });
        }


        //方法一
        userLiveData = Transformations.map(carLiveData, car->{
            System.out.println("~~Transformations~~");
            System.out.println(car);
            return new User("bob", "lee");
        });
        userLiveData.observe(this, user -> {
            System.out.print("observer user|");
            System.out.println(user);
        });




        //方法二
//        userLiveData = Transformations.switchMap(carLiveData, car->{
//            System.out.println();
//            MutableLiveData<User> temp = new MutableLiveData<User>();
//            temp.setValue(new User("tom", "lee"));
//            LiveData<User> userLiveData = temp;
//            return userLiveData;
//        });
//        userLiveData.observe(this, user -> {
//            System.out.print("observer user|");
//            System.out.println(user);
//        });

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


    public void start(View view) {
        System.out.println("~~button.start~~");
        carLiveData.postValue(new Car("VOLVO"));
//        System.out.println(userLiveData);
    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");
        System.out.println("carLiveData is " + carLiveData.getValue());
    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");

    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void del(View view) {
        System.out.println("~~button.del~~");

    }


    public void query(View view) {
        System.out.println("~~button.query~~");

    }

}
