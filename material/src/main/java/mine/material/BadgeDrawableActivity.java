package mine.material;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.button.MaterialButtonToggleGroup;

import static com.google.android.material.badge.BadgeDrawable.BOTTOM_START;

/**
 * Created by Administrator on 2020/6/27.
 */
public class BadgeDrawableActivity extends AppCompatActivity {
    BadgeDrawable badgeDrawable;

    @SuppressLint("RestrictedApi")
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
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestoreInstanceState  *********");

        super.onRestoreInstanceState(savedInstanceState);
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
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");

        super.onPause();
    }

    @Override
    public void onBackPressed() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onBackPressed  *********");

        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");

        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onSaveInstanceState  *********");

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");

        super.onDestroy();
    }


    public void start(View view) {
        System.out.println("~~button.start~~");

        badgeDrawable = BadgeDrawable.create(this);
        badgeDrawable.setNumber(88);
        badgeDrawable.setAlpha(255);
        badgeDrawable.setMaxCharacterCount(2);
        badgeDrawable.setBadgeGravity(BadgeDrawable.BOTTOM_END);
        badgeDrawable.setBackgroundColor(getResources().getColor(R.color.Black, null));
        badgeDrawable.setBadgeTextColor(getResources().getColor(R.color.Yellow, null));


        ViewGroup viewGroup = (ViewGroup) view.getParent();
        System.out.println(viewGroup);
        viewGroup.setClipChildren(false);
        BadgeUtils.attachBadgeDrawable(badgeDrawable, view, null);

    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

        badgeDrawable.setNumber(badgeDrawable.getNumber()+1);
    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

        badgeDrawable = BadgeDrawable.create(this);
        badgeDrawable.setNumber(1);
        badgeDrawable.setBadgeGravity(BOTTOM_START);

        View v = findViewById(R.id.button14);
        System.out.println(v);

        ViewGroup viewGroup = (ViewGroup) v.getParent();
        System.out.println(viewGroup);

        viewGroup.setClipChildren(false);
        BadgeUtils.attachBadgeDrawable(badgeDrawable, v, null);

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
