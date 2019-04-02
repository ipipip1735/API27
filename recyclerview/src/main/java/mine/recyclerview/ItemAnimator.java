package mine.recyclerview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2019/4/2.
 */
public class ItemAnimator extends RecyclerView.ItemAnimator {

    Map<View, int[]> animateDisappearance = new HashMap<>();
    Map<View, int[]> animateAppearance = new HashMap<>();
    Map<View, int[]> animatePersistence = new HashMap<>();

    @Override
    public boolean animateDisappearance(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo preLayoutInfo, @Nullable ItemHolderInfo postLayoutInfo) {
        System.out.println("-->animateDisappearance<--");
        System.out.println(((TextView) viewHolder.itemView).getText() + "|viewHolder is " + viewHolder);

        //获取信息
        if (preLayoutInfo != null) {
            System.out.println("preLayoutInfo.left is " + preLayoutInfo.left);
            System.out.println("preLayoutInfo.top is " + preLayoutInfo.top);
            System.out.println("preLayoutInfo.right is " + preLayoutInfo.right);
            System.out.println("preLayoutInfo.bottom is " + preLayoutInfo.bottom);
        } else {
            System.out.println("preLayoutInfo is " + preLayoutInfo);
        }
        if (postLayoutInfo != null) {
            System.out.println("postLayoutInfo.left is " + postLayoutInfo.left);
            System.out.println("postLayoutInfo.top is " + postLayoutInfo.top);
            System.out.println("postLayoutInfo.right is " + postLayoutInfo.right);
            System.out.println("postLayoutInfo.bottom is " + postLayoutInfo.bottom);
        } else {
            System.out.println("postLayoutInfo is " + postLayoutInfo);
        }
        System.out.println("target.getLeft is " + viewHolder.itemView.getLeft());
        System.out.println("target.getTop is " + viewHolder.itemView.getTop());
        System.out.println("target.getRight is " + viewHolder.itemView.getRight());
        System.out.println("target.getBottom is " + viewHolder.itemView.getBottom());

        //通过布局值计算动画值，这里为了简化直接取布局值
        int[] values = new int[2];
        values[0] = 0;
        values[1] = 1;
        animateDisappearance.put(viewHolder.itemView, values);
        viewHolder.itemView.setVisibility(View.INVISIBLE);

        return true;
    }

    @Override
    public boolean animateAppearance(@NonNull RecyclerView.ViewHolder viewHolder, @Nullable ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
        System.out.println("-->animateAppearance<--");
        System.out.println(((TextView) viewHolder.itemView).getText() + "|viewHolder is " + viewHolder);


        //获取信息
        if (preLayoutInfo != null) {
            System.out.println("preLayoutInfo.left is " + preLayoutInfo.left);
            System.out.println("preLayoutInfo.top is " + preLayoutInfo.top);
            System.out.println("preLayoutInfo.right is " + preLayoutInfo.right);
            System.out.println("preLayoutInfo.bottom is " + preLayoutInfo.bottom);
        } else {
            System.out.println("preLayoutInfo is " + preLayoutInfo);
        }
        if (postLayoutInfo != null) {
            System.out.println("postLayoutInfo.left is " + postLayoutInfo.left);
            System.out.println("postLayoutInfo.top is " + postLayoutInfo.top);
            System.out.println("postLayoutInfo.right is " + postLayoutInfo.right);
            System.out.println("postLayoutInfo.bottom is " + postLayoutInfo.bottom);
        } else {
            System.out.println("postLayoutInfo is " + postLayoutInfo);
        }
        System.out.println("target.getLeft is " + viewHolder.itemView.getLeft());
        System.out.println("target.getTop is " + viewHolder.itemView.getTop());
        System.out.println("target.getRight is " + viewHolder.itemView.getRight());
        System.out.println("target.getBottom is " + viewHolder.itemView.getBottom());

        //通过布局值计算动画值，这里为了简化直接取布局值
        int[] values = new int[2];
        values[0] = preLayoutInfo.top;
        values[1] = postLayoutInfo.top;
        animateAppearance.put(viewHolder.itemView, values);


        return true;
    }

