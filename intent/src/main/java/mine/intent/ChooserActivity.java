package mine.intent;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ComponentInfo;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import static android.content.Intent.EXTRA_ALTERNATE_INTENTS;
import static android.content.Intent.EXTRA_CHOOSER_REFINEMENT_INTENT_SENDER;
import static android.content.Intent.EXTRA_CHOSEN_COMPONENT;
import static android.content.Intent.EXTRA_CHOSEN_COMPONENT_INTENT_SENDER;
import static android.content.Intent.EXTRA_INITIAL_INTENTS;
import static android.content.Intent.EXTRA_INTENT;

/**
 * Created by Administrator on 2021/3/13.
 */
public class ChooserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_client);


        Intent intent = getIntent();
        System.out.println("intent = " + intent);
        if (intent.getExtras() != null) {


            //当使用EXTRA_CHOOSER_REFINEMENT_INTENT_SENDER时
            System.out.println("EXTRA_INTENT is " + intent.getExtras().get(EXTRA_INTENT));
            intent = (Intent) intent.getExtras().get(EXTRA_INTENT);
            System.out.println("EXTRA_ALTERNATE_INTENTS is " + intent.getExtras().get(EXTRA_ALTERNATE_INTENTS));
            startActivity(intent);


            //当使用EXTRA_CHOSEN_COMPONENT_INTENT_SENDER时
//            System.out.println("EXTRA_CHOSEN_COMPONENT is " + intent.getExtras().get(EXTRA_CHOSEN_COMPONENT));
//            ComponentName componentName = (ComponentName) intent.getExtras().get(EXTRA_CHOSEN_COMPONENT);
//            intent = new Intent();
//            intent.setComponent(componentName);
//            startActivity(intent);

        }


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
//        Intent chooser = new Intent(Intent.ACTION_CHOOSER);
//        chooser.putExtra(Intent.EXTRA_INTENT, new Intent("one"));//设置主Intent
////        chooser.putExtra(Intent.EXTRA_INTENT, new Intent("xx" + new Random().nextInt(100)));//将主Intent设置为不匹配任何Android组件
//
////        chooser.putExtra(Intent.EXTRA_TITLE, "请选择方式");//设置选择器标题
////
////        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, //在主Intent之前展现
////                new Intent[]{new Intent("two")});
////
////        chooser.putExtra(Intent.EXTRA_ALTERNATE_INTENTS, //在主Intent之后展现
////                new Intent[]{new Intent("three")});
////
////        chooser.putExtra(Intent.EXTRA_EXCLUDE_COMPONENTS, //排除某些被匹配到的Android组件
////                new ComponentName[]{new ComponentName("mine.file", "mine.file.PermissionActivity")});
//
//        chooser.putExtra(Intent.EXTRA_AUTO_LAUNCH_SINGLE_CHOICE, //仅匹配到单个Android组件是否显示选择器
//                true);//true表示不显示选择器，而是自动启动匹配到的android组件
//
//        startActivityForResult(chooser, 1);


        //方式二：快捷方式
//        Intent chooser = Intent.createChooser(new Intent("one"), "请选择方式");
//        Intent[] intentArray = {new Intent("two"), new Intent("three")};
//        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
//        startActivity(chooser);

    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");


        //方式一：指定发送器（使用EXTRA_CHOSEN_COMPONENT_INTENT_SENDE）
//        Intent chooser = new Intent(Intent.ACTION_CHOOSER);
//        chooser.putExtra(Intent.EXTRA_INTENT, new Intent("one"));
//        chooser.putExtra(Intent.EXTRA_TITLE, "请选择方式");
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, new Intent(this, ChooserActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
//        IntentSender intentSender = pendingIntent.getIntentSender();//创建IntentSender
//        chooser.putExtra(EXTRA_CHOSEN_COMPONENT_INTENT_SENDER, intentSender);//设置IntentSender
//
//        Intent[] intentArray = {new Intent("two"), new Intent("three")};
//        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
//
//        startActivity(chooser);


        //方式二：指定发送器（使用EXTRA_CHOOSER_REFINEMENT_INTENT_SENDER）
//        Intent chooser = new Intent(Intent.ACTION_CHOOSER);
//        chooser.putExtra(Intent.EXTRA_INTENT, new Intent("one"));
//        chooser.putExtra(Intent.EXTRA_TITLE, "请选择方式");
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, new Intent(this, ChooserActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
//        IntentSender intentSender = pendingIntent.getIntentSender();
//        chooser.putExtra(EXTRA_CHOOSER_REFINEMENT_INTENT_SENDER, intentSender);
//
//        Intent[] intentArray = {new Intent("two"), new Intent("three")};
//        chooser.putExtra(EXTRA_INITIAL_INTENTS, intentArray);
//
//        startActivity(chooser);


        //方式三：指定发送器（使用EXTRA_CHOOSER_REFINEMENT_INTENT_SENDER）
        Intent chooser = new Intent(Intent.ACTION_CHOOSER);
        chooser.putExtra(Intent.EXTRA_INTENT, new Intent("one"));
        chooser.putExtra(Intent.EXTRA_TITLE, "请选择方式");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, new Intent(this, TheBroadcastReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT);
        IntentSender intentSender = pendingIntent.getIntentSender();//创建IntentSender
        chooser.putExtra(EXTRA_CHOSEN_COMPONENT_INTENT_SENDER, intentSender);//设置IntentSender

        Intent[] intentArray = {new Intent("two"), new Intent("three")};
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);

        startActivity(chooser);


//        sendBroadcast(new Intent(this, TheBroadcastReceiver.class));

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