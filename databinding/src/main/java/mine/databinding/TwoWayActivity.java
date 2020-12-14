package mine.databinding;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import java.sql.Time;
import java.time.Instant;

import mine.databinding.data.CatObservable;
import mine.databinding.data.CattleObservable;
import mine.databinding.data.TheViewModel;
import mine.databinding.data.User;
import mine.databinding.data.UserObservable;
import mine.databinding.databinding.ActivityLifecycleBinding;
import mine.databinding.databinding.ActivityTwoWayBinding;


/**
 * Created by Administrator on 2020/11/2.
 */
public class TwoWayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        ActivityTwoWayBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_two_way);
//        binding.setCattle(new CattleObservable("ox"));
        binding.setUser(new UserObservable("ox"));

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
//        ActivityTwoWayBinding binding = DataBindingUtil.getBinding(findViewById(R.id.cl));
//        binding.getCattle().setName("Max");
    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");
//        ActivityTwoWayBinding binding = DataBindingUtil.getBinding(findViewById(R.id.cl));
//        binding.textView.setText("Lobby");
//
//        System.out.println("Cattle.name is " + binding.getCattle().getName());
    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");
        ActivityTwoWayBinding binding = DataBindingUtil.getBinding(findViewById(R.id.cl));
        binding.getUser().setName("xox");
        System.out.println("time is " + binding.timeTextView.time);
    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");
        ActivityTwoWayBinding binding = DataBindingUtil.getBinding(findViewById(R.id.cl));
        System.out.println("time is " + binding.timeTextView.time);

        binding.timeTextView.setTime("tot");

        System.out.println("name is " + binding.getUser().getName());

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