package mine.recyclerview;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/2.
 */
public class SimpleItemAnimator extends android.support.v7.widget.SimpleItemAnimator {

    Map<RecyclerView.ViewHolder, float[]> animateRemove = new HashMap<>();
    Map<RecyclerView.ViewHolder, float[]> animateAdd = new HashMap<>();
    Map<RecyclerView.ViewHolder, int[]> animateMove = new HashMap<>();

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        System.out.println("-->animateRemove<--");
        System.out.println(((TextView) holder.itemView).getText() + "|holder is " + holder);

        View v = holder.itemView;
        float[] values = new float[1];
        values[0] = v.getTranslationX();
        animateRemove.put(holder, values);
        return true;
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        System.out.println("-->animateAdd<--");
        System.out.println(((TextView) holder.itemView).getText() + "|holder is " + holder);

        View v = holder.itemView;
        float[] values = new float[1];
        values[0] = v.getTranslationX();

        animateAdd.put(holder, values);

        v.setAlpha(0f);
        v.setTranslationX(-v.getWidth());

        return true;
    }

    @Override
    public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        System.out.println("-->animateMove<--");
        System.out.println(((TextView) holder.itemView).getText() + "|holder is " + holder);

        int[] values = new int[1];
        values[0] = toY;
        animateMove.put(holder, values);

        holder.itemView.setY(fromY);
        return true;
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
        System.out.println("-->animateChange<--");
        System.out.println(((TextView) oldHolder.itemView).getText() + "|oldHolder is " + oldHolder);
        System.out.println(((TextView) newHolder.itemView).getText() + "|newHolder is " + newHolder);

        return true;
    }

    @Override
    public void runPendingAnimations() {
        System.out.println("-->runPendingAnimations<--");

        long duration = 2500L;

        System.out.println("animateMove'count is " + animateMove.keySet().size());
        for (final RecyclerView.ViewHolder holder : animateMove.keySet()) {

            holder.itemView.animate().setDuration(duration)
                    .y(animateMove.get(holder)[0])
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            System.out.println("~~onAnimateMoveEnd~~");
                            holder.itemView.animate().setListener(null);
                            dispatchMoveFinished(holder);
                        }
                    })
                    .start();
            System.out.println("animateMove|" + ((TextView) holder.itemView).getText());
        }
        animateMove.clear();


        System.out.println("animateRemove'count is " + animateRemove.keySet().size());
        for (final RecyclerView.ViewHolder holder : animateRemove.keySet()) {
            final float x = animateRemove.get(holder)[0];

            holder.itemView.animate().setDuration(duration)
                    .translationX(animateRemove.get(holder)[0] + 150f)
                    .alpha(0f)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            System.out.println("~~onAnimateRemoveEnd~~");
                            System.out.println(holder);
                            holder.itemView.animate().setListener(null);
                            holder.itemView.setTranslationX(x);
                            holder.itemView.setAlpha(0f);
                            dispatchRemoveFinished(holder);
                        }
                    });
            System.out.println("animateRemove|" + ((TextView) holder.itemView).getText());
        }
        animateRemove.clear();


        System.out.println("animateAdd'count is " + animateAdd.keySet().size());
        for (final RecyclerView.ViewHolder holder : animateAdd.keySet()) {
            holder.itemView.animate().setDuration(duration)
                    .translationX(animateAdd.get(holder)[0])
                    .alpha(1f)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            System.out.println("~~onAnimateRemoveEnd~~");
                            holder.itemView.animate().setListener(null);
                            dispatchAddFinished(holder);
                        }
                    });
            System.out.println("animateAdd|" + ((TextView) holder.itemView).getText());
        }
        animateAdd.clear();
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