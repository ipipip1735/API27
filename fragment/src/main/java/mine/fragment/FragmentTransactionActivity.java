package mine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

/**
 * Created by Administrator on 2019/2/17.
 */
public class FragmentTransactionActivity extends AppCompatActivity {

    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentManager.OnBackStackChangedListener listener;
    List<Integer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("**********  Main.onCreate  ***********");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transation);
        fragmentManager = getSupportFragmentManager();

        //注册监听器
        listener = new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                System.out.println("-- addOnBackStackChangedListener.onBackStackChanged --");
            }
        };
        fragmentManager.addOnBackStackChangedListener(listener);

        list = new ArrayList<>();

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
//        fragment = fragmentManager.getFragment(savedInstanceState, "basic");
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
//        fragmentManager.putFragment(outState, "basic", fragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");

        //注销监听器
        fragmentManager.removeOnBackStackChangedListener(listener);
        listener = null;
    }

    public void add(View view) {
        System.out.println("~~button.button.add~~");

        //配置参数
        int id = new Random().nextInt(99);
        list.add(id); //保存ID
        String text = "text-" + id;
        Bundle bundle = new Bundle();
        bundle.putString("text", text);

        //创建Fragment
        TextFragment textFragment = new TextFragment();
        textFragment.setArguments(bundle);//传递参数


        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.ll, textFragment, "Tag-" + id)
                .addToBackStack("addText" + id);
        fragmentTransaction.commit();

    }


    public void remove(View view) {
        System.out.println("~~button.remove~~");

        //配置参数
        String text = "text" + new Random().nextInt(99);
        Bundle bundle = new Bundle();
        bundle.putString("text", text);

        //创建Fragment
        TextFragment textFragment = new TextFragment();
        textFragment.setArguments(bundle);//传递参数


        getSupportFragmentManager()
                .beginTransaction()
                .remove(fragmentManager.findFragmentByTag("frag1"))
                .addToBackStack("removeText")
                .commit();

    }

    public void replace(View view) {
        System.out.println("~~button.replace~~");

        //配置参数
        String text = "text" + new Random().nextInt(99);
        Bundle bundle = new Bundle();
        bundle.putString("text", text);

        //创建Fragment
        TextFragment textFragment = new TextFragment();
        textFragment.setArguments(bundle);//传递参数


        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.ll, textFragment, "NewFrag")
                .addToBackStack("three");
        fragmentTransaction.commit();

    }

    public void show(View view) {
        System.out.println("~~button.show~~");

        int count = fragmentManager.getBackStackEntryCount();//获取总数
        System.out.println("count is " + count);

        for (int i = 0; i < count; i++) { //打印
            FragmentManager.BackStackEntry backStackEntry = fragmentManager.getBackStackEntryAt(i);
            System.out.println("getId is " + backStackEntry.getId());
            System.out.println("getName is " + backStackEntry.getName());
        }
    }

    public void hide(View view) {
        System.out.println("~~button.hide~~");
    }

    public void attach(View view) {
        System.out.println("~~button.attach~~");

    }

    public void detach(View view) {
        System.out.println("~~button.detach~~");
    }

    public void pop(View view) {
        System.out.println("~~button.pop~~");

        String tag = "addText" + list.get(2);
        System.out.println("tag is " + tag);
        fragmentManager.popBackStack(tag, POP_BACK_STACK_INCLUSIVE);

    }
}
