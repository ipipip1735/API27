package mine.databinding;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import mine.databinding.data.Car;
import mine.databinding.data.User;
import mine.databinding.databinding.ActivityMainBinding;

/**
 * Created by Administrator on 2020/10/25.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        //方式一
//        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main); //渲染布局，并返回数据绑定对象
//        System.out.println("binding = " + binding);
//        binding.setUser(new User("Bob")); //设置数据对象


        //方法二
////        ActivityMainBinding binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_main, null, false); //通过膨胀器获取数据绑定对象
//        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater()); //通过膨胀器获取数据绑定对象
//        System.out.println("binding = " + binding);
//        binding.setVariable(BR.user, new User("Tom"));
//        System.out.println("binding.tv = " + binding.tv); //获取View，布局文件中带ID的都是
//        System.out.println("binding.getRoot() = " + binding.getRoot());
//        setContentView(binding.getRoot()); //指定页面根View

        //方法三
        setContentView(R.layout.activity_main);
//        ActivityMainBinding binding = DataBindingUtil.bind(findViewById(R.id.cl));
//        binding.setVariable(BR.user, new User("Tom"));

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

        //方式一
//        DataBindingUtil.bind(findViewById(R.id.cl));
//        ViewDataBinding binding = DataBindingUtil.getBinding(findViewById(R.id.cl));
//        binding.setVariable(BR.auto, new Car(135));

        //方式二
        DataBindingUtil.bind(findViewById(R.id.cl));
        ActivityMainBinding binding = DataBindingUtil.getBinding(findViewById(R.id.cl));
        binding.setAuto(new Car(522));

    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");
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