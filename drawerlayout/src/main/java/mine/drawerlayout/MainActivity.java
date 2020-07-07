package mine.drawerlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import static androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
import static androidx.drawerlayout.widget.DrawerLayout.STATE_DRAGGING;
import static androidx.drawerlayout.widget.DrawerLayout.STATE_IDLE;
import static androidx.drawerlayout.widget.DrawerLayout.STATE_SETTLING;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.activity_sub);


        DrawerLayout drawerLayout = findViewById(R.id.dl);

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                System.out.println("~~onDrawerSlide~~");
                System.out.println("drawerView is " + drawerView);
                System.out.println("slideOffset is " + slideOffset);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                System.out.println("~~onDrawerOpened~~");
                System.out.println("drawerView is " + drawerView);
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                System.out.println("~~onDrawerClosed~~");
                System.out.println("drawerView is " + drawerView);

            }

            @Override
            public void onDrawerStateChanged(int newState) {
                System.out.println("~~onDrawerStateChanged~~");
                switch (newState) {
                    case STATE_IDLE:
                        System.out.println("newState is STATE_IDLE");
                        break;
                    case STATE_DRAGGING:
                        System.out.println("newState is STATE_DRAGGING");
                        break;
                    case STATE_SETTLING:
                        System.out.println("newState is STATE_SETTLING");
                        break;
                    default:
                        System.out.println("default!!");
                }
            }
        });

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
        DrawerLayout drawerLayout = findViewById(R.id.dl);

//        drawerLayout.setDrawerElevation(30f);
//        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);

//        drawerLayout.setDrawerShadow(getResources().getDrawable(R.color.AntiqueWhite, null), GravityCompat.START);
//        drawerLayout.setDrawerTitle(GravityCompat.START, "ooooooo");
//        drawerLayout.setScrimColor(getColor(R.color.AntiqueWhite));
//        drawerLayout.setStatusBarBackground(R.color.AntiqueWhite);
        drawerLayout.setStatusBarBackgroundColor(getColor(R.color.AntiqueWhite));

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
