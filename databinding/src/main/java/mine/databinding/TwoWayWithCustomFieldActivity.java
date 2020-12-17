package mine.databinding;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import java.sql.Time;
import java.time.Instant;

import mine.databinding.data.UserObservable;
import mine.databinding.databinding.ActivityTwoWayWithCustomFieldBinding;

/**
 * Created by Administrator on 2020/12/15.
 */
public class TwoWayWithCustomFieldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        ActivityTwoWayWithCustomFieldBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_two_way_with_custom_field);
        binding.setUser(new UserObservable("Bob"));

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

        ActivityTwoWayWithCustomFieldBinding binding = DataBindingUtil.getBinding(findViewById(R.id.cl));
        binding.getUser().setAge(new Time(Instant.now().toEpochMilli()));

        System.out.println("age = " + binding.getUser().getAge());
        System.out.println("time = " + binding.timeTextView.time);
    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");
        ActivityTwoWayWithCustomFieldBinding binding = DataBindingUtil.getBinding(findViewById(R.id.cl));
        binding.timeTextView.setTime(new Time(Instant.now().toEpochMilli()));

        System.out.println("age = " + binding.getUser().getAge());
        System.out.println("time = " + binding.timeTextView.time);
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

        ActivityTwoWayWithCustomFieldBinding binding = DataBindingUtil.getBinding(findViewById(R.id.cl));
        System.out.println("age = " + binding.getUser().getAge());
        System.out.println("time = " + binding.timeTextView.time);

    }
}