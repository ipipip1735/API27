package mine.recyclerview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
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

        System.out.println("getAlpha is " + viewHolder.itemView.getAlpha());
        System.out.println("getTranslationX is " + viewHolder.itemView.getTranslationX());
        System.out.println("getTranslationY is " + viewHolder.itemView.getTranslationY());

        //通过布局值计算动画值，这里为了简化直接取布局值
        float[] values = new float[1];
        values[0] = viewHolder.itemView.getTranslationX();
        pendingDisappearance.put(viewHolder, values);

        viewHolder.itemView.setTop(preLayoutInfo.top);

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

        System.out.println("target.getAlpha is " + viewHolder.itemView.getAlpha());

        //通过布局值计算动画值，这里为了简化直接取布局值
        float[] values = new float[1];
        values[0] = viewHolder.itemView.getTranslationX();
        pendingAppearance.put(viewHolder, values);

        //将View属性值设置为布局前的属性值
        viewHolder.itemView.setAlpha(0f);
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
        float[] values = new float[2];
        values[0] = preLayoutInfo.top;
        values[1] = postLayoutInfo.top;
        pendingPersistence.put(viewHolder, values);

        //将View属性值设置为布局前的属性值
        viewHolder.itemView.setY(preLayoutInfo.top);//设置为布局修改前的值
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
            final float x = pendingDisappearance.get(holder)[0];

            animateDisappearance.add(holder);
            holder.itemView.animate().setDuration(duration)
                    .alpha(0f)
                    .translationX(-holder.itemView.getWidth())
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            System.out.println("~~Disappearance onAnimationEnd~~");
                            holder.itemView.setAlpha(1f);
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
                    .alpha(1f)
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

            //方式一：使用View动画
//            holder.itemView.animate().setDuration(duration)
//                    .y(pendingPersistence.get(holder)[1])
//                    .setListener(new AnimatorListenerAdapter() {
//                        @Override
//                        public void onAnimationEnd(Animator animation) {
//                            dispatchAnimationFinished(holder);
//                            animatePersistence.remove(holder);
//                            if(isRunning()) dispatchAnimationsFinished();
//                        }
//                    })
//                    .start();

            //方式二：使用对象动画
            ObjectAnimator animator = ObjectAnimator.ofFloat(holder.itemView, "y", pendingPersistence.get(holder));
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    dispatchAnimationFinished(holder);
                    animatePersistence.remove(holder);
                    if(isRunning()) dispatchAnimationsFinished();
                }
            });
            animator.setDuration(duration).start();



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