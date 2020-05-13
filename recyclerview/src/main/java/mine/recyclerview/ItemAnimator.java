package mine.recyclerview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2019/4/2.
 */
public class ItemAnimator extends RecyclerView.ItemAnimator {

    private Map<RecyclerView.ViewHolder, float[]> pendingDisappearance = new HashMap<>();
    private Map<RecyclerView.ViewHolder, float[]> pendingAppearance = new HashMap<>();
    private Map<RecyclerView.ViewHolder, float[]> pendingPersistence = new HashMap<>();
    private List<RecyclerView.ViewHolder> animateDisappearance = new ArrayList<>();
    private List<RecyclerView.ViewHolder> animateAppearance = new ArrayList<>();
    private List<RecyclerView.ViewHolder> animatePersistence = new ArrayList<>();


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
        float[] values = new float[2];
        values[0] = viewHolder.itemView.getAlpha();
        values[1] = viewHolder.itemView.getTranslationX();
        pendingDisappearance.put(viewHolder, values);

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
        float[] values = new float[2];
        values[0] = viewHolder.itemView.getAlpha();;
        values[1] = viewHolder.itemView.getTranslationX();
        pendingAppearance.put(viewHolder, values);

        viewHolder.itemView.setAlpha(viewHolder.itemView.getAlpha());
        viewHolder.itemView.setTranslationX(-viewHolder.itemView.getWidth());
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
        float[] values = new float[1];
        values[0] = postLayoutInfo.top;
        pendingPersistence.put(viewHolder, values);

        viewHolder.itemView.setY(preLayoutInfo.top);
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
        for (final RecyclerView.ViewHolder holder : pendingDisappearance.keySet()) {
            final float alpha = pendingDisappearance.get(holder)[0];
            final float x = pendingDisappearance.get(holder)[1];

            animateDisappearance.add(holder);
            holder.itemView.animate().setDuration(duration)
                    .alpha(alpha * 0f)
                    .translationX(x + 150f)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            holder.itemView.setAlpha(alpha);
                            holder.itemView.setTranslationX(x);
                            dispatchAnimationFinished(holder);
                            animateDisappearance.remove(holder);
                            if(isRunning()) dispatchAnimationsFinished();
                        }
                    })
                    .start();
            System.out.println("pendingDisappearance|" + ((TextView) holder.itemView).getText());
        }
        pendingDisappearance.clear();


        //创建入屏动画
        for (final RecyclerView.ViewHolder holder : pendingAppearance.keySet()) {

            animateAppearance.add(holder);
            holder.itemView.animate().setDuration(duration)
                    .translationX(pendingAppearance.get(holder)[0])
                    .alpha(pendingAppearance.get(holder)[0])
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            dispatchAnimationFinished(holder);
                            animateAppearance.remove(holder);
                            if(isRunning()) dispatchAnimationsFinished();
                        }
                    })
                    .start();

            System.out.println("pendingAppearance|" + ((TextView) holder.itemView).getText());
        }
        pendingAppearance.clear();


        //创建屏中动画
        for (final RecyclerView.ViewHolder holder : pendingPersistence.keySet()) {

            animatePersistence.add(holder);
            holder.itemView.animate().setDuration(duration)
                    .y(pendingPersistence.get(holder)[0])
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            dispatchAnimationFinished(holder);
                            animatePersistence.remove(holder);
                            if(isRunning()) dispatchAnimationsFinished();
                        }
                    })
                    .start();
            System.out.println("pendingPersistence|" + ((TextView) holder.itemView).getText());
        }
        pendingPersistence.clear();

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
        if(!pendingDisappearance.isEmpty()
        ||!pendingAppearance.isEmpty()
        ||!pendingPersistence.isEmpty()
        ||!animateDisappearance.isEmpty()
        ||!animateAppearance.isEmpty()
        ||!animatePersistence.isEmpty()) return false;
        return true;
    }

}