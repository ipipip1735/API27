package mine.utilities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONArrayActivity extends AppCompatActivity {
    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        setContentView(R.layout.activity_json);

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


    public void init(View view) {
        System.out.println("~~button.init~~");

        //方式一：创建空JSON对象
//        jsonArray = new JSONArray();
//        System.out.println(jsonArray);


        //方式二：使用字符串创建JSON对象
////        String json = "{\"one\": 111, \"two\": \"222\"}";
//        String json = "[{\"one\": 111}, {\"two\": \"222\"}]";
//        try {
//            jsonArray = new JSONArray(json);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        System.out.println(jsonArray);


        //方式三：使用字符串创建JSON对象
        List list = new ArrayList<>();
        list.add("one");
        list.add(222);

        Map<String, Object> copyFrom = new HashMap<>();
        copyFrom.put("one", 111);
        copyFrom.put("two", "222");
        list.add(new JSONObject(copyFrom));


        jsonArray = new JSONArray(list);

        System.out.println(jsonArray);

    }


    public void get(View view) {
        System.out.println("~~button.get~~");


    }


    public void add(View view) {
        System.out.println("~~button.add~~");


    }

    public void del(View view) {
        System.out.println("~~button.del~~");

    }

    public void info(View view) {
        System.out.println("~~button.info~~");

        System.out.println(jsonArray);
    }

    public void resize(View view) {
        System.out.println("~~button.resize~~");

    }


    public void snapshot(View view) {
        System.out.println("~~button.snapshot~~");

    }
}
