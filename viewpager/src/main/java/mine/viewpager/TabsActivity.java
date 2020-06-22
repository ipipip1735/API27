package mine.viewpager;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.Random;

public class TabsActivity extends AppCompatActivity {
    ViewPager2 mPager;
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
//        setContentView(R.layout.activity_tabs);
        setContentView(R.layout.activity_tabs_with_pageview2);

        mPager = findViewById(R.id.vp);
        mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        mPager.setAdapter(new FragmentStateAdapter(this) {
            @Override
            public int getItemCount() {
                return 5;
            }

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return new OneFragment(position);
            }
        });


        mPager.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                System.out.println("~~transformPage~~");
//                System.out.println("page is " + page);
                TextView textView = page.findViewById(R.id.tv);
                System.out.println(textView.getText() + " is " + position);
            }
        });




        mTabLayout = findViewById(R.id.tabLayout);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                System.out.println("~~ onTabSelected ~~");
                tabInfo(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                System.out.println("~~ onTabUnselected ~~");
                tabInfo(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                System.out.println("~~ onTabReselected ~~");
                tabInfo(tab);
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
        ViewPager mPager = findViewById(R.id.vp);

        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
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
        mTabLayout.addTab(mTabLayout.newTab());



    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

    }

    public void add(View view) {
        System.out.println("~~button.add~~");

    }

    public void del(View view) {
        System.out.println("~~button.del~~");

    }

    private void tabInfo(TabLayout.Tab tab) {
        System.out.println(tab);
        System.out.println("getBadge is " + tab.getBadge());
        System.out.println("getContentDescription is " + tab.getContentDescription());
        System.out.println("getCustomView is " + tab.getCustomView());
        System.out.println("getIcon is " + tab.getIcon());
//                System.out.println("getOrCreateBadge is " + tab.getOrCreateBadge());
        System.out.println("getPosition is " + tab.getPosition());
        System.out.println("getTabLabelVisibility is " + tab.getTabLabelVisibility());
        System.out.println("getTag is " + tab.getTag());
        System.out.println("getText is " + tab.getText());
    }
}
