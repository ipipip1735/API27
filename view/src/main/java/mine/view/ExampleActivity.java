package mine.view;

import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/4/14.
 */

public class ExampleActivity extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        System.out.println("*******  Example  onCreateMenu  *********");
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return super.onCreateOptionsMenu(menu);
        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        System.out.println("*******  Example  onPrepareMenu  *********");
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("**********  Example  onCreate  ***********");

        setContentView(R.layout.activity_task);


        XmlResourceParser parser = getResources().getXml(R.layout.activity_task);
        XmlResourceParser xmlResourceParser = getResources().getLayout(R.layout.activity_task);



    }

    @Override
    protected void onNewIntent(Intent intent) {
        System.out.println("****  Example  onNewIntent  *****");
        super.onNewIntent(intent);
    }


    @Override
    protected void onStart() {
        System.out.println("****Example  onStart*****");
        super.onStart();

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("****  Example  onRestoreInstanceState  *****");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        System.out.println("****Example  onRestart*****");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        System.out.println("****Example  onResume*****");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("****Example  onPause*****");
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        System.out.println("********Example***onBackPressed**********");
        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("****Example  onStop*****");
        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("****  Example   onSaveInstanceState  *****");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("****Example  onDestroy*****");
        super.onDestroy();
    }


    public void initLoad(View view) throws IOException {

        System.out.println("---------  initLoad  --------");

        try {
            XmlPullParserFactory  factory  =  XmlPullParserFactory.newInstance();
//            factory.setFeature("FEATURE_XML_ROUNDTRIP",true);
//            factory.setFeature("FEATURE_PROCESS_NAMESPACES",true);
//            factory.setFeature("FEATURE_PROCESS_DOCDECL",true);

            XmlResourceParser xrp = getResources().getXml(R.xml.mytest);


            System.out.println(xrp.TYPES[xrp.next()]);
            System.out.println("getLineNumber = " + xrp.getLineNumber());
            System.out.println("getColumnNumber = " + xrp.getColumnNumber());

            System.out.println("----------------");
            System.out.println("");

            System.out.println("getName= " + xrp.getName());
            System.out.println("----------------");
            System.out.println("getNamespace = " + xrp.getNamespace());
            System.out.println("getAttributeCount = " + xrp.getAttributeCount());
            System.out.println("----------------");
            System.out.println("getText= " + xrp.getText());
            System.out.println("=========================");




            System.out.println(xrp.TYPES[xrp.next()]);
            System.out.println("getLineNumber = " + xrp.getLineNumber());
            System.out.println("getColumnNumber = " + xrp.getColumnNumber());

            System.out.println("----------------");
            System.out.println("");

            System.out.println("getName= " + xrp.getName());
            System.out.println("----------------");
            System.out.println("getNamespace = " + xrp.getNamespace());
            System.out.println("getAttributeCount = " + xrp.getAttributeCount());
            System.out.println("----------------");
            System.out.println("getText= " + xrp.getText());
            System.out.println("=========================");




            System.out.println(xrp.TYPES[xrp.next()]);
            System.out.println("getLineNumber = " + xrp.getLineNumber());
            System.out.println("getColumnNumber = " + xrp.getColumnNumber());

            System.out.println("----------------");
            System.out.println("");

            System.out.println("getName= " + xrp.getName());
            System.out.println("----------------");
            System.out.println("getNamespace = " + xrp.getNamespace());
            System.out.println("getAttributeCount = " + xrp.getAttributeCount());
            System.out.println("----------------");
            System.out.println("getText= " + xrp.getText());
            System.out.println("=========================");






        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

    }
}

