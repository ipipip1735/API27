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

import java.util.Random;

import static androidx.viewpager2.widget.ViewPager2.ORIENTATION_VERTICAL;

public class TwoActivity extends AppCompatActivity {
    ViewPager2 mPager;
    float offset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        setContentView(R.layout.activity_two);

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

        boolean r = mPager.beginFakeDrag();
        System.out.println("r is " + r);
    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");
        boolean r = mPager.endFakeDrag();
        System.out.println("r is " + r);
    }

    public void add(View view) {
        System.out.println("~~button.add~~");

        boolean r = mPager.fakeDragBy(offset+=30f);
        System.out.println("r is " + r + "," +
                "offset is " + offset);

    }

    public void del(View view) {
        System.out.println("~~button.del~~");

        mPager.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                System.out.println("~~onDraw~~");
                super.onDraw(c, parent, state);
            }

            @Override
            public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                System.out.println("~~onDrawOver~~");
                super.onDrawOver(c, parent, state);

                Random random = new Random();
                Paint paint = new Paint();
//                int height = parent.getHeight();

                for (int i = 1; i < parent.getChildCount(); i++) { //遍历绘制
                    int left = parent.getChildAt(i).getLeft();
                    int top = parent.getChildAt(i).getTop();
                    int right = parent.getChildAt(i).getRight();
                    int bottom = parent.getChildAt(i).getBottom();

                    System.out.println("child is " + i + ", " +
                            "w is " + parent.getChildAt(i).getWidth() + ", " +
                            "h is " + parent.getChildAt(i).getHeight() + ", " +
                            "left is " + parent.getChildAt(i).getLeft() + ", " +
                            "right is " + parent.getChildAt(i).getRight() + ", " +
                            "top is " + parent.getChildAt(i).getTop() + ", " +
                            "bottom is " + parent.getChildAt(i).getBottom());

                    paint.setColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
                    c.drawRect(left, top, left+50, bottom, paint);
                }
            }

            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                System.out.println("~~getItemOffsets~~");
                super.getItemOffsets(outRect, view, parent, state);

                System.out.println(outRect);

                int paddingLeft, paddingTop, paddingRight, paddingBottom;
                paddingLeft = 0;
                paddingTop = 0;
                paddingRight = 0;
                paddingBottom = 0;
                outRect.set(paddingLeft, paddingTop, paddingRight, paddingBottom);
            }
        });

    }
}
