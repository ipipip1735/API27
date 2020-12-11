package mine.databinding;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import mine.databinding.data.User;
import mine.databinding.data.TheViewModel;
import mine.databinding.databinding.ActivityLifecycleBinding;


/**
 * Created by Administrator on 2020/11/2.
 */
public class LifecycleActivity extends AppCompatActivity {
    TheViewModel theViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        ActivityLifecycleBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_lifecycle);
        binding.setLifecycleOwner(this);

        //仅LiveDate
        binding.setUser(new MutableLiveData<>(new User("Mary")));

        //ViewModel包裹的LiveData
        theViewModel = new ViewModelProvider(this).get(TheViewModel.class);
        theViewModel.setUser(new MutableLiveData<>(new User("Tom")));
        binding.setViewModel(theViewModel);
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

        //修改ViewModel(View属性同步变更)
//        ActivityLifecycleBinding binding = DataBindingUtil.getBinding(findViewById(R.id.cl));
//        binding.getViewModel().getUser().setValue(new User("Jack"));
////        theViewModel.getUser().setValue(new User("Jack"));


        //修改View属性(同时更新数据绑定对象)
        TextView textView = findViewById(R.id.textView7);
        User user = new User("Jone");
        textView.setText(user.getName() + " - " + user.getAge());
        ActivityLifecycleBinding binding = DataBindingUtil.getBinding(findViewById(R.id.cl));
        binding.getViewModel().getUser().setValue(user);

    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

        ActivityLifecycleBinding binding = DataBindingUtil.getBinding(findViewById(R.id.cl));
        binding.getUser().setValue(new User("Lisa"));
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

        ActivityLifecycleBinding binding = DataBindingUtil.getBinding(findViewById(R.id.cl));
        System.out.println("User is " + binding.getViewModel().getUser().getValue());

        TextView textView = findViewById(R.id.textView7);
        System.out.println(textView.getText());

    }
}