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
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        System.out.println("~~TheScrollView.onOverScrolled~~");
        System.out.println("scrollX = " + scrollX + ", scrollY = " + scrollY + ", clampedX = " + clampedX + ", clampedY = " + clampedY);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        System.out.println("~~TheScrollView.onStartNestedScroll~~");
        System.out.println("child = " + child + ", target = " + target + ", nestedScrollAxes = " + nestedScrollAxes);
        return super.onStartNestedScroll(child, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        super.onNestedScrollAccepted(child, target, axes);
        System.out.println("~~TheScrollView.onNestedScrollAccepted~~");
        System.out.println("child = " + child + ", target = " + target + ", axes = " + axes);
    }

    @Override
    public void onStopNestedScroll(View target) {
        super.onStopNestedScroll(target);
        System.out.println("~~TheScrollView.onSt~~opNestedScroll");
        System.out.println("target = " + target);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        System.out.println("~~TheScrollView.onNestedScroll~~");
        System.out.println("target = " + target + ", dxConsumed = " + dxConsumed + ", dyConsumed = " + dyConsumed + ", dxUnconsumed = " + dxUnconsumed + ", dyUnconsumed = " + dyUnconsumed);
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        System.out.println("~~TheScrollView.onNestedFling~~");
        System.out.println("target = " + target + ", velocityX = " + velocityX + ", velocityY = " + velocityY + ", consumed = " + consumed);
        return super.onNestedFling(target, velocityX, velocityY, consumed);
    }
}
