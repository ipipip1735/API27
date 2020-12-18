package mine.databinding;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import mine.databinding.data.Dog;
import mine.databinding.databinding.ActivityAdaptorBinding;
import mine.databinding.databinding.ActivityMainBinding;


/**
 * Created by Administrator on 2020/11/1.
 */
public class AdapterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        ActivityAdaptorBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_adaptor);
        binding.setDog(new Dog("Odie"));
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

        //方式一：每次使用数据对象新实例
//        ActivityAdaptorBinding binding = DataBindingUtil.findBinding(findViewById(R.id.cl));
//        binding.setDog(new Dog("oneeee"));//更新数据绑定对象

        //方式二：每次使用同一个数据对象实例
        ActivityAdaptorBinding binding = DataBindingUtil.getBinding(findViewById(R.id.cl));
        binding.getDog().setName("twooooo");
        binding.getDog().setAge(2);
//        binding.getDog().setGender(true);
        binding.setDog(binding.getDog());//更新数据绑定对象
    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

        ActivityAdaptorBinding binding = DataBindingUtil.getBinding(findViewById(R.id.cl));
        binding.tv.setAge(11);
    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

        ActivityAdaptorBinding binding = DataBindingUtil.getBinding(findViewById(R.id.cl));
        System.out.println("tv.age is " + binding.tv.getAge());
        System.out.println("dog.age is " + binding.getDog().getAge());

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