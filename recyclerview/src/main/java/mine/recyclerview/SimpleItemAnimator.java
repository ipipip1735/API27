package mine.recyclerview;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/2.
 */
public class SimpleItemAnimator extends android.support.v7.widget.SimpleItemAnimator {

    Map<RecyclerView.ViewHolder, int[]> animateRemove = new HashMap<>();
    Map<RecyclerView.ViewHolder, int[]> animateMove = new HashMap<>();

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        System.out.println("-->animateRemove<--");
        System.out.println(((TextView) holder.itemView).getText() + "|holder is " + holder);

        int[] values = new int[1];
        values[0] = 0;
        animateRemove.put(holder, values);
        return true;
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        System.out.println("-->animateAdd<--");
        System.out.println(((TextView) holder.itemView).getText() + "|holder is " + holder);

        return true;
    }

    @Override
    public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        System.out.println("-->animateMove<--");
        System.out.println(((TextView) holder.itemView).getText() + "|holder is " + holder);

        int[] values = new int[2];
        values[0] = fromY;
        values[1] = toY;
        animateMove.put(holder, values);

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

        System.out.println("animateRemove'count is " + animateRemove.keySet().size());
        for (final RecyclerView.ViewHolder holder : animateRemove.keySet()) {

            holder.itemView.animate().setDuration(duration)
                    .x(holder.itemView.getX() + 150f)
                    .alpha(animateRemove.get(holder)[0] + 0.3f)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            System.out.println("~~onAnimateRemoveEnd~~");
                            holder.itemView.setX(0);
                            holder.itemView.setAlpha(1);
                            dispatchRemoveFinished(holder);
                        }
                    })
                    .start();
            System.out.println("animateRemove|" + ((TextView) holder.itemView).getText());
        }
        animateRemove.clear();


        System.out.println("animateMove'count is " + animateMove.keySet().size());
        for (final RecyclerView.ViewHolder holder : animateMove.keySet()) {
            holder.itemView.setY(animateMove.get(holder)[0]);
            holder.itemView.animate().setDuration(duration)
                    .y(animateMove.get(holder)[1])
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            System.out.println("~~onAnimateMoveEnd~~");
                            dispatchMoveFinished(holder);
                        }
                    })
                    .start();
            System.out.println("animateMove|" + ((TextView) holder.itemView).getText());
        }
        animateMove.clear();

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