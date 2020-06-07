package mine.utilities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONObjectActivity extends AppCompatActivity {
    JSONObject jsonObject;


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
//        jsonObject = new JSONObject();
//        System.out.println(jsonObject);


        //方式二：使用字符串创建JSON对象
//        String json = " {\n" +
//                "     \"query\": \"Pizza\",\n" +
//                "     \"locations\": [\n" +
//                "         94043,\n" +
//                "         90210\n" +
//                "     ]\n" +
//                " }";
//        try {
//            jsonObject = new JSONObject(json);
//            System.out.println(jsonObject);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


        Map<String, Object> copyFrom = new HashMap<>();
        copyFrom.put("one", 111);
        copyFrom.put("two", "222");
        copyFrom.put("three", 300L);
        copyFrom.put("five", JSONObject.NULL);
        copyFrom.put("four", null);

        jsonObject = new JSONObject(copyFrom);
        System.out.println(jsonObject);

    }

    public void get(View view) {
        System.out.println("~~button.get~~");

        //方法以一：使用get方法
        try {
            boolean jo_boolean = jsonObject.getBoolean("jo-boolean");
            System.out.println("jo_boolean is " + jo_boolean);

            int jo_int = jsonObject.getInt("jo-int");
            System.out.println("jo_int is " + jo_int);

            double jo_double = jsonObject.getDouble("jo-double");
            System.out.println("jo_double is " + jo_double);

            long jo_long = jsonObject.getLong("jo-long");
            System.out.println("jo_long is " + jo_long);


            Object obj = jsonObject.get("jo-long");
            System.out.println("obj is " + obj);


//            System.out.println(jsonObject.getJSONArray(String name));
//            System.out.println(jsonObject.getJSONObject(String name));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //方法二：使用opt方法
//        double jo_double = jsonObject.optDouble("jo-xx", 9.9d);
//        System.out.println("jo_double is " + jo_double);


    }


    public void add(View view) {
        System.out.println("~~button.add~~");

        try {
            jsonObject.put("jo-double", 11.3d);
            jsonObject.put("jo-boolean", true);
            jsonObject.put("jo-int", 11);
            jsonObject.put("jo-long", 12345L);
            jsonObject.put("jo-objec", new Object());
            jsonObject.put("jo-null", JSONObject.NULL);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(jsonObject);

    }

    public void del(View view) {
        System.out.println("~~button.del~~");


        try {

            System.out.println(jsonObject);
            jsonObject.put("jo-boolean", null);
            System.out.println(jsonObject);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        jsonObject.remove("jo-double");
        System.out.println(jsonObject);


    }

    public void info(View view) {
        System.out.println("~~button.info~~");

        System.out.println(jsonObject);

    }



}
