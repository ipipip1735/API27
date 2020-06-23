package mine.material;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * Created by Administrator on 2020/6/23.
 */
class OneBehavior extends CoordinatorLayout.Behavior<Button> {
    public OneBehavior() {
        super();
        System.out.println("++" + getClass().getSimpleName() + ".Constructor1++");
    }

    public OneBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        System.out.println("++" + getClass().getSimpleName() + ".Constructor2++");
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull Button child, @NonNull View dependency) {
        System.out.println("~~" + getClass().getSimpleName() + ".layoutDependsOn~~");

        System.out.println("parent is " + parent);
        System.out.println("child is " + child);
        System.out.println("dependency is " + dependency);
        boolean r = super.layoutDependsOn(parent, child, dependency);
        System.out.println(r);
//        return r;
        return true;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull Button child, @NonNull View dependency) {
        System.out.println("~~" + getClass().getSimpleName() + ".onDependentViewChanged~~");
        System.out.println("parent is " + parent);
        System.out.println("child is " + child);
        System.out.println("dependency is " + dependency);


        boolean r = super.onDependentViewChanged(parent, child, dependency);
//        return r;
        return true;
    }
}