package mine.coordinatorlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.appbar.AppBarLayout;

import java.net.URLEncoder;

/**
 * Created by Administrator on 2020/6/23.
 */
class TwoBehavior extends CoordinatorLayout.Behavior<View> {

    public TwoBehavior() {
        super();
        System.out.println("++" + getClass().getSimpleName() + ".Constructor1++");
    }

    public TwoBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        System.out.println("++" + getClass().getSimpleName() + ".Constructor2++");
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       @NonNull View child, @NonNull View directTargetChild,
                                       @NonNull View target, int axes, int type) {
        System.out.println("~~" + getClass().getSimpleName() + ".onStartNestedScroll~~");
        System.out.println("coordinatorLayout is " + coordinatorLayout);
        System.out.println("child is " + child);
        System.out.println("target is " + target);
        System.out.println("directTargetChild is " + directTargetChild);
        System.out.println("axes is " + axes);
        System.out.println("type is " + type);

        boolean b = super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
        System.out.println(b);
        return true;
    }

    //    @Override
//    public void onNestedScrollAccepted(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
//        System.out.println("~~" + getClass().getSimpleName() + ".onNestedScrollAccepted~~");
//        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes, type);
//    }
//
    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                               @NonNull View child, @NonNull View target,
                               int dxConsumed, int dyConsumed,
                               int dxUnconsumed, int dyUnconsumed,
                               int type, @NonNull int[] consumed) {
        System.out.println("~~" + getClass().getSimpleName() + ".onNestedScroll~~");

        System.out.println("coordinatorLayout is " + coordinatorLayout);
        System.out.println("child is " + child);
        System.out.println("target is " + target);
        System.out.println("dxConsumed is " + dxConsumed);
        System.out.println("dyConsumed is " + dyConsumed);
        System.out.println("dxUnconsumed is " + dxUnconsumed);
        System.out.println("dyUnconsumed is " + dyUnconsumed);
        System.out.println("type is " + type);
        for (int i = 0; i < consumed.length; i++) {
            System.out.println("consumed.[" + i + "] = " + consumed[i]);
        }

//        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type, consumed);


        child.setX(child.getX() + dxConsumed);
        child.setY(child.getY() + dyConsumed);
        if (child.getId() == R.id.button1) {
            child.setX(0);
            child.setY(0);
        }
    }
//
//    @Override
//    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int type) {
//        System.out.println("~~" + getClass().getSimpleName() + ".onStopNestedScroll~~");
//        super.onStopNestedScroll(coordinatorLayout, child, target, type);
//    }
//
//    @Override
//    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
//        System.out.println("~~" + getClass().getSimpleName() + ".onNestedPreScroll~~");
//        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
//    }
//
//    @Override
//    public boolean onNestedFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, float velocityX, float velocityY, boolean consumed) {
//        System.out.println("~~" + getClass().getSimpleName() + ".onNestedFling~~");
//        boolean b = super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
//        System.out.println(b);
//        return false;
//    }
//
//    @Override
//    public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, float velocityX, float velocityY) {
//        System.out.println("~~" + getClass().getSimpleName() + ".onNestedPreFling~~");
//        boolean b = super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
//        System.out.println(b);
//        return false;
//    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull
            View child, @NonNull View dependency) {
        System.out.println("~~" + getClass().getSimpleName() + ".layoutDependsOn~~");

        System.out.println("parent is " + parent);
        System.out.println("child is " + child);
        System.out.println("dependency is " + dependency);

//        System.out.println("dependency|X=" + dependency.getX() + ", Y=" + dependency.getY());
//        System.out.println("child|X=" + child.getX() + ", Y=" + child.getY());


//        return super.layoutDependsOn(parent, child, dependency);
        return dependency instanceof NestedScrollView;//判断child是否依赖dependency
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        System.out.println("~~" + getClass().getSimpleName() + ".onDependentViewChanged~~");
        System.out.println("parent is " + parent);
        System.out.println("child is " + child);
        System.out.println("dependency is " + dependency);

//        System.out.println("dependency|X=" + dependency.getX() + ", Y=" + dependency.getY());
//        System.out.println("child|X=" + child.getX() + ", Y=" + child.getY());

//        child.setX(dependency.getX() + child.getWidth());
//        child.setY(dependency.getY() + child.getHeight());

//        return super.onDependentViewChanged(parent, child, dependency);
        return false;
    }
}