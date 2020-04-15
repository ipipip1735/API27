package mine.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class CustomViewGroup extends ViewGroup {
    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        System.out.println("... " + this.getClass().getSimpleName() + ".CustomViewGroup ...");


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        System.out.println("... " + this.getClass().getSimpleName() + ".onMeasure ...");

        int measuredHeight = 0, measuredWidth = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {

            getChildAt(i).measure(999, 999);
            measuredWidth += getChildAt(i).getMeasuredWidth();
            measuredHeight += getChildAt(i).getMeasuredHeight();

        }

        setMeasuredDimension(measuredHeight, measuredWidth);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        System.out.println("... " + this.getClass().getSimpleName() + ".onLayout ...");
//        System.out.print("Gchanged=" + changed);
//        System.out.print(", Gleft=" + l);
//        System.out.print(", Gtop=" + t);
//        System.out.print(", Gright=" + r);
//        System.out.println(", Gbottom=" + b);


        int left = 0, top = 0, right = 0, bottom = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).layout(0, 0, 150, 200);
            left += getChildAt(i).getLeft();
            top += getChildAt(i).getTop();
            right += getChildAt(i).getRight();
            bottom += getChildAt(i).getBottom();
        }

        setLeft(left);
        setTop(top);
        setRight(right + 50);
        setBottom(bottom + 50);

    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        System.out.println("~~ " + this.getClass().getSimpleName() + ".generateLayoutParams ~~");
        return new CVGLayoutParams(getContext(), attrs);
    }
//        return super.generateLayoutParams(attrs);

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        System.out.println("~~ " + this.getClass().getSimpleName() + ".generateLayoutParams2 ~~");
        return super.generateLayoutParams(p);
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        System.out.println("~~ " + this.getClass().getSimpleName() + ".generateDefaultLayoutParams ~~");
        return super.generateDefaultLayoutParams();
    }

    public class CVGLayoutParams extends ViewGroup.LayoutParams {
        int align = 0;

        public CVGLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            System.out.println("~~ " + this.getClass().getSimpleName() + ".CVGLayoutParams ~~");
            TypedArray array = c.obtainStyledAttributes(attrs, R.styleable.CustomViewGroup);
            align = array.getInt(R.styleable.CustomViewGroup_align, -1);
            array.recycle();
        }
    }
}


