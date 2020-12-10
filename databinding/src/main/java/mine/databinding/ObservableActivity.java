package mine.databinding;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableMap;
import androidx.databinding.ViewDataBinding;

import mine.databinding.data.Car;
import mine.databinding.data.CatObservable;
import mine.databinding.data.Listener;
import mine.databinding.data.Person;
import mine.databinding.data.User;
import mine.databinding.databinding.ActivityMainBinding;
import mine.databinding.databinding.ActivityObservableBinding;


/**
 * Created by Administrator on 2020/11/7.
 */
public class ObservableActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");


        ActivityObservableBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_observable);
        binding.setCat(new CatObservable("Tom"));

        binding.setMap(new ObservableArrayMap<>());
        binding.getMap().put("one", 111);
        binding.getMap().put("two", "22222");
        binding.getMap().addOnMapChangedCallback(new ObservableMap.OnMapChangedCallback<ObservableMap<String, Object>, String, Object>() {
            @Override
            public void onMapChanged(ObservableMap<String, Object> sender, String key) {
                System.out.println("~~OnMapChangedCallback.onMapChanged~~");
                System.out.println("sender is " + sender);
                System.out.println("key is " + key);
            }
        });

        binding.setPerson(new Person("Mack"));

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

        ActivityObservableBinding binding = DataBindingUtil.getBinding(findViewById(R.id.cl));
        binding.getCat().setName("Kate");
        binding.getCat().setAge(12);
    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

        ActivityObservableBinding binding = DataBindingUtil.getBinding(findViewById(R.id.cl));
        binding.getMap().put("one", 121);
    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

        ActivityObservableBinding binding = DataBindingUtil.getBinding(findViewById(R.id.cl));
        binding.getPerson().name.set("Jack");
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