    @Override
    public boolean animatePersistence(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
        System.out.println("-->animatePersistence<--");
        System.out.println(((TextView) viewHolder.itemView).getText() + "|viewHolder is " + viewHolder);

        //获取信息
        if (preLayoutInfo != null) {
            System.out.println("preLayoutInfo.left is " + preLayoutInfo.left);
            System.out.println("preLayoutInfo.top is " + preLayoutInfo.top);
            System.out.println("preLayoutInfo.right is " + preLayoutInfo.right);
            System.out.println("preLayoutInfo.bottom is " + preLayoutInfo.bottom);
        } else {
            System.out.println("preLayoutInfo is " + preLayoutInfo);
        }
        if (postLayoutInfo != null) {
            System.out.println("postLayoutInfo.left is " + postLayoutInfo.left);
            System.out.println("postLayoutInfo.top is " + postLayoutInfo.top);
            System.out.println("postLayoutInfo.right is " + postLayoutInfo.right);
            System.out.println("postLayoutInfo.bottom is " + postLayoutInfo.bottom);
        } else {
            System.out.println("postLayoutInfo is " + postLayoutInfo);
        }
        System.out.println("target.getLeft is " + viewHolder.itemView.getLeft());
        System.out.println("target.getTop is " + viewHolder.itemView.getTop());
        System.out.println("target.getRight is " + viewHolder.itemView.getRight());
        System.out.println("target.getBottom is " + viewHolder.itemView.getBottom());

        //通过布局值计算动画值，这里为了简化直接取布局值
        int[] values = new int[2];
        values[0] = preLayoutInfo.top;
        values[1] = postLayoutInfo.top;
        animatePersistence.put(viewHolder.itemView, values);

        return true;
    }

    @Override
    public boolean animateChange(@NonNull RecyclerView.ViewHolder oldHolder, @NonNull RecyclerView.ViewHolder newHolder, @NonNull ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
        System.out.println("-->animateChange<--");

        //获取信息
        if (preLayoutInfo != null) {
            System.out.println("preLayoutInfo.left is " + preLayoutInfo.left);
            System.out.println("preLayoutInfo.top is " + preLayoutInfo.top);
            System.out.println("preLayoutInfo.right is " + preLayoutInfo.right);
            System.out.println("preLayoutInfo.bottom is " + preLayoutInfo.bottom);
        } else {
            System.out.println("preLayoutInfo is " + preLayoutInfo);
        }
        if (postLayoutInfo != null) {
            System.out.println("postLayoutInfo.left is " + postLayoutInfo.left);
            System.out.println("postLayoutInfo.top is " + postLayoutInfo.top);
            System.out.println("postLayoutInfo.right is " + postLayoutInfo.right);
            System.out.println("postLayoutInfo.bottom is " + postLayoutInfo.bottom);
        } else {
            System.out.println("postLayoutInfo is " + postLayoutInfo);
        }

        return true;
    }

    @Override
    public void runPendingAnimations() {
        System.out.println("-->runPendingAnimations<--");

        long duration = 3000L;


        for (View v : animateDisappearance.keySet()) {
            v.animate().setDuration(duration)
                    .x(v.getX() + 150f)
                    .alpha(animateDisappearance.get(v)[0])
                    .start();
            System.out.println("animateDisappearance|" + ((TextView) v).getText());
        }

        for (View v : animateAppearance.keySet()) {
            v.animate().setDuration(duration)
                    .x(v.getX() + 15f)
                    .y(animateAppearance.get(v)[1])
                    .start();
        }

        for (View v : animatePersistence.keySet()) {
            v.setY(animatePersistence.get(v)[0]);
            v.animate().setDuration(duration)
                    .y(animatePersistence.get(v)[1])
                    .start();

            System.out.println("animatePersistence|" + ((TextView) v).getText());
        }

    }


    @Override
    public void endAnimation(RecyclerView.ViewHolder item) {
        System.out.println("-->endAnimation<--");
    }

    @Override
    public void endAnimations() {
        System.out.println("-->endAnimations<--");
    }

    @Override
    public boolean isRunning() {
        System.out.println("-->isRunning<--");
        return false;
    }

}