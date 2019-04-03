package mine.recyclerview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2019/4/2.
 */
public class ItemAnimator extends RecyclerView.ItemAnimator {

   private Map<RecyclerView.ViewHolder, int[]> animateDisappearance = new HashMap<>();
   private Map<RecyclerView.ViewHolder, int[]> animateAppearance = new HashMap<>();
   private Map<RecyclerView.ViewHolder, int[]> animatePersistence = new HashMap<>();

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
        values[0] = 1;
        values[1] = (int) viewHolder.itemView.getX();
        animateDisappearance.put(viewHolder, values);

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
        animateAppearance.put(viewHolder, values);


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
        animatePersistence.put(viewHolder, values);

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

        //创建出屏动画
        for (final RecyclerView.ViewHolder holder : animateDisappearance.keySet()) {
            final int alpha = animateDisappearance.get(holder)[0];
            final int x = animateDisappearance.get(holder)[1];

            holder.itemView.animate().setDuration(duration)
                    .alpha(alpha * 0.1f)
                    .x(x+150)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            holder.itemView.setAlpha(alpha);
                            holder.itemView.setX(x);
                            dispatchAnimationFinished(holder);
                        }
                    })
                    .start();
            System.out.println("animateDisappearance|" + ((TextView) holder.itemView).getText());
        }
        animateDisappearance.clear();




        //创建入屏动画
        for (final RecyclerView.ViewHolder holder : animateAppearance.keySet()) {
            holder.itemView.setY(animateAppearance.get(holder)[0]);
            holder.itemView.animate().setDuration(duration)
                    .y(animateAppearance.get(holder)[1])
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            dispatchAnimationFinished(holder);
                        }
                    })
                    .start();

            System.out.println("animateAppearance|" + ((TextView) holder.itemView).getText());
        }
        animateAppearance.clear();



        //创建屏中动画
        for (final RecyclerView.ViewHolder holder : animatePersistence.keySet()) {
            holder.itemView.setY(animatePersistence.get(holder)[0]);
            holder.itemView.animate().setDuration(duration)
                    .y(animatePersistence.get(holder)[1])
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            dispatchAnimationFinished(holder);
                        }
                    })
                    .start();
            System.out.println("animatePersistence|" + ((TextView) holder.itemView).getText());
        }
        animatePersistence.clear();





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