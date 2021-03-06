 package mine.hilt;

 import android.os.Bundle;
 import android.view.View;

 import androidx.appcompat.app.AppCompatActivity;

 import javax.inject.Inject;

 import dagger.hilt.android.AndroidEntryPoint;
 import mine.hilt.data.Company;
 import mine.hilt.data.Employee;

 /**
  * Created by Administrator on 2020/12/25.
  */
 @AndroidEntryPoint
 public class OneActivity extends AppCompatActivity {
     @Inject
     Company company1, company2;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
         setContentView(R.layout.activity_main);

         //作用域
         System.out.println("OneActivity|company1 = " + company1);
         System.out.println("OneActivity|company2 = " + company2);

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
     }

     public void stop(View view) {
         System.out.println("~~button.stop~~");
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