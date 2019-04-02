package mine.recyclerview;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/2.
 */
public class SimpleItemAnimator extends android.support.v7.widget.SimpleItemAnimator {

    Map<View, int[]> animateRemove = new HashMap<>();
    Map<View, int[]> animateMove = new HashMap<>();

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        System.out.println("-->animateRemove<--");
        System.out.println(((TextView) holder.itemView).getText() + "|holder is " + holder);

        int[] values = new int[1];
        values[0] = 0;
        animateRemove.put(holder.itemView, values);
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
        animateMove.put(holder.itemView, values);

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

        long duration = 3000L;

        for (View v : animateRemove.keySet()) {
            v.animate().setDuration(duration)
                    .x(v.getX() + 150f)
                    .alpha(animateRemove.get(v)[0])
                    .start();
            System.out.println("animateRemove|" + ((TextView) v).getText());
        }
        for (View v : animateMove.keySet()) {
            v.setY(animateMove.get(v)[0]);
            v.animate().setDuration(duration)
                    .y(animateMove.get(v)[1])
                    .start();
            System.out.println("animateMove|" + ((TextView) v).getText());
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