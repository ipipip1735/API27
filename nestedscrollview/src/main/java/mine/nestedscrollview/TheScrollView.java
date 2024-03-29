package mine.nestedscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2021/7/4.
 */
public class TheScrollView extends ScrollView {
    public TheScrollView(Context context) {
        super(context);
        System.out.println("~~TheScrollView.TheScrollView~~");
    }

    public TheScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        System.out.println("~~TheScrollView.TheScrollView~~");
    }

    public TheScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        System.out.println("~~TheScrollView.TheScrollView~~");
    }

    public TheScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        System.out.println("~~TheScrollView.TheScrollView~~");
    }

    @Override
    public boolean startNestedScroll(int axes) {
        System.out.println("~~" + getClass().getSimpleName() + ".startNestedScroll~~");
        boolean b = super.startNestedScroll(axes);
        System.out.println("TheScrollView.startNestedScroll|b = " + b);


        return b;
    }


    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        System.out.println("~~TheScrollView.onOverScrolled~~");
        System.out.println("TheScrollView.onOverScrolled|scrollX = " + scrollX + ", scrollY = " + scrollY + ", clampedX = " + clampedX + ", clampedY = " + clampedY);

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        System.out.println("~~" + getClass().getSimpleName() + ".onScrollChanged~~");
        System.out.println("TheScrollView.onScrollChanged|l = " + l + ", t = " + t + ", oldl = " + oldl + ", oldt = " + oldt);
        super.onScrollChanged(l, t, oldl, oldt);
//        System.out.println("getScrollX() = " + getScrollX() + ", getScrollY() = " + getScrollY());
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        System.out.println("~~TheScrollView.onStartNestedScroll~~");
        System.out.println("child = " + child + ", target = " + target + ", nestedScrollAxes = " + nestedScrollAxes);
        Boolean b = super.onStartNestedScroll(child, target, nestedScrollAxes);
        System.out.println("b = " + b);
        return b;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        System.out.println("~~TheScrollView.onNestedScrollAccepted~~");
        super.onNestedScrollAccepted(child, target, axes);
        System.out.println("TheScrollView.onNestedScrollAccepted|child = " + child + ", target = " + target + ", axes = " + axes);
    }

    @Override
    public void onStopNestedScroll(View target) {
        System.out.println("~~TheScrollView.onStopNestedScroll~~");
        super.onStopNestedScroll(target);
        System.out.println("TheScrollView.onStopNestedScroll|target = " + target);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        System.out.println("~~TheScrollView.onNestedScroll~~");
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        System.out.println("TheScrollView.onNestedScroll|target = " + target + ", dxConsumed = " + dxConsumed + ", dyConsumed = " + dyConsumed + ", dxUnconsumed = " + dxUnconsumed + ", dyUnconsumed = " + dyUnconsumed);
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        System.out.println("~~TheScrollView.onNestedFling~~");
        System.out.println("TheScrollView.onNestedFling|target = " + target + ", velocityX = " + velocityX + ", velocityY = " + velocityY + ", consumed = " + consumed);
        return super.onNestedFling(target, velocityX, velocityY, consumed);
    }
}
