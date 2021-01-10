package mine.viewmodel;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.io.File;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    BaseViewModel baseViewModel;
    StringViewModel stringViewModel;
    SavedStateViewModel savedStateViewModel;
    SavedStateWithProviderViewModel savedStateWithProviderViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_client);

        //方式一：最简使用
//        stringViewModel = new ViewModelProvider(this).get(StringViewModel.class);
//        System.out.println("stringViewModel is " + stringViewModel.getName() + "|" + stringViewModel.hashCode());



        //方式二：最简LiveData
//        textView = new TextView(this);
//        textView.setText("go go go");
//        ViewGroup viewGroup = findViewById(R.id.fl);
//        viewGroup.addView(textView);
//
//        baseViewModel = ViewModelProviders.of(this).get(BaseViewModel.class);
//        baseViewModel.getUsers().observe(this, data -> {
//            System.out.println("~~update ViewModel~~");
//            textView.setText(data);//更新UI
//        });


        //方法三：最简SavedStateHandle
//        savedStateViewModel = new ViewModelProvider(this).get(SavedStateViewModel.class);
//        System.out.println("savedStateViewModel = " + savedStateViewModel);


        //方法三：最简ViewModel提供器
        savedStateWithProviderViewModel = new ViewModelProvider(this).get(SavedStateWithProviderViewModel.class);
        System.out.println("savedStateWithProviderViewModel = " + savedStateWithProviderViewModel);

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
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestoreInstanceState  *********");
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onSaveInstanceState  *********");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");
    }

    public void start(View view) {
        System.out.println("~~button.start~~");
        baseViewModel.getUsers().postValue("User" + new Random().nextInt(100));
    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");
        stringViewModel = new ViewModelProvider(this).get(StringViewModel.class);
        System.out.println("stringViewModel is " + stringViewModel.getName() + "|" + stringViewModel.hashCode());
    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");
        savedStateViewModel.setName("bob");
    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");
        System.out.println("name is " + savedStateViewModel.getName());
    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");
    }


    public void del(View view) {
        System.out.println("~~button.del~~");

        savedStateWithProviderViewModel.setFile(getCacheDir());
    }


    public void query(View view) {
        System.out.println("~~button.query~~");

        File file = savedStateWithProviderViewModel.getFile();
        System.out.println("file = " + file);
    }

}
