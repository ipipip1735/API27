package mine.databinding;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import java.util.Date;

import mine.databinding.data.CattleObservable;
import mine.databinding.data.CustomerObservable;
import mine.databinding.databinding.ActivityTwoWayWithConvertersBinding;


/**S
 * Created by Administrator on 2020/12/15.
 */
public class TwoWayWithConvertersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        ActivityTwoWayWithConvertersBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_two_way_with_converters);
        binding.setCustomer(new CustomerObservable("Bob"));
        System.out.println(binding.getCustomer());

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

        ActivityTwoWayWithConvertersBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_two_way_with_converters);
//        binding.setCustomer(new CustomerObservable("Bob"));
        System.out.println(binding.getCustomer());

//        ActivityTwoWayWithConvertersBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_two_way_with_converters);
//        System.out.println(binding.getCustomer());

//        binding.getCustomer().setBirthDay(new Date());


//        System.out.println();

    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

        ActivityTwoWayWithConvertersBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_two_way_with_converters);
//        String s = (String) binding.textView.getText();
//        System.out.println("s = " + s);

//        System.out.println("birthDay is " + binding.getCustomer().getBirthDay());

        System.out.println("tv is " + binding.textView);

        binding.textView.setText("ddddd");
    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

        ActivityTwoWayWithConvertersBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_two_way_with_converters);
        System.out.println(binding.getCustomer());
//        System.out.println(binding.getCustomer().getBirthDay());

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