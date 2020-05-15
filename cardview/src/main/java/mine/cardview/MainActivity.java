package mine.cardview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2019/4/12.
 */
public class MainActivity extends AppCompatActivity {

    int left = 0;
    int top = 0;
    int right = 0;
    int bottom = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_main);
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


    public void update(View view) {
        System.out.println("~~button.start~~");

        CardView cardView = findViewById(R.id.cv);


        cardView.setUseCompatPadding(true);//保证阴影显示的在Lollipop前后版本是一致的

        //更新半径
//        cardView.setRadius(cardView.getRadius() + 5f);
//        System.out.println("getRadius is " + cardView.getRadius());


        //更新padding
        cardView.setContentPadding(this.left += 10, this.top, this.right, this.bottom);


    }

    public void elevation(View view) {
        System.out.println("~~button.start~~");

        CardView cardView = findViewById(R.id.cv);


        //更新高度
        cardView.setElevation(cardView.getElevation() + 15f);//View的方法不一定兼容
        cardView.setCardElevation(cardView.getCardElevation() + 15f);//兼容方法
        cardView.setMaxCardElevation(10f);
        System.out.println("setElevation is " + cardView.getElevation());

    }
}
