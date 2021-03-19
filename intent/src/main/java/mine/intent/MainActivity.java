package mine.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.net.URISyntaxException;
import java.util.List;

import static android.content.Intent.CATEGORY_ALTERNATIVE;
import static android.content.Intent.URI_ALLOW_UNSAFE;

/**
 * Created by Administrator on 2021/3/10.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_client);

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

        Intent intent = new Intent("one");//仅设action
//        Intent intent = new Intent("one", Uri.parse("AA://BB:123/CC/d.efg"));//设置data和action
//        Intent intent = new Intent("one", Uri.parse("AA://BB:123/CC/d.efg"));

//        intent.setType("aa/bb");//设置MIM，把Uri设为null
//        intent.setData(Uri.parse("AA://BB:123/CC/d.efg"));//设置Uri，把MIME设为null
//        intent.setDataAndType(Uri.parse("AA://BB:123/CC/d.efg"), "aa/bb");//设置Uri和MIME


//        intent.setPackage(getPackageName());


        startActivity(intent);

    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

        //启动多个Activity
        Intent[] intents = new Intent[]{new Intent("one"), new Intent("two")};
        startActivities(intents);

    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

        //Uri和Intent转换
        Intent intent = new Intent("aaa");
        intent.setDataAndType(Uri.parse("AA://BB:123/CC/d.efg"), "mm/nn");
        intent.setComponent(getComponentName());
        intent.setPackage(getPackageName());
        intent.addCategory(CATEGORY_ALTERNATIVE);
        String uri = intent.toUri(URI_ALLOW_UNSAFE);
        System.out.println("uri = " + uri); //Uri = AA://BB:123/CC/d.efg#Intent;action=aaa;type=mm/nn;end

        try {
            intent = Intent.parseUri(uri, URI_ALLOW_UNSAFE);
            System.out.println("getData is " + intent.getData());
            System.out.println("getAction is " + intent.getAction());
            System.out.println("getType is " + intent.getType());
            System.out.println("getCategories is " + intent.getCategories());
            System.out.println("getScheme is " + intent.getScheme());
            System.out.println("getComponent is " + intent.getComponent());
            System.out.println("getPackage is " + intent.getPackage());
            System.out.println("getFlags is " + intent.getFlags());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");

        //标准化MIME
        String mime = Intent.normalizeMimeType("text/plain; charset=utf-8");//text/plain; charset=utf-8将转化为text/plain
        System.out.println("mime = " + mime);

        mime = Intent.normalizeMimeType("ab/Vv; ttt");//ab/Vv; ttt将转化为ab/vv
        System.out.println("mime = " + mime);

    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void del(View view) {
        System.out.println("~~button.del~~");





    }


    public void query(View view) {
        System.out.println("~~button.query~~");


        //方式一
//        Intent intent = new Intent("one");
//
//        ActivityInfo activityInfo = intent.resolveActivityInfo(getPackageManager(), 0);
//        System.out.println("targetActivity = " + activityInfo.targetActivity);
//        System.out.println("taskAffinity = " + activityInfo.taskAffinity);
//        System.out.println("theme = " + activityInfo.theme);
//
//        ComponentName componentName = intent.resolveActivity(getPackageManager());
//        System.out.println("componentName = " + componentName);

        //方式二：使用PackageManager
        Intent intent = new Intent();
        intent.setType("image/*");
        List<ResolveInfo> resolveInfos = getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resolveInfos) {
            System.out.println(resolveInfo);
            System.out.println(resolveInfo.activityInfo.name);
            System.out.println(resolveInfo.activityInfo.packageName);
            System.out.println("---");
        }



    }
